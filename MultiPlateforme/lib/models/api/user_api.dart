import 'dart:convert';

import 'package:cibus_multi_plateforme/models/user.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class userApi{

  static Future<List<User>> getRestaurant() async{
    var uri = Uri.http('192.168.1.107:8000', '/user/');
    final response = await http.get(uri);
    List data = jsonDecode(response.body);
    List temp = [];
    for (var i in data) {
      print(i);
      temp.add(i);
    }
    return User.recipesFromSnapshot(temp);
  }
}