import 'package:cibus_multi_plateforme/components/rounded_button.dart';
import 'package:cibus_multi_plateforme/constants.dart';
import 'package:cibus_multi_plateforme/models/api/category_api.dart';
import 'package:cibus_multi_plateforme/models/api/repas_api.dart';
import 'package:cibus_multi_plateforme/models/api/restaurant.api.dart';
import 'package:cibus_multi_plateforme/models/category.dart';
import 'package:cibus_multi_plateforme/models/repas.dart';
import 'package:cibus_multi_plateforme/models/restaurant.dart';
import 'package:cibus_multi_plateforme/services/api_services.dart';
import 'package:cibus_multi_plateforme/views/Activities/restaurant_details.dart';
import 'package:cibus_multi_plateforme/views/widgets/RestaurantNewCard.dart';
import 'package:cibus_multi_plateforme/views/widgets/components/category_card.dart';
import 'package:flutter/material.dart';
import 'package:smooth_star_rating/smooth_star_rating.dart';
import 'widgets/repas_card.dart';
import 'widgets/restaurant_card.dart';
import 'widgets/restaurant_home_card.dart';
// final baseUrl = "http://192.168.1.107:8000/static/";



class Dashboard extends StatefulWidget {
  const Dashboard({Key? key}) : super(key: key);

  @override
  State<Dashboard> createState() => _DashboardState();
}
late final List<Restaurant> restaux;

class _DashboardState extends State<Dashboard> {
    List<Widget> boxes = [];
    late List<Restaurant> _recipes;
    late List<Categories> _categoriesList;
   late List<Repas> _repasList;
    bool _isLoading = true;
    bool _isLoadingRepas = true;
    bool _isLoadingcat = true;

  Future<void> getcategories() async{
    var liste = await CategoryApi.getCategories();
    _categoriesList = <Categories>[];
    _categoriesList = liste;
    setState((){
      _isLoadingcat = false;
    });
  }
  Future<void> getRestaurant() async{
    var liste = await RestaurantApi.getRestaurant();
    _recipes = <Restaurant>[];
    _recipes = liste;
    setState((){
      _isLoading = false;
    });
    print(_recipes);
    getRepas();
  }
    Future<void> getRepas() async{
      var liste = await RepasApi.getBestRepas();
      _repasList = <Repas>[];
      _repasList = liste;
      setState((){
        _isLoadingRepas = false;
      });
      print(_repasList);
    }

