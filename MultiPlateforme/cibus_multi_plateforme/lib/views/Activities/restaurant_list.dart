import 'package:cibus_multi_plateforme/models/api/restaurant.api.dart';
import 'package:cibus_multi_plateforme/models/restaurant.dart';
import 'package:cibus_multi_plateforme/services/api_services.dart';
import 'package:cibus_multi_plateforme/views/widgets/restaurant_card.dart';
import 'package:flutter/material.dart';

import 'restaurant_details.dart';

final baseUrl = "http://192.168.1.107:8000/static/";


class RestaurantList extends StatefulWidget {
  @override
  _RestaurantListState createState() => _RestaurantListState();
}
class _RestaurantListState extends State<RestaurantList> {

  late List<Restaurant> _recipes;
  bool _isLoading = true;

  @override
  void initState(){
    super.initState();
    getRestaurant();
  }

  Future<void> getRestaurant() async{
    _recipes = await RestaurantApi.getRestaurant();
    setState((){
      _isLoading = false;
    });
    print(_recipes);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.white,
          // actionsIconTheme: Icons.arrow_back_ios,
          title: Text(
              'Nos Restaurants',
            style: TextStyle(
              color: Colors.black
            ),
          ),
          centerTitle: true,
        ),
        body: _isLoading
            ? const Center(child: CircularProgressIndicator())
            : ListView.builder(
          itemCount: _recipes.length,
          itemBuilder: (context, index) {
            return RestaurantCard(
              nom: _recipes[index].nom,
              description: _recipes[index].description,
              cookTime: _recipes[index].description,
              rating: _recipes[index].rating.toString(),
              thumbnailUrl: api_services.baseImageUrlRestaurant + _recipes[index].image,
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context)=>RestaurantDetails(_recipes[index]))
                );
                print(_recipes[index]);
              },
            );
          },
        ));
  }
}


