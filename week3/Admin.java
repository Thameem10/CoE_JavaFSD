package week3;

import java.sql.*;

public class Admin {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root"; 

    public static boolean validateAdmin(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, username, password)) {
            System.out.println("Admin login successful");
            return true;
        } catch (SQLException e) {
            System.out.println("Admin login failed: " + e.getMessage());
            return false;
        }
    }

    public void addAccountant(String name, String email, String phone, String password) {
        String query = "INSERT INTO accountant (name, email, phone, password) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, password);
            ps.executeUpdate();
            System.out.println("Accountant added successfully");

        } catch (SQLException e) {
            System.out.println("Error adding accountant: " + e.getMessage());
        }
    }

    public void viewAccountant() {
        String query = "SELECT * FROM accountant";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") +
                                   ", Email: " + rs.getString("email") +
                                   ", Phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing accountants: " + e.getMessage());
        }
    }

    public void deleteAccountant(String name) {
        String query = "DELETE FROM accountant WHERE name = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0)
                System.out.println("Accountant deleted successfully");
            else
                System.out.println("No accountant found with the given name");

        } catch (SQLException e) {
            System.out.println("Error deleting accountant: " + e.getMessage());
        }
    }

    public void updateAccountant(String name, String newEmail) {
        String query = "UPDATE accountant SET email = ? WHERE name = ?";
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, newEmail);
            ps.setString(2, name);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0)
                System.out.println("Accountant updated successfully");
            else
                System.out.println("No accountant found with the given name");

        } catch (SQLException e) {
            System.out.println("Error updating accountant: " + e.getMessage());
        }
    }
}
