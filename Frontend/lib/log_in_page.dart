import 'package:flutter/material.dart';

class Startpage extends StatelessWidget {
  const Startpage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [
              Color.fromARGB(255, 245, 206, 199),
              Color.fromARGB(255, 231, 151, 150),
            ],
          ),
        ),
        child: Expanded(
          child: Column(
            children: [
              const SizedBox(
                width: 5,
                height: 70,
              ),
              const Row(
                children: [
                  SizedBox(
                    height: 4,
                    width: 35,
                  ),
                  Text(
                    'Begin\nWeavin\'!',
                    style: TextStyle(
                      fontFamily: 'GmarketSans',
                      fontWeight: FontWeight.w600,
                      color: Colors.white,
                      fontSize: 60.0,
                      height: 1,
                    ),
                  ),
                ],
              ),
              SizedBox(
                width: MediaQuery.of(context).size.width,
                child: Image.asset(
                  'assets/images/X.png',
                  fit: BoxFit.contain,
                ),
              ),
              const SizedBox(
                height: 230,
              ),
              SizedBox(
                width: 330,
                height: 50,
                child: ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor:
                        MaterialStateProperty.all<Color>(Colors.white),
                  ),
                  onPressed: null,
                  child: const Text(
                    'SIGN IN',
                    style: TextStyle(
                      fontFamily: 'GmarketSans',
                      fontWeight: FontWeight.w600,
                      color: Color.fromARGB(255, 227, 153, 151),
                      fontSize: 15,
                    ),
                  ),
                ),
              ),
              const SizedBox(
                width: 1,
                height: 10,
              ),
              Container(
                decoration: BoxDecoration(
                  border: Border.all(
                    color: Colors.white,
                    width: 1.0,
                  ),
                  borderRadius: BorderRadius.circular(30.0),
                ),
                child: SizedBox(
                  width: 330,
                  height: 50,
                  child: ElevatedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all<Color>(
                        const Color.fromARGB(255, 222, 156, 154),
                      ),
                    ),
                    onPressed: null,
                    child: const Text(
                      'CREATE AN ACCOUNT',
                      style: TextStyle(
                        fontFamily: 'GmarketSans',
                        fontWeight: FontWeight.w600,
                        color: Colors.white,
                        fontSize: 15,
                      ),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
