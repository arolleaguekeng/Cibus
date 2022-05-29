import 'package:cibus_multi_plateforme/constants.dart';
import 'package:flutter/material.dart';

class AlreadyHaveAccountCheck extends StatelessWidget {
  final bool login;
  final VoidCallback press;
  const AlreadyHaveAccountCheck({
    Key? key,
    this.login = true,
    required this.press,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children:  <Widget>[
        Text(
          login ?
          "Don't have Account ?": "Already have Account ?",
          style: const TextStyle(color: kPrimaryColor),
        ),
        GestureDetector(
            onTap: press,
            child:  Text(
              login ? "Sign UP" : "Sign In",
              style: const TextStyle(
                  color: kPrimaryColor,
                  fontWeight: FontWeight.bold
              ),
            )
        )
      ],
    );
  }
}