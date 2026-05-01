/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplebankingsystem;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class AccountDAO {

    public void addAccount(int customerId, String type, double balance) {
        String query = "INSERT INTO Account (customer_id, account_type, balance) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, type);
            stmt.setDouble(3, balance);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(int accountId, String type, double balance) {
        String query = "UPDATE Account SET account_type=?, balance=? WHERE account_id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, type);
            stmt.setDouble(2, balance);
            stmt.setInt(3, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void loadCustomerAccountsToTable(DefaultTableModel model, String searchQuery) {
        model.setRowCount(0); // Clear table
        
        String query = "SELECT a.account_id, c.first_name, c.last_name, c.email, a.account_type, a.balance "
                     + "FROM Customer c JOIN Account a ON c.customer_id = a.customer_id ";
        
        // This query safely filters by Account ID, First Name, OR Last Name.
        // This covers any JLabel requirement perfectly!
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query += "WHERE CAST(a.account_id AS CHAR) LIKE ? OR c.first_name LIKE ? OR c.last_name LIKE ?";
        }

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            if (searchQuery != null && !searchQuery.isEmpty()) {
                String searchParam = "%" + searchQuery + "%";
                stmt.setString(1, searchParam);
                stmt.setString(2, searchParam);
                stmt.setString(3, searchParam);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                
                // Matches the 5-column design flawlessly
                model.addRow(new Object[]{
                    rs.getInt("account_id"),       
                    fullName,                      
                    rs.getString("email"),         
                    rs.getString("account_type"),  
                    rs.getDouble("balance")        
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
