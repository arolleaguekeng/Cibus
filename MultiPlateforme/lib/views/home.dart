import 'package:cibus_multi_plateforme/models/api/restaurant.api.dart';
import 'package:cibus_multi_plateforme/views/widgets/restaurant_card.dart';
import 'package:flutter/material.dart';

import '../models/restaurant.dart';
import 'Activities/restaurant_details.dart';

final baseUrl = "http://192.168.1.107:8000/static/";


class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}
class _HomePageState extends State<HomePage> {

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
          // actionsIconTheme: Icons.arrow_back_ios,
          title: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: const [
              Icon(Icons.arrow_back_ios),
              SizedBox(width: 100),
              Text('Nos Restaurants'),
            ],
          ),
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
                thumbnailUrl: baseUrl + _recipes[index].image,
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


