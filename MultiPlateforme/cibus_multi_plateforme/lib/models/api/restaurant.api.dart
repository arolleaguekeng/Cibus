import 'dart:convert';
import 'package:cibus_multi_plateforme/models/restaurant.dart';
import 'package:http/http.dart' as http;


class RestaurantApi{

  static Future<List<Restaurant>> getRestaurant() async{
    var uri = Uri.http('192.168.1.107:8000', '/Restaurants/');
    final response = await http.get(uri);
    List data = jsonDecode(response.body);
    List temp = [];
    for (var i in data) {
      print(i);
      temp.add(i);
    }
    return Restaurant.recipesFromSnapshot(temp);
  }
}