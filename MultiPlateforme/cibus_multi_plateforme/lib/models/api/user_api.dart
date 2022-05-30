import 'dart:convert';
import 'dart:math';

import 'package:cibus_multi_plateforme/models/user.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class userApi{
  static bool IsLogin = false;
  static  User? currentUser;
  static void loginUser(String username, String password) async{
    //http://192.168.1.107:8000/login?email=string&password=string
    final response = await http
        .get(Uri.parse('http://192.168.1.107:8000/login?email=$username&password=$password'));
    if (response.statusCode == 200){
      print("============================================="+response.body.toString());
      IsLogin = true;
      // print("login sccess");
      // print(response.body);
      // List data = jsonDecode(response.body);
      // List temp = [];
      // for (var i in data) {
      //   print("++++++++++++++++++++++++++++"+i);
      //   temp.add(i);
      // }
      // var a =User.recipesFromSnapshot(temp);
      // currentUser = a[0];
    }
    else{
      IsLogin = false;
      throw Exception('email or password is incorrect !');
    }
    }


static void login(String username, String password){
    loginUser(username,password);
}
//signup new user
  static createUser(String username, String password) async{
    try{
      final response = await http
       .post(
          Uri.parse(
              'http://192.168.1.107:8000/user',
          ),
        body: {
            'email' : username,
            'password' : password
        }
      );
      if(response.statusCode == 200){
        print('account created successfull');
        loginUser(username, password);
      }
      else{
        print("failed to create account");
      }
    } on Exception catch(e){
      print(e.toString());
    }
  }
  }
