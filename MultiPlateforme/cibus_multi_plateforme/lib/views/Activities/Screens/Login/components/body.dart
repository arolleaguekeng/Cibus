
import 'package:cibus_multi_plateforme/components/already_have_account_check.dart';
import 'package:cibus_multi_plateforme/components/rounded_button.dart';
import 'package:cibus_multi_plateforme/components/rounded_input_field.dart';
import 'package:cibus_multi_plateforme/components/rounded_password_field.dart';
import 'package:cibus_multi_plateforme/components/text_field_container.dart';
import 'package:cibus_multi_plateforme/constants.dart';
import 'package:cibus_multi_plateforme/views/Activities/Screens/Login/components/background.dart';
import 'package:cibus_multi_plateforme/views/Activities/Screens/Signup/signup_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
class Body extends StatelessWidget {
  const Body({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
Size size = MediaQuery.of(context).size;
    return Background(
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children:  <Widget>[
            const Text("LOGIN",
              style:TextStyle(fontWeight: FontWeight.bold),
            ),
            SizedBox(height: size.height * 0.03,),
            SvgPicture.asset(
              "assets/icons/login.svg",
              height: size.height * 0.35,
            ),
            SizedBox(height: size.height * 0.03,),
            RoundedInputField(
              hintText: "Your Email" ,
              onChanged: (value) {},
            ),
            SizedBox(height: size.height * 0.03,),
            RoundedPasswordField(
                onChanged: (value) {}
            ),
            SizedBox(height: size.height * 0.03,),
            RoundedButton(
                text: "LOGIN",
                press: (){}
            ),
            SizedBox(height: size.height * 0.03,),
            AlreadyHaveAccountCheck(
              press: (){
                Navigator.push(context,
                    MaterialPageRoute(
                        builder: (context){
                          return SignUpScreen();
                        })
                );
              },
            ),
          ],
        ),
      ),

    );
  }
}









