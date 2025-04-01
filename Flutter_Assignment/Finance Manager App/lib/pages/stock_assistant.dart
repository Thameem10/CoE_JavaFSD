import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class StockAssistantScreen extends StatefulWidget {
  @override
  _StockAssistantScreenState createState() => _StockAssistantScreenState();
}

class _StockAssistantScreenState extends State<StockAssistantScreen> {
  List<dynamic> stockData = [];
  bool isLoading = true;
  String errorMessage = '';

  @override
  void initState() {
    super.initState();
    fetchStockData();
  }

  Future<void> fetchStockData() async {
    final url = Uri.parse(
        'https://financialmodelingprep.com/api/v3/stock/actives?apikey=iL8TmY6OvmvTm460I0cBkhz1vOH5unec');

    try {
      final response = await http.get(url);

      if (response.statusCode == 200) {
        final data = json.decode(response.body);

        setState(() {
          stockData = data['mostActiveStock'] ?? [];
          isLoading = false;
        });
      } else {
        setState(() {
          errorMessage = 'Failed to load stock data (${response.statusCode})';
          isLoading = false;
        });
      }
    } catch (e) {
      setState(() {
        errorMessage = 'Error: $e';
        isLoading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Stock Market Assistant',
          style: TextStyle(
            fontWeight: FontWeight.w900,
            fontSize: 24,
            color: Colors.white,
            shadows: [
              Shadow(blurRadius: 4.0, color: Colors.black54, offset: Offset(2, 2)),
            ],
          ),
        ),
        backgroundColor: Colors.blue.shade700,
        elevation: 5,
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.blue.shade400, Colors.blue.shade900],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        child: isLoading
            ? Center(child: CircularProgressIndicator(color: Colors.white))
            : errorMessage.isNotEmpty
                ? Center(
                    child: Text(
                      errorMessage,
                      style: TextStyle(color: Colors.redAccent, fontSize: 16, fontWeight: FontWeight.bold),
                      textAlign: TextAlign.center,
                    ),
                  )
                : ListView.builder(
                    padding: EdgeInsets.all(16.0),
                    itemCount: stockData.length,
                    itemBuilder: (context, index) {
                      var stock = stockData[index];
                      double price = double.tryParse(stock['price'] ?? '0') ?? 0.0;
                      double changePercentage = double.tryParse(stock['changesPercentage'] ?? '0') ?? 0.0;

                      return Card(
                        color: Colors.white.withOpacity(0.95),
                        elevation: 6,
                        margin: EdgeInsets.symmetric(vertical: 8.0),
                        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                        child: ListTile(
                          contentPadding: EdgeInsets.all(12),
                          title: Text(
                            stock['companyName'] ?? 'Unknown',
                            style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.black87),
                          ),
                          subtitle: Text(
                            '\$${price.toStringAsFixed(2)}',
                            style: TextStyle(fontSize: 16, color: Colors.blue.shade700, fontWeight: FontWeight.bold),
                          ),
                          trailing: Text(
                            '${changePercentage.toStringAsFixed(2)}%',
                            style: TextStyle(
                              color: changePercentage >= 0 ? Colors.green : Colors.red,
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
