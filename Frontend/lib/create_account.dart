import 'package:flutter/material.dart';
//import 'package:google_fonts/google_fonts.dart';

class CreateAccountPage extends StatelessWidget {
  const CreateAccountPage({super.key});

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Container(
        color: Colors.white,
        child: Scaffold(
          body: LayoutBuilder(
            builder: (context, constraints) {
              return SingleChildScrollView(
                child: Column(
                  children: [
                    const SizedBox(height: 20),
                    Row(
                      children: [
                        const SizedBox(width: 12),
                        IconButton(
                          onPressed: () => Navigator.pop(context),
                          icon: const Icon(
                            Icons.arrow_back_ios_new,
                            color: Color.fromARGB(255, 231, 151, 150),
                          ),
                        ),
                      ],
                    ),
                    Row(
                      children: [
                        SizedBox(width: constraints.maxWidth * 0.07),
                        SizedBox(
                          width: constraints.maxWidth * 0.86,
                          child: Column(
                            children: [
                              const SizedBox(height: 15),
                              SizedBox(height: constraints.maxHeight * 0.05),
                              const Row(
                                children: [
                                  Text(
                                    "Create Your\nAccount!",
                                    style: TextStyle(
                                        fontFamily: 'GmarketSans',
                                        color:
                                            Color.fromARGB(255, 231, 151, 150),
                                        fontSize: 35,
                                        fontWeight: FontWeight.bold,
                                        height: 1.2),
                                  ),
                                ],
                              ),
                              //spacing between create_your_account and username
                              SizedBox(height: constraints.maxHeight * 0.05),
                              //USERNAME
                              const Row(
                                children: [
                                  Text(
                                    "Username",
                                    textAlign: TextAlign.left,
                                    style: TextStyle(
                                      fontFamily: 'NotoSans',
                                      color: Color.fromARGB(255, 231, 151, 150),
                                      fontSize: 14,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 9),
                              TextFormField(
                                decoration: InputDecoration(
                                  contentPadding: const EdgeInsets.all(5),
                                  border: OutlineInputBorder(
                                    borderSide: const BorderSide(
                                        color: Colors.grey, width: 0.5),
                                    borderRadius: BorderRadius.circular(10.0),
                                  ),
                                  label: const Text('  Type Here'),
                                ),
                                validator: (value) {
                                  if (value == null ||
                                      value.isEmpty ||
                                      value.trim().length < 4 ||
                                      value.trim().length > 10) {
                                    return 'Your username must be between 4 and 10 characters.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  //_enteredName = value!;
                                },
                              ),
                              const SizedBox(height: 15),
                              //E-MAIL
                              const Row(
                                children: [
                                  Text(
                                    "E-mail",
                                    textAlign: TextAlign.left,
                                    style: TextStyle(
                                      fontFamily: 'NotoSans',
                                      color: Color.fromARGB(255, 231, 151, 150),
                                      fontSize: 14,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 9),
                              TextFormField(
                                decoration: InputDecoration(
                                  contentPadding: const EdgeInsets.all(5),
                                  border: OutlineInputBorder(
                                    borderSide: const BorderSide(
                                        color: Colors.grey, width: 0.5),
                                    borderRadius: BorderRadius.circular(10.0),
                                  ),
                                  label: const Text('  Type Here'),
                                ),
                                validator: (value) {
                                  if (value == null ||
                                      value.isEmpty ||
                                      value.trim().length < 4 ||
                                      value.trim().length > 10) {
                                    return 'Your username must be between 4 and 10 characters.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  //_enteredName = value!;
                                },
                              ),
                              const SizedBox(height: 15),
                              //PASSWORD
                              const Row(
                                children: [
                                  Text(
                                    "Password",
                                    textAlign: TextAlign.left,
                                    style: TextStyle(
                                      fontFamily: 'NotoSans',
                                      color: Color.fromARGB(255, 231, 151, 150),
                                      fontSize: 14,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 9),
                              TextFormField(
                                decoration: InputDecoration(
                                  contentPadding: const EdgeInsets.all(5),
                                  border: OutlineInputBorder(
                                    borderSide: const BorderSide(
                                        color: Colors.grey, width: 0.5),
                                    borderRadius: BorderRadius.circular(10.0),
                                  ),
                                  label: const Text('  Type Here'),
                                ),
                                validator: (value) {
                                  if (value == null ||
                                      value.isEmpty ||
                                      value.trim().length < 4 ||
                                      value.trim().length > 10) {
                                    return 'Your username must be between 4 and 10 characters.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  //_enteredName = value!;
                                },
                              ),
                              const SizedBox(height: 15),
                              //CONFIRM PASSWORD
                              const Row(
                                children: [
                                  Text(
                                    "Confirm Password",
                                    textAlign: TextAlign.left,
                                    style: TextStyle(
                                      fontFamily: 'NotoSans',
                                      color: Color.fromARGB(255, 231, 151, 150),
                                      fontSize: 14,
                                      fontWeight: FontWeight.w900,
                                    ),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 9),
                              TextFormField(
                                decoration: InputDecoration(
                                  contentPadding: const EdgeInsets.all(5),
                                  border: OutlineInputBorder(
                                    borderSide: const BorderSide(
                                        color: Colors.grey, width: 0.5),
                                    borderRadius: BorderRadius.circular(10.0),
                                  ),
                                  label: const Text('  Type Here'),
                                ),
                                validator: (value) {
                                  if (value == null ||
                                      value.isEmpty ||
                                      value.trim().length < 4 ||
                                      value.trim().length > 10) {
                                    return 'Your username must be between 4 and 10 characters.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  //_enteredName = value!;
                                },
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              );
            },
          ),
        ),
      ),
    );
  }
}
