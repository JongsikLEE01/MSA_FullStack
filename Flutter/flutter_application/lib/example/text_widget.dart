import 'package:flutter/material.dart';

class TextWidget extends StatelessWidget {
  const TextWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(
          child: Text(
        "집가고싶습니다",
        style: TextStyle(
            fontSize: 30.0, fontWeight: FontWeight.bold, color: Colors.red),
      )),
    );
  }
}
