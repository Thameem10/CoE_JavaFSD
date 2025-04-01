import 'package:flutter/material.dart';
import 'crypto_page.dart';
import 'bill_splitter.dart';
import 'stock_assistant.dart';
import 'investment_planner.dart';
import 'freelancer_tracker.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
      title: Text(
        'Finance Manager',
        style: TextStyle(
          fontWeight: FontWeight.w900, // Extra Bold
          fontSize: 24, // Larger size
          color: Colors.white, // Ensures visibility
          shadows: [
          Shadow(
            blurRadius: 4.0,
            color: Colors.black.withOpacity(0.3),
            offset: Offset(2, 2),
          ),
        ],
      ),
    ),
    backgroundColor: Colors.deepPurple,
    elevation: 5,
    shadowColor: Colors.black45,
    ),
      
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.deepPurple.shade300, Colors.deepPurple.shade800],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: GridView.count(
            crossAxisCount: 2,
            crossAxisSpacing: 16,
            mainAxisSpacing: 16,
            children: [
              _buildFeatureCard(context, 'Crypto Tracker', Icons.monetization_on, Colors.teal, CryptoScreen()),
              _buildFeatureCard(context, 'Bill Splitter', Icons.group, Colors.orange, BillSplitterScreen()),
              _buildFeatureCard(context, 'Stock Assistant', Icons.trending_up, Colors.blue, StockAssistantScreen()),
              _buildFeatureCard(context, 'Savings Planner', Icons.savings, Colors.purple, InvestmentPlannerScreen()),
              _buildFeatureCard(context, 'Freelancer Tracker', Icons.work, Colors.red, FreelancerTrackerScreen()),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildFeatureCard(BuildContext context, String title, IconData icon, Color color, Widget screen) {
    return Card(
      elevation: 8,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
      color: Colors.white.withOpacity(0.9),
      shadowColor: Colors.black38,
      child: InkWell(
        onTap: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) => screen));
        },
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  gradient: LinearGradient(
                    colors: [color.withOpacity(0.5), color],
                    begin: Alignment.topLeft,
                    end: Alignment.bottomRight,
                  ),
                ),
                padding: EdgeInsets.all(15),
                child: Icon(icon, size: 40, color: Colors.white),
              ),
              SizedBox(height: 12),
              Text(
                title,
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                  color: Colors.black87,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
