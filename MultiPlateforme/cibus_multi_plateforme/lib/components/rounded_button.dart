import 'package:cibus_multi_plateforme/constants.dart';
import 'package:flutter/material.dart';
class RoundedButton extends StatelessWidget{
  final String text;
  final VoidCallback press;
  final Color color, textColor;
  const RoundedButton({
        super.key,
        required this.text,
        required this.press,
        this.color = kPrimaryColor,
        this.textColor = Colors.white
      });
  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Container(
      width: size.width * 0.8,
      child:ClipRRect(
        borderRadius: BorderRadius.circular(29),
        child:FlatButton(
          padding: EdgeInsets.symmetric(vertical: 20, horizontal: 40),
          color: color,
          onPressed: press,
          child:  Text(
            text,
            style: TextStyle(color: textColor),
          ),
        ) ,
      ) ,
    );
  }

}