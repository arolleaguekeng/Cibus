import 'package:cibus_multi_plateforme/models/repas.dart';

class api_services{
  static  String baseUrl = "192.168.1.107:8000";
  static  String httpbaseUrl = "http://$baseUrl";
  static  String baseImageUrlRestaurant = "$httpbaseUrl/static/Restaurants/";
  static  String baseImageUrlRepas = "$httpbaseUrl/static/Repas/";
  static  String baseImageUrlcategory = "$httpbaseUrl/static/Categorie/";
  static List<Repas> myCart = [];

}