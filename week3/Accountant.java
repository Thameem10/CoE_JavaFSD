package week3;

import java.sql.*;

public class Accountant {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "root"; 

    public static boolean validateAccountant(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, username, password)) {
            System.out.println("Accountant login successful");
            return true;
        } catch (SQLException e) {
            System.out.println("Accountant login failed: " + e.getMessage());
            return false;
        }
    }

    public void addStudent(String name, String email, String course, double fee, double paid, double due, String address, String phone) {
        String query = "INSERT INTO student (name, email, course, fee, paid, due, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setDouble(4, fee);
            ps.setDouble(5, paid);
            ps.setDouble(6, due);
            ps.setString(7, address);
            ps.setString(8, phone);
            ps.executeUpdate();
            System.out.println("Student added successfully");

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void viewStudent() {
        String query = "SELECT * FROM student";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") +
                                   ", Email: " + rs.getString("email") +
                                   ", Course: " + rs.getString("course") +
                                   ", Due: " + rs.getDouble("due"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing students: " + e.getMessage());
        }
    }

    public void deleteStudent(String name) {
        String query = "DELETE FROM student WHERE name = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println("Student deleted successfully");

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
}
