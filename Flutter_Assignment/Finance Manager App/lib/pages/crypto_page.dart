import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class CryptoScreen extends StatefulWidget {
  @override
  _CryptoScreenState createState() => _CryptoScreenState();
}

class _CryptoScreenState extends State<CryptoScreen> {
  List<dynamic> cryptoData = [];
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    fetchCryptoData();
  }

  Future<void> fetchCryptoData() async {
    final url = Uri.parse('https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=false');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      setState(() {
        cryptoData = json.decode(response.body);
        isLoading = false;
      });
    } else {
      throw Exception('Failed to load crypto data');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Crypto Tracker',
          style: TextStyle(
            fontWeight: FontWeight.w900,
            fontSize: 24,
            color: Colors.white,
            shadows: [
              Shadow(blurRadius: 4.0, color: Colors.black54, offset: Offset(2, 2)),
            ],
          ),
        ),
        backgroundColor: Colors.teal.shade700,
        elevation: 5,
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.teal.shade400, Colors.teal.shade900],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        child: isLoading
            ? Center(child: CircularProgressIndicator(color: Colors.white))
            : ListView.builder(
                padding: EdgeInsets.all(16.0),
                itemCount: cryptoData.length,
                itemBuilder: (context, index) {
                  var crypto = cryptoData[index];
                  return Card(
                    color: Colors.white.withOpacity(0.9),
                    elevation: 6,
                    margin: EdgeInsets.symmetric(vertical: 8.0),
                    shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                    child: ListTile(
                      contentPadding: EdgeInsets.all(12),
                      leading: CircleAvatar(
                        backgroundColor: Colors.white,
                        backgroundImage: NetworkImage(crypto['image']),
                        radius: 22,
                      ),
                      title: Text(
                        crypto['name'],
                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.black87),
                      ),
                      subtitle: Text(
                        '\$${crypto['current_price'].toStringAsFixed(2)}',
                        style: TextStyle(fontSize: 16, color: Colors.teal.shade700, fontWeight: FontWeight.bold),
                      ),
                      trailing: Text(
                        '${crypto['price_change_percentage_24h'].toStringAsFixed(2)}%',
                        style: TextStyle(
                          color: crypto['price_change_percentage_24h'] >= 0 ? Colors.green : Colors.red,
                          fontWeight: FontWeight.bold,
                          fontSize: 16,
                        ),
                      ),
                    ),
                  );
                },
              ),
      ),
    );
  }
}
