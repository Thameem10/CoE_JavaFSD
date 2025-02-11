package week3;

public class LoginPage {
    public static void main(String[] args) {
        String username = "root";
        String password = "root";

        Admin admin = new Admin();
        Accountant accountant = new Accountant();

        if (Admin.validateAdmin(username, password)) {
            System.out.println("Admin Login Successful");
            admin.addAccountant("sanjay", "sanjay@gmail.com", "54321", "sanjay@123");
            admin.viewAccountant();
            admin.updateAccountant("sanjay", "sanjay77@123");
            admin.deleteAccountant("sanjay");
        } else {
            System.out.println("Invalid Admin Credentials. Returning to login...");
            return;
        }

        if (Accountant.validateAccountant(username, password)) {
            System.out.println("Accountant Login Successful");
            accountant.addStudent("aasik", "aasik@gmail.com", "cse", 10000.00, 7000.00, 3000.00, "madurai", "12345");
            accountant.viewStudent();
            accountant.deleteStudent("aasik");
        } else {
            System.out.println("Invalid Accountant Credentials. Returning to login...");
        }
    }
}
