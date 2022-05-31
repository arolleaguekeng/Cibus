
import 'package:flutter/foundation.dart';

class Categories{
  final int category_id;
  final String image;
  final String nom;

  Categories(
      {
      required this.category_id,
      required this.image,
      required this.nom
      });

  factory Categories.fromJson(dynamic json){
    return Categories(
        category_id: json['category_id'] as int,
        image: json['image'] as String,
        nom: json['nom'] as String
    );
  }

  static List<Categories> recipesFromSnapshot(List snapshot){
    return snapshot.map((data) => Categories.fromJson(data)).toList();
  }


}