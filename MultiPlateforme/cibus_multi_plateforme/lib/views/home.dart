import 'package:cibus_multi_plateforme/constants.dart';
import 'package:cibus_multi_plateforme/models/api/restaurant.api.dart';
import 'package:cibus_multi_plateforme/views/Activities/chat.dart';
import 'package:cibus_multi_plateforme/views/Activities/profile.dart';
import 'package:cibus_multi_plateforme/views/Activities/restaurant_list.dart';
import 'package:cibus_multi_plateforme/views/Activities/setting.dart';
import 'package:cibus_multi_plateforme/views/dashboard.dart';
import 'package:cibus_multi_plateforme/views/widgets/bottom_navigationBar.dart';
import 'package:cibus_multi_plateforme/views/widgets/my_cart_page.dart';
import 'package:cibus_multi_plateforme/views/widgets/restaurant_card.dart';
import 'package:flutter/material.dart';

import '../models/restaurant.dart';
import 'Activities/restaurant_details.dart';
import 'widgets/restaurant_home_card.dart';




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

  int currentTab = 0;
  final List<Widget> screens = [
    Dashboard(),
    Chat(),
    Profile(),
    Setting()
  ];
  AppBar? appBar = null;

  final PageStorageBucket bucket = PageStorageBucket();
  Widget currentScreen = Dashboard();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: PageStorage(
          child: currentScreen,
          bucket: bucket,
        ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.shopping_cart,color: Colors.black45,),
        onPressed: () {
          // Navigator.push(context, MaterialPageRoute(builder: (context)=>RestaurantDetails(_recipes[index]))
          Navigator.push(context, MaterialPageRoute(builder: (context)=> MyCartPage()));
        },
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: BottomAppBar(
        shape: CircularNotchedRectangle(),
        notchMargin: 10,
        child: Container(
          height: 60,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: <Widget>[
              Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  MaterialButton(
                    minWidth: 60,
                      onPressed: () {
                        setState((){
                          currentScreen = Dashboard();
                          currentTab = 0;
                        },

                        );

                      },
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(
                              Icons.dashboard,
                              color: currentTab ==0
                                  ? kPrimaryColor
                                  : Colors.grey
                          ),
                          Text(
                              'Home',
                            style: TextStyle(
                                color: currentTab == 0
                                    ? kPrimaryColor
                                    : Colors.grey
                            ),
                          )
                        ],
                      )
                  ),
                ],
              ),
              //Right Tab bar icons
              Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  MaterialButton(
                      minWidth: 60,
                      onPressed: () {
                        setState((){
                          currentScreen = RestaurantList();
                          currentTab = 2;
                        },

                        );

                      },
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(
                              Icons.bookmark_sharp,
                              color: currentTab ==2
                                  ? kPrimaryColor
                                  : Colors.grey
                          ),
                          Text(
                            'Market',
                            style: TextStyle(
                                color: currentTab == 2
                                    ? kPrimaryColor
                                    : Colors.grey
                            ),
                          )
                        ],
                      )
                  ),
                ],
              )
            ],
          ),
        ),

      ),
    );
  }
  // Widget getRestaurants(){
  //   var size = MediaQuery.of(context).size;
  //   return ListView(
  //     padding: EdgeInsets.zero,
  //     children: [
  //       Stack(),
  //       SizedBox(
  //         height: 40,
  //   )
  //
  //       )
  //     ],
  //   )
  // }


}



// _isLoading
// ? const Center(child: CircularProgressIndicator())
// : ListView.builder(
// scrollDirection: Axis.horizontal,
// itemCount: _recipes.length,
// itemBuilder: (context, index) {
// return RestaurantHomeCard(
// nom: _recipes[index].nom,
// description: _recipes[index].description,
// cookTime: _recipes[index].description,
// rating: _recipes[index].rating.toString(),
// thumbnailUrl: baseUrl + _recipes[index].image,
// onTap: () {
// Navigator.push(context, MaterialPageRoute(builder: (context)=>RestaurantDetails(_recipes[index]))
// );
// print(_recipes[index]);
// },
// );
//
// },
//
// ),
// bottomNavigationBar: BottomNavigationCibus() ,


