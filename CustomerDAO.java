/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplebankingsystem;

import java.sql.*;

/**
 *
 * @author Asus
 */
public class CustomerDAO {

    public int addCustomer(String fName, String lName, String email, String phone) {
        String query = "INSERT INTO Customer (first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Returns the newly created Customer ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateCustomer(int id, String fName, String lName, String email, String phone) {
        String query = "UPDATE Customer SET first_name=?, last_name=?, email=?, phone_number=? WHERE customer_id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id) {
        String query = "DELETE FROM Customer WHERE customer_id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
