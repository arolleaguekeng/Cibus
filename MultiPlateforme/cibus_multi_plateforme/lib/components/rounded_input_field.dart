import 'package:cibus_multi_plateforme/components/text_field_container.dart';
import 'package:flutter/material.dart';

import '../constants.dart';

class RoundedInputField extends StatelessWidget {
  final TextEditingController? controller;
  final String hintText;
  final IconData icon;
  final ValueChanged<String> onChanged;
  const RoundedInputField({
    Key? key,
    required this.hintText,
    this.icon = Icons.person,
    required this.onChanged,
    required this.controller,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return  TextFielContainer(
      child: TextField(
        controller: controller,
        onChanged: onChanged,
        decoration:
        InputDecoration(
            icon:  Icon(
              icon,
              color: kPrimaryColor,
            ),
            hintText: hintText,
            border: InputBorder.none
        ),
      ),
    );
  }
}