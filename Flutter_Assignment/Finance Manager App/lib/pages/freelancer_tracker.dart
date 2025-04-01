import 'package:flutter/material.dart';

class FreelancerTrackerScreen extends StatefulWidget {
  @override
  _FreelancerTrackerScreenState createState() => _FreelancerTrackerScreenState();
}

class _FreelancerTrackerScreenState extends State<FreelancerTrackerScreen> {
  final List<Map<String, dynamic>> _invoices = [];
  final TextEditingController _clientController = TextEditingController();
  final TextEditingController _amountController = TextEditingController();
  final TextEditingController _dateController = TextEditingController();

  void _addInvoice() {
    final String client = _clientController.text;
    final double amount = double.tryParse(_amountController.text) ?? 0.0;
    final String date = _dateController.text;

    if (client.isNotEmpty && amount > 0 && date.isNotEmpty) {
      setState(() {
        _invoices.add({
          'client': client,
          'amount': amount,
          'date': date,
          'paid': false,
        });
      });
      _clientController.clear();
      _amountController.clear();
      _dateController.clear();
    }
  }

  void _togglePaid(int index) {
    setState(() {
      _invoices[index]['paid'] = !_invoices[index]['paid'];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Freelancer Payment Tracker',
          style: TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 22,
            color: Colors.white,
            shadows: [
              Shadow(blurRadius: 2, color: Colors.black26, offset: Offset(1, 1)),
            ],
          ),
        ),
        backgroundColor: Colors.pink.shade400, // Soft but rich pink
        elevation: 4,
      ),
      backgroundColor: Colors.pink.shade100, // Soft pastel pink background
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.pink.shade200, Colors.pink.shade100], // Gentle pink gradient
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
            _buildStyledTextField('Client Name', _clientController, Icons.person),
            SizedBox(height: 10),
            _buildStyledTextField('Amount (\$)', _amountController, Icons.attach_money),
            SizedBox(height: 10),
            _buildStyledTextField('Due Date', _dateController, Icons.calendar_today),
            SizedBox(height: 15),
            Center(
              child: ElevatedButton(
                onPressed: _addInvoice,
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.pink.shade400, // Gentle deep pink button
                  padding: EdgeInsets.symmetric(horizontal: 40, vertical: 12),
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
                  elevation: 4,
                ),
                child: Text(
                  'Add Invoice',
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.white),
                ),
              ),
            ),
            SizedBox(height: 15),
            Expanded(
              child: ListView.builder(
                itemCount: _invoices.length,
                itemBuilder: (context, index) {
                  final invoice = _invoices[index];
                  return Card(
                    elevation: 3,
                    color: Colors.pink.shade200.withOpacity(0.9), // Soft pink for readability
                    margin: EdgeInsets.symmetric(vertical: 8.0),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: ListTile(
                      title: Text(
                        invoice['client'],
                        style: TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                          color: Colors.pink.shade900, // Darker pink text
                        ),
                      ),
                      subtitle: Text(
                        'Due: ${invoice['date']} | Amount: \$${invoice['amount'].toStringAsFixed(2)}',
                        style: TextStyle(color: Colors.pink.shade700),
                      ),
                      trailing: IconButton(
                        icon: Icon(
                          invoice['paid'] ? Icons.check_circle : Icons.cancel,
                          color: invoice['paid'] ? Colors.greenAccent.shade400 : Colors.pink.shade700,
                        ),
                        onPressed: () => _togglePaid(index),
                      ),
                    ),
                  );
                },
              ),
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
        labelStyle: TextStyle(color: Colors.pink.shade900, fontSize: 16),
        filled: true,
        fillColor: Colors.pink.shade50, // Light pastel pink
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: Colors.pink.shade300),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(12),
          borderSide: BorderSide(color: Colors.pink.shade400),
        ),
        prefixIcon: Icon(icon, color: Colors.pink.shade700),
      ),
      style: TextStyle(color: Colors.pink.shade900, fontSize: 18),
      keyboardType: TextInputType.number,
    );
  }
}
