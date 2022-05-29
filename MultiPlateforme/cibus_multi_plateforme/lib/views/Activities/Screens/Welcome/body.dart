import 'package:cibus_multi_plateforme/views/Activities/Screens/Signup/signup_screen.dart';
import 'package:flutter/material.dart';
import 'package:cibus_multi_plateforme/views/widgets/components/background.dart';
import 'package:flutter_svg/svg.dart';
import 'package:cibus_multi_plateforme/components/rounded_button.dart';

import '../../../../constants.dart';
import '../Login/LoginScreen.dart';

class Body extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    //this size provide us to total height and with of our screen
    return Background(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          const Text(
            "WELCOME TO CIBUS",
            style: TextStyle(fontWeight: FontWeight.bold),
          ),
          SizedBox(height: size.height * 0.03),
          SvgPicture.asset(
            "assets/icons/chat.svg",
            height: size.height * 0.45,
          ),
          SizedBox(height: size.height * 0.03),
          RoundedButton(
            text: "LOGIN",
            press: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                    builder: (context) {
                      return LoginScreen();
                    },
                  ),
            );
            },
          ),
          SizedBox(height: size.height * 0.03),
          RoundedButton(
            text: "SING UP",
              press: (){
                Navigator.push(context,
                    MaterialPageRoute(
                        builder: (context){
                          return SignUpScreen();
                        })
                );
              },
            color: kPrimaryLightColor,
            textColor: Colors.black,
          ),
        ],
      ),
    );
  }
}


