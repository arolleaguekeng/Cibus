import 'package:cibus_multi_plateforme/models/api/repas_api.dart';
import 'package:cibus_multi_plateforme/models/restaurant.dart';
import 'package:cibus_multi_plateforme/services/api_services.dart';
import 'package:cibus_multi_plateforme/views/widgets/repas_card.dart';
import 'package:flutter/material.dart';

import '../../models/repas.dart';

final baseUrl = "http://192.168.1.107:8000/static/";
bool _isLoading = true;

class RestaurantDetails extends StatefulWidget {
  final Restaurant restaurant;

  RestaurantDetails(this.restaurant);
  @override
  _RestaurantDetailsState createState() => _RestaurantDetailsState(restaurant);
}
class _RestaurantDetailsState extends State<RestaurantDetails>{

  late List<Repas> _recipes;
  bool _isLoading = true;
  final Restaurant restaurant;

  _RestaurantDetailsState(this.restaurant);

  @override
  void initState(){
    super.initState();
    getRepas();
  }

  Future<void> getRepas() async{
    var liste = await RepasApi.getRepas(restaurant);
    _recipes = <Repas>[];
    _recipes = liste;
    setState((){
      _isLoading = false;
    });
    print(_recipes);
  }
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(

      appBar: AppBar(
        elevation: 0.8,
        title: Text(restaurant.nom),

        backgroundColor: Colors.white,
      ),

      body: Material(
          child: Column(children: <Widget>[
            Expanded(
                child: ListView(
                  children: <Widget>[
                    Container(
                        margin: EdgeInsets.symmetric(horizontal: 0, vertical: 0),
                        width: MediaQuery.of(context).size.width,
                        height: 300,
                        decoration: BoxDecoration(
                          color: Colors.black,
                          borderRadius: BorderRadius.circular(10),
                          boxShadow: [
                            BoxShadow(
                              color: Colors.black.withOpacity(0.6),
                              offset: const Offset(
                                0.0,
                                10.0,
                              ),
                              blurRadius: 10.0,
                              spreadRadius: -6.0,
                            ),
                          ],
                          image: DecorationImage(
                            colorFilter: ColorFilter.mode(
                              Colors.black.withOpacity(0.4),
                              BlendMode.multiply,
                            ),
                            image: NetworkImage(api_services.baseImageUrlRestaurant+restaurant.image),
                            fit: BoxFit.cover,
                          ),
                        ),
                        child: Stack(
                          children: [
                            Align(
                              alignment: Alignment.center,
                              child: Padding(
                                padding: const EdgeInsets.symmetric(horizontal: 5.0),
                                child: Text(
                                  restaurant.nom,
                                  style: const TextStyle(
                                    fontSize: 40,
                                  ),
                                  overflow: TextOverflow.ellipsis,
                                  maxLines: 2,
                                  textAlign: TextAlign.center,
                                ),
                              ),
                            ),
                            Align(
                              alignment: Alignment.bottomCenter,
                              child: Padding(
                                padding: const EdgeInsets.symmetric(vertical: 100.0),
                                child: Text(
                                  restaurant.description,
                                  style: const TextStyle(
                                      fontSize: 20,
                                      color: Colors.amberAccent
                                  ),
                                  overflow: TextOverflow.ellipsis,
                                  maxLines: 4,
                                  textAlign: TextAlign.center,
                                ),
                              ),
                            ),
                            Align(
                              alignment: Alignment.bottomLeft,
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                children: [
                                  Container(
                                    padding: EdgeInsets.all(5),
                                    margin: EdgeInsets.all(10),
                                    decoration: BoxDecoration(
                                      color: Colors.black.withOpacity(0.4),
                                      borderRadius: BorderRadius.circular(15),
                                    ),
                                    child: Row(
                                      children: [
                                        const Icon(
                                          Icons.favorite_border,
                                          color: Colors.yellow,
                                          size: 25,

                                        ),
                                        const SizedBox(width: 7),
                                        Text(restaurant.rating.toString()),
                                      ],
                                    ),
                                  ),
                                  Container(
                                    padding: const EdgeInsets.all(5),
                                    margin: const EdgeInsets.all(10),
                                    decoration: BoxDecoration(
                                      color: Colors.black.withOpacity(0.4),
                                      borderRadius: BorderRadius.circular(15),
                                    ),
                                    child: Row(
                                      children: [
                                        const Icon(
                                          Icons.schedule,
                                          color: Colors.yellow,
                                          size: 18,
                                        ),
                                        const SizedBox(width: 7),
                                        Text(restaurant.description),
                                      ],
                                    ),
                                  ),
                                ],
                              ),
                            ),

                            Align(
                              alignment: Alignment.topLeft,
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                                children: [
                                  Container(
                                    padding: EdgeInsets.all(5),
                                    margin: EdgeInsets.all(10),
                                    decoration: BoxDecoration(
                                      color: Colors.black.withOpacity(0.4),
                                      borderRadius: BorderRadius.circular(15),
                                    ),
                                    child: Row(
                                      children: const [
                                        Icon(
                                          Icons.arrow_back,
                                          color: Colors.yellow,
                                          size: 30,
                                        ),
                                        SizedBox(width: 7)
                                      ],
                                    ),
                                  ),
                                  Container(
                                    padding: const EdgeInsets.all(5),
                                    margin: const EdgeInsets.all(10),
                                    decoration: BoxDecoration(
                                      color: Colors.black.withOpacity(0.4),
                                      borderRadius: BorderRadius.circular(15),
                                    ),
                                    child: Row(
                                      children: const [
                                        Icon(
                                          Icons.star_border,
                                          color: Colors.yellow,
                                          size: 30,
                                        ),
                                      ],
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        )
                    ),
                    SizedBox(height: size.height *0.03),
                    const Text(
                      "Nos Repas",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                          fontSize: 20
                      ),
                    ),

                    Container(
                        height: size.height /2,
                        child: ListView.builder(
                          scrollDirection: Axis.vertical,
                          shrinkWrap: true,
                          itemCount: _recipes.length,
                          itemBuilder: (context, index) {
                            return RepasCard(
                              nom: _recipes[index].nom,
                              description: _recipes[index].description,
                              price: _recipes[index].price,
                              rating: _recipes[index].rating,
                              image: api_services.baseImageUrlRepas + _recipes[index].image,
                              onTap: () {
                                Navigator.push(context, MaterialPageRoute(builder: (context)=>RestaurantDetails(restaurant))
                                );
                                print(_recipes[index]);
                              },
                            );
                          },)

                    ),],
                )
            )
          ]
          )
      ),
    );
  }

}



