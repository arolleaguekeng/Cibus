import 'dart:convert';
import 'package:cibus_multi_plateforme/models/restaurant.dart';
import 'package:cibus_multi_plateforme/services/api_services.dart';
import 'package:cibus_multi_plateforme/models/category.dart';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;


class CategoryApi{

  static Future<List<Categories>> getCategories() async{
    var uri = Uri.http(api_services.baseUrl, '/category/');
    final response = await http.get(uri);
    List data = jsonDecode(response.body);
    List temp = [];
    for (var i in data) {
      print(i);
      temp.add(i);
    }
    return Categories.recipesFromSnapshot(temp);
  }

  static Future<Categories> getrestaurantById(int Id) async{
    //http://192.168.1.107:8000/login?email=string&password=string
    final response = await http
        .get(Uri.parse('${api_services.httpbaseUrl}/Restaurant$Id'));
    if (response.statusCode == 200){
      Categories data = jsonDecode(response.body);
      print("============================================="+response.body.toString());
      return data;
    }
    else{
      throw Exception('email or password is incorrect !');
    }
  }
}