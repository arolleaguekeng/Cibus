import 'package:cibus_multi_plateforme/components/already_have_account_check.dart';
import 'package:cibus_multi_plateforme/components/rounded_button.dart';
import 'package:cibus_multi_plateforme/components/rounded_input_field.dart';
import 'package:cibus_multi_plateforme/components/rounded_password_field.dart';
import 'package:cibus_multi_plateforme/constants.dart';
import 'package:cibus_multi_plateforme/views/Activities/Screens/Login/LoginScreen.dart';
import 'package:cibus_multi_plateforme/views/Activities/Screens/Signup/background.dart';
import 'package:cibus_multi_plateforme/views/Activities/Screens/Signup/or_divider.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';

import 'components/social_icon.dart';

class Body extends StatelessWidget{
  const Body({
    super.key,
  });
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Background(
      child: SingleChildScrollView(
        child: Column(
          children:  <Widget>[
            const Text(
              "SIGN UP",
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
            SizedBox(height: size.height *0.35),
            SvgPicture.asset(
              "assets/icons/signup.svg",
              height: size.height * 0.35,
            ),
            SizedBox(height: size.height *0.35),
            RoundedInputField(
              hintText: "Your Email",
              onChanged: (value) {},
            ),
            SizedBox(height: size.height *0.35),
            RoundedPasswordField(
              onChanged: (value) {},
            ),
            SizedBox(height: size.height *0.35),
            RoundedButton(text: "SINGUP", press: () {}),
            AlreadyHaveAccountCheck(
              login: false,
              press: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context){
                          return LoginScreen();
                        })
                );
              },
            ),
            OrDivider(),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children:  <Widget>[
                SocialIcon(
                  iconSrc: "assets/icons/faceboock.svg",
                  press: () {},
                ),
                SocialIcon(
                  iconSrc: "assets/icons/twitter.svg",
                  press: () {},
                ),
                SocialIcon(
                  iconSrc: "assets/icons/google-plus.svg",
                  press: () {},
                ),
              ],
            )
          ],
        ),
      )

    );
  }

}




