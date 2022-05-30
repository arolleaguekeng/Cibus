import 'package:cibus_multi_plateforme/views/home.dart';
import 'package:flutter/material.dart';
import 'package:cibus_multi_plateforme/views/Activities/Screens/Welcome/welcome_sreen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Cibus',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.orange,
        secondaryHeaderColor: Colors.white,
        textTheme: const TextTheme(
          bodyText2: TextStyle(color: Colors.white)
        )
      ),
      home:  HomePage(),
    );
  }
}


