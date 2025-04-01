import 'package:flutter/material.dart';

class BillSplitterScreen extends StatefulWidget {
  @override
  _BillSplitterScreenState createState() => _BillSplitterScreenState();
}

class _BillSplitterScreenState extends State<BillSplitterScreen> {
  final TextEditingController _amountController = TextEditingController();
  final TextEditingController _peopleController = TextEditingController();
  double _splitAmount = 0.0;

  void _calculateSplit() {
    double totalAmount = double.tryParse(_amountController.text) ?? 0.0;
    int people = int.tryParse(_peopleController.text) ?? 1;

    if (people > 0) {
      setState(() {
        _splitAmount = totalAmount / people;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Bill Splitter',
          style: TextStyle(
            fontWeight: FontWeight.w900,
            fontSize: 24,
            color: Colors.white,
            shadows: [
              Shadow(blurRadius: 4.0, color: Colors.black54, offset: Offset(2, 2)),
            ],
          ),
        ),
        backgroundColor: Colors.orange.shade600, // Lighter than before
        elevation: 5,
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.orange.shade300, Colors.orange.shade700], // Softer shades
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        padding: EdgeInsets.all(16),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            _buildStyledTextField('Total Amount', _amountController, Icons.attach_money),
            SizedBox(height: 16),
            _buildStyledTextField('Number of People', _peopleController, Icons.group),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _calculateSplit,
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.orange.shade500, // Softer shade
                padding: EdgeInsets.symmetric(vertical: 12, horizontal: 40),
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                elevation: 6,
              ),
              child: Text('Split Bill', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white)),
            ),
            SizedBox(height: 20),
            Text(
              'Each Person Pays:',
              style: TextStyle(fontSize: 18, color: Colors.white70),
            ),
            Text(
              '\$${_splitAmount.toStringAsFixed(2)}',
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
        fillColor: Colors.white.withOpacity(0.15), // Slightly less bright
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: Colors.white30), // Softer contrast
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
}
