import 'package:cibus_multi_plateforme/constants.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
class BottomNavigationCibus extends StatefulWidget {
  const BottomNavigationCibus({Key? key}) : super(key: key);

  @override
  State<BottomNavigationCibus> createState() => _BottomNavigationCibusState();
}

class _BottomNavigationCibusState extends State<BottomNavigationCibus> {
  int _selectedIndex = 0;
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 64,
      decoration: BoxDecoration(
        color: kPrimaryLightColor,
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.3),
            spreadRadius: 2,
            blurRadius: 15,
            offset: Offset(0,5)
          )
        ],
        borderRadius: const BorderRadius.only(
          topLeft: Radius.circular(24),
          topRight: Radius.circular(24),
        )
      ),
      // child: BottomNavigationBar(
      //   items: <BottomNavigationBarItem>[
      //   BottomNavigationBarItem(
      //     icon: Icons.home,
      //     title: "home"
      //   )
      // ],
      // ),
    );
  }
}
