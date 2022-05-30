import 'dart:ffi';

class User{
  final int user_id;
  final int localisation_id;
  final String nom;
  final String email;
  final String password;
  final int phone;
  final String imgae;

  User({
    required this.user_id,
    required this.localisation_id,
    required this.nom,
    required this.email,
    required this.password,
    required this.phone,
    required this.imgae});

  factory User.fromJson(dynamic json){
    return User(
    user_id: json['user_id'] as int,
    localisation_id: json['localisation_id'] as int,
    nom: json['nom'] as String,
    email: json["email"] as String,
    password: json['password'] as String,
    phone: json['phone'] as int,
    imgae: json['image'] as String
  );
    }
  static List<User> recipesFromSnapshot(List snapshot){
    return snapshot.map((data) => User.fromJson(data)).toList();
  }
  @override
  String toString(){
    return 'Restaurant {name: $nom, image: $imgae,}';
  }
}



