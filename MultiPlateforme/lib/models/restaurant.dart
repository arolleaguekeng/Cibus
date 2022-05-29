import 'dart:ffi';

class Restaurant{
  final int restaurant_id;
  final String image;
  final String nom;
  final String description;
  final double rating;



  Restaurant(
      {required this.restaurant_id,
      required this.image,
      required this.nom,
      required this.description,
      required this.rating});

  factory Restaurant.fromJson(dynamic json){
    return Restaurant(
        restaurant_id: json['restaurant_id'] as int,
        image: json['image'] as String,
        nom: json['nom'] as String,
        description: json ['description'] as String,
        rating: json['rating'] as double
    );
  }

  static List<Restaurant> recipesFromSnapshot(List snapshot){
    return snapshot.map((data) => Restaurant.fromJson(data)).toList();
  }

  @override
  String toString(){
    return 'Restaurant {name: $nom, image: $image, rating: $rating, description: $description}';
  }
}