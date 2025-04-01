import 'package:flutter/material.dart';

class InvestmentPlannerScreen extends StatefulWidget {
  @override
  _InvestmentPlannerScreenState createState() => _InvestmentPlannerScreenState();
}

class _InvestmentPlannerScreenState extends State<InvestmentPlannerScreen> {
  final TextEditingController _investmentController = TextEditingController();
  final TextEditingController _yearsController = TextEditingController();
  double _interestRate = 5.0;
  double _futureValue = 0.0;

  void _calculateInvestment() {
    final double investment = double.tryParse(_investmentController.text) ?? 0.0;
    final int years = int.tryParse(_yearsController.text) ?? 1;
    final double rate = _interestRate / 100;

    setState(() {
      _futureValue = investment * (1 + rate * years);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Investment Planner',
          style: TextStyle(
            fontWeight: FontWeight.w900,
            fontSize: 24,
            color: Colors.white,
            shadows: [
              Shadow(blurRadius: 4.0, color: Colors.black54, offset: Offset(2, 2)),
            ],
          ),
        ),
        backgroundColor: Colors.purple.shade700,
        elevation: 5,
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.purple.shade400, Colors.purple.shade900],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        padding: EdgeInsets.all(16),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            _buildStyledTextField('Initial Investment', _investmentController, Icons.attach_money),
            SizedBox(height: 16),
            _buildStyledTextField('Number of Years', _yearsController, Icons.calendar_today),
            SizedBox(height: 20),
            _buildSlider(),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _calculateInvestment,
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.deepPurple.shade700,
                padding: EdgeInsets.symmetric(vertical: 12, horizontal: 40),
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                elevation: 6,
              ),
              child: Text('Calculate', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white)),
            ),
            SizedBox(height: 20),
            Text(
              'Future Value:',
              style: TextStyle(fontSize: 18, color: Colors.white70),
            ),
            Text(
              '\$${_futureValue.toStringAsFixed(2)}',
              style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Colors.white),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildStyledTextField(String label, TextEditingController controller, IconData icon) {
    return TextField(
      controller: controller,
      decoration: InputDecoration(
        labelText: label,
        labelStyle: TextStyle(color: Colors.white70, fontSize: 16),
        filled: true,
        fillColor: Colors.white.withOpacity(0.2),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: Colors.white38),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: Colors.white),
        ),
        prefixIcon: Icon(icon, color: Colors.white70),
      ),
      style: TextStyle(color: Colors.white, fontSize: 18),
      keyboardType: TextInputType.number,
    );
  }

  Widget _buildSlider() {
    return Column(
      children: [
        Text(
          'Interest Rate: ${_interestRate.toStringAsFixed(1)}%',
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold, color: Colors.white70),
        ),
        Slider(
          value: _interestRate,
          min: 1,
          max: 20,
          divisions: 19,
          activeColor: Colors.deepPurpleAccent,
          label: '${_interestRate.toStringAsFixed(1)}%',
          onChanged: (value) {
            setState(() {
              _interestRate = value;
            });
          },
        ),
      ],
    );
  }
}