  @override
  void initState() {

    boxes.add(
      Container(
        width: 150.0,
        child: DecoratedBox(
          decoration: BoxDecoration(color: Colors.red),
        ),
      ),
    );
    boxes.add(

      Container(
        width: 150.0,
        child: const DecoratedBox(
          decoration: BoxDecoration(color: Colors.green),
        ),
      ),
    );
    boxes.add(
      Container(
        width: 150.0,
        child: Container(
          decoration: const BoxDecoration(color: Colors.blue),
        ),
      ),
    );
    boxes.add(
      Container(
        width: 150.0,
        child: const DecoratedBox(
          decoration: BoxDecoration(color: Colors.orange),
        ),
      ),
    );
    getRestaurant();
    getRepas();
    getcategories();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(

      appBar: AppBar(
        backgroundColor: Colors.white,
        title: Text(
            "Cibus",
          style: TextStyle(
            fontSize: 25,
            fontWeight: FontWeight.bold,
            color: kPrimaryColor,
          ),
        ),
        leading: GestureDetector(
          onTap: () { /* Write listener code here */ },
          child: Icon(
            Icons.menu,
            color: kPrimaryColor,// add custom icons also
          ),
        ),
        actions: <Widget>[
          Padding(
              padding: EdgeInsets.only(right: 20.0),
              child: GestureDetector(
                onTap: () {
                  showModalBottomSheet(
                      context: context,
                      shape: const RoundedRectangleBorder(
                        borderRadius: BorderRadius.vertical(top: Radius.circular(25.0)),
                      ),
                      builder: (context){
                        return Container(
                          height: size.height *2,
                        );
                      });
                },
                child: Icon(
                  Icons.search,
                  size: 26.0,
                  color: kPrimaryColor,
                ),
              )
          ),
          Padding(
              padding: EdgeInsets.only(right: 20.0),
              child: GestureDetector(
                onTap: () {
                    showModalBottomSheet(
                    context: context,
                    shape: const RoundedRectangleBorder(
                    borderRadius: BorderRadius.vertical(top: Radius.circular(25.0)),
                    ),
                    builder: (context){
                  return Container(
                  );
                  });
                },
                child: Icon(
                    Icons.settings,
                  color: kPrimaryColor,
                ),
              )
          ),
        ],
      ),

      body: Material(
          child: Column(
              children: <Widget>[
            Expanded(

                child: ListView(
                  children: <Widget>[
                    SizedBox(height: size.height *0.03),
                    const Text(
                      "Category",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                          fontSize: 20
                      ),
                    ),
                    SizedBox(height: size.height *0.01),
                    Container(
                      height: 100.0,
                      child: _isLoadingcat
                          ? const Center(child: CircularProgressIndicator())
                          : ListView.builder(
                          scrollDirection: Axis.horizontal,
                          shrinkWrap: true,
                          itemCount: _categoriesList.length,
                          itemBuilder: (context, index) {
                            return CategoryCard(
                              nom: _categoriesList[index].nom,
                              image: api_services.baseImageUrlcategory + _categoriesList[index].image,
                              onTap: () {
                                Navigator.push(context, MaterialPageRoute(builder: (context)=>RestaurantDetails(_recipes[index]))
                                );
                                print(_recipes[index]);
                              },
                            );
                          }
                      ),

                    ),
                    SizedBox(height: size.height *0.03),
                    const Text(
                      "Popular Restaurant",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                        fontSize: 20
                      ),
                    ),
                    SizedBox(height: size.height *0.01),
                    Container(
                      height: 220.0,
                      child: _isLoading
                          ? const Center(child: CircularProgressIndicator())
                          : ListView.builder(
                          scrollDirection: Axis.horizontal,
                          shrinkWrap: true,
                          itemCount: _recipes.length,
                          itemBuilder: (context, index) {
                            return RestaurantHomeCard(
                              nom: _recipes[index].nom,
                              description: _recipes[index].description,
                              cookTime: _recipes[index].description,
                              rating: _recipes[index].rating,
                              thumbnailUrl: api_services.baseImageUrlRestaurant + _recipes[index].image,
                              onTap: () {
                                try{
                                  Navigator.push(context, MaterialPageRoute(builder: (context)=>RestaurantDetails(_recipes[index])));
                                }
                                  on Exception catch (exception){
                                  }

                                print(_recipes[index]);
                              },
                            );
                          }
                      ),

                    ),
                    const Text(
                      "Popular Foods Nearby",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                          fontSize: 20
                      ),
                    ),
                    Container(
                      height: 130.0,
                      width: 1,
                      child: _isLoadingRepas
                          ? const Center(child: CircularProgressIndicator())
                          : ListView.builder(
                          scrollDirection: Axis.horizontal,
                          shrinkWrap: true,
                          itemCount: _repasList.length,
                          itemBuilder: (context, index) {
                            return RepasCard(
                              nom: _repasList[index].nom,
                              description: _repasList[index].description,
                              price: _repasList[index].price,
                              rating: _repasList[index].rating,
                              image: api_services.baseImageUrlRepas + _repasList[index].image,
                              onTap: () {
                                double totalAmound = 0;
                                int quantity = 0;
                                bool isfavorite = false;
                                showModalBottomSheet(
                                    context: context,
                                    shape: const RoundedRectangleBorder(
                                      borderRadius: BorderRadius.vertical(top: Radius.circular(25.0)),
                                    ),
                                    builder: (context){
                                      return Container(
                                        decoration:  const BoxDecoration(
                                            borderRadius:  BorderRadius.only(
                                                topLeft: Radius.circular(100),
                                                topRight: Radius.circular(130)
                                            )
                                        ),
                                        padding: EdgeInsets.symmetric(horizontal: size.width *0.05),
                                        child: Column(
                                          children: [
                                            //Boutton en haut a droite
                                            Align(
                                                alignment: Alignment.topRight,
                                                child: Container(
                                                  width: size.width * 0.2,
                                                  child:ClipRRect(
                                                    borderRadius: BorderRadius.circular(7),
                                                    child:FlatButton(
                                                      padding: EdgeInsets.symmetric(vertical: 3, horizontal: 3),
                                                      color: kPrimaryColor,
                                                      onPressed: () {},
                                                      child:  const Text(
                                                        "Non Veg",
                                                        style: TextStyle(color: Colors.white),
                                                      ),
                                                    ) ,
                                                  ) ,
                                                )
                                            ),
                                            //in formations sur le restaurant
                                            Row(
                                              children: [
                                                Container(
                                                  height: size.width * 0.3,
                                                  width: size.width * 0.3,
                                                  decoration: BoxDecoration(
                                                    color: Colors.black,
                                                    borderRadius: BorderRadius.circular(10),
                                                    image: DecorationImage(
                                                      image: NetworkImage(api_services.baseImageUrlRepas+_repasList[index].image),
                                                      fit: BoxFit.cover,
                                                    ),
                                                  ),
                                                ),
                                                //nom du repas
                                                Column(
                                                  children:  [
                                                     Align(
                                                      alignment: Alignment.centerLeft,
                                                      child: Text(
                                                        _repasList[index].nom,
                                                        style: const TextStyle(
                                                            fontWeight: FontWeight.bold,
                                                            fontSize: 30,
                                                            color: Colors.black
                                                        ),
                                                      ),
                                                    ),
                                                    //description du repas
                                                    Text(
                                                      _repasList[index].description,
                                                      style: const TextStyle(
                                                          fontSize: 20,
                                                          color: kPrimaryColor
                                                      ),
                                                    ),
                                                    //rating du repas
                                                    SmoothStarRating(
                                                        allowHalfRating: false,
                                                        onRated: (v) {
                                                        },
                                                        starCount: 5,
                                                        rating: _repasList[index].rating,
                                                        size: 30.0,
                                                        isReadOnly:true,
                                                        color: Colors.orange,
                                                        borderColor: Colors.blueGrey,
                                                        spacing:0.0
                                                    ),
                                                    Text(
                                                      '${_repasList[index].price} XAF',
                                                      style: const TextStyle(
                                                          fontWeight: FontWeight.bold,
                                                          fontSize: 25,
                                                          color: Colors.black
                                                      ),
                                                    ),
                                                  ],
                                                ),
                                                SizedBox(width: size.height *0.02),
                                                IconButton(
                                                    onPressed: (){

                                                    },
                                                    icon:  const Icon(
                                                      Icons.favorite_border,
                                                      color: kPrimaryColor,
                                                      size: 38,
                                                    )
                                                ),

                                              ],
                                            ),
                                            //le Texte "description de repas"
                                            SizedBox(height: size.height * 0.013,),
                                            const Align(
                                              alignment: Alignment.centerLeft,
                                              child: Text(
                                                "Description",
                                                style: TextStyle(
                                                    fontWeight: FontWeight.bold,
                                                    fontSize: 25,
                                                    color: Colors.black
                                                ),
                                              ),
                                            ),
                                            SizedBox(height: size.height * 0.013,),
                                            //la description du repas
                                            const Align(
                                              alignment: Alignment.centerLeft,
                                              child: Text(
                                                "Un plat 100% camerounais qui vous ferras sourrir de plaisir et de bonne humeure",
                                                style: TextStyle(
                                                    fontWeight: FontWeight.bold,
                                                    fontSize: 18,
                                                    color: Colors.grey
                                                ),
                                              ),
                                            ),
                                            SizedBox(height: size.height * 0.013,),
                                            //la quantité
                                            Row(
                                              children:  [
                                                const Align(
                                                  alignment: Alignment.centerLeft,
                                                  child: Text(
                                                    "Quantity",
                                                    style: TextStyle(
                                                        fontWeight: FontWeight.bold,
                                                        fontSize: 18,
                                                        color: Colors.grey
                                                    ),
                                                  ),
                                                ),
                                                SizedBox(width: size.width * 0.4,),
                                                //augmenter diminuer quantité
                                                Align(

                                                  alignment: Alignment.centerRight,
                                                  child: Row(
                                                    children: [
                                                      //boutton moin
                                                      IconButton(
                                                          onPressed: (){
                                                            setState((){
                                                              quantity --;
                                                              totalAmound = CalculTotalAmound(
                                                                  quantity,
                                                                  _repasList[index].price
                                                              );
                                                            });

                                                          },
                                                          icon: const Icon(Icons.remove_circle,
                                                            color: Colors.blueGrey,)
                                                      ),
                                                      Text(
                                                        quantity.toString(),
                                                        style: const TextStyle(
                                                            fontWeight: FontWeight.bold,
                                                            fontSize: 18,
                                                            color: Colors.black
                                                        ),
                                                      ),
                                                      //boutton plus
                                                      IconButton(
                                                          onPressed: (){
                                                            quantity --;
                                                            totalAmound = CalculTotalAmound(
                                                                quantity,
                                                                _repasList[index].price
                                                            );
                                                          },
                                                          icon: const Icon(Icons.add_circle,
                                                            color: kPrimaryColor,)
                                                      ),
                                                    ],
                                                  )
                                                ),
                                              ],
                                            ),
                                            SizedBox(height: size.height * 0.013,),
                                            //total Amound
                                            Row(
                                              children:   [
                                                const Align(
                                                  alignment: Alignment.centerLeft,
                                                  child: Text(
                                                    "ToTal Amount",
                                                    style: TextStyle(
                                                        fontWeight: FontWeight.bold,
                                                        fontSize: 18,
                                                        color: Colors.grey
                                                    ),
                                                  ),
                                                ),
                                                Align(
                                                  alignment: Alignment.centerLeft,
                                                  child: Text(
                                                    " $totalAmound XAF",
                                                    style: const TextStyle(
                                                        fontWeight: FontWeight.bold,
                                                        fontSize: 18,
                                                        color: kPrimaryColor
                                                    ),
                                                  ),
                                                ),
                                              ],
                                            ),
                                            SizedBox(height: size.height * 0.013,),
                                            //bouttons du bas (Add to card)
                                            Row(
                                              children: [
                                                Align(
                                                    alignment: Alignment.centerRight,
                                                    child: Material(
                                                      // needed
                                                      color: Colors.transparent,
                                                      child: InkWell(
                                                        borderRadius: BorderRadius.circular(7),
                                                        onTap: () {

                                                        }, // needed
                                                        child: Image.asset(
                                                          "assets/icons/ic_market.png",
                                                          width: size.width * 0.13,
                                                          height: size.width * 0.13,
                                                          fit: BoxFit.cover,
                                                        ),
                                                      ),
                                                    )
                                                ),
                                                SizedBox(width: size.width * 0.03,),
                                                Align(
                                                    alignment: Alignment.centerRight,
                                                    child: Container(
                                                      width: size.width * 0.7,
                                                      height: size.width * 0.13,
                                                      child:ClipRRect(
                                                        borderRadius: BorderRadius.circular(7),
                                                        child:FlatButton(
                                                          padding: EdgeInsets.symmetric(vertical: 3, horizontal: 3),
                                                          color: kPrimaryColor,
                                                          onPressed: () {
                                                            if(api_services.myCart.contains(_repasList[index])){
                                                              Navigator.pop(context);
                                                            }
                                                            else {
                                                              api_services
                                                                  .myCart.add(
                                                                  _repasList[index]);
                                                              Navigator.pop(context);
                                                            }
                                                          },
                                                          child:  const Text(
                                                            "Add To Cart",
                                                            style: TextStyle(
                                                                color: Colors.white,
                                                                fontWeight: FontWeight.bold
                                                            ),
                                                          ),
                                                        ) ,
                                                      ) ,
                                                    )
                                                ),
                                              ],
                                            )
                                          ],
                                        ),
                                      );
                                });

                                print(_repasList[index]);
                              },
                            );
                          }
                      ),
                    ),
                    const Text(
                      "New Restaurants ",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                          fontSize: 20
                      ),
                    ),
                    SizedBox(height: size.height *0.01),
                    Container(
                      height: 300.0,

                      child: _isLoading
                          ? const Center(child: CircularProgressIndicator())
                          : ListView.builder(
                          scrollDirection: Axis.horizontal,
                          shrinkWrap: true,
                          itemCount: _recipes.length,
                          itemBuilder: (context, index) {
                            return RestaurantNewCard(
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
                          }
                      ),

                    ),
                    Container(
                      height: 200.0,
                      child: DecoratedBox(
                        decoration: BoxDecoration(color: Colors.green),
                      ),
                    ),
                    Container(
                      height: 200.0,
                      child: DecoratedBox(
                        decoration: BoxDecoration(color: Colors.blue),
                      ),
                    ),
                  ],
                )
            )
          ]
          )
      ),
    );
  }
  double CalculTotalAmound(int quantity , double pU)
  {
    return pU* quantity;
  }
}
