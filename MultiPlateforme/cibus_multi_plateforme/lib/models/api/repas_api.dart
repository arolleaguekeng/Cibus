import 'dart:convert';
import 'package:cibus_multi_plateforme/models/repas.dart';
import 'package:cibus_multi_plateforme/models/restaurant.dart';
import 'package:cibus_multi_plateforme/services/api_services.dart';
import 'package:http/http.dart' as http;
class RepasApi{

  static Future<List<Repas>> getRepas(Restaurant restaurant) async{
    var uri = Uri.http(api_services.baseUrl, '/repas/');
    final response = await http.get(uri);
    List data = jsonDecode(response.body);
    List temp = [];
    List<Repas> restauxRepas = [];
    for (var i in data) {
      print(i);
      temp.add(i);
    }

    var repass =  Repas.recipesFromSnapshot(temp);
    for (var repas in repass)
      {
        if(repas.restaurant_id == restaurant.restaurant_id){
              restauxRepas.add(repas);
        }
      }
    return restauxRepas;
  }


static Future<List<Repas>> getBestRepas() async{
    var uri = Uri.http(api_services.baseUrl, '/repas/');
    final response = await http.get(uri);
    List data = jsonDecode(response.body);
    List temp = [];
    List<Repas> restauxRepas = [];
    for (var i in data) {
      print(i);
      temp.add(i);
  }

  var repass =  Repas.recipesFromSnapshot(temp);
  for (var repas in repass)
  {

    if(repas.rating >=4){
      restauxRepas.add(repas);
    }
  }
  return restauxRepas;
  }
}


