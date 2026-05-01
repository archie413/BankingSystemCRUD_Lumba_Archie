/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplebankingsystem;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class TransactionDAO {

    public boolean processTransaction(int accountId, double amount, String type) {
        String checkBalance = "SELECT balance FROM Account WHERE account_id = ?";
        String updateBalance = "UPDATE Account SET balance = balance + ? WHERE account_id = ?";
        String logTransaction = "INSERT INTO Transaction (account_id, transaction_type, amount, transaction_date) VALUES (?, ?, ?, CURRENT_DATE)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Check balance for withdrawals
            if (type.equals("Withdraw")) {
                try (PreparedStatement stmt = conn.prepareStatement(checkBalance)) {
                    stmt.setInt(1, accountId);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next() && rs.getDouble("balance") < amount) {
                        JOptionPane.showMessageDialog(null, "Insufficient Funds!");
                        return false;
                    }
                }
                amount = -amount; // Make it negative for the math below
            }

            // Update account balance
            try (PreparedStatement stmt = conn.prepareStatement(updateBalance)) {
                stmt.setDouble(1, amount);
                stmt.setInt(2, accountId);
                stmt.executeUpdate();
            }

            // Log the transaction in history
            try (PreparedStatement stmt = conn.prepareStatement(logTransaction)) {
                stmt.setInt(1, accountId);
                stmt.setString(2, type);
                stmt.setDouble(3, Math.abs(amount));
                stmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

  public void loadTransactionsToTable(DefaultTableModel model, String searchQuery) {
        model.setRowCount(0); // Clear table
        
        String query = "SELECT * FROM Transaction";
        
        // FIX: Simplified the WHERE clause to let MySQL handle integer-to-string conversion natively
        if (searchQuery != null && !searchQuery.isEmpty()) {
            query += " WHERE account_id LIKE ? OR transaction_type LIKE ?";
        }
        query += " ORDER BY transaction_date DESC";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            if (searchQuery != null && !searchQuery.isEmpty()) {
                String param = "%" + searchQuery + "%";
                stmt.setString(1, param);
                stmt.setString(2, param);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Matches the 5-column history layout perfectly
                model.addRow(new Object[]{
                    rs.getInt("transaction_id"),       
                    rs.getInt("account_id"),           
                    rs.getString("transaction_type"),  
                    rs.getDouble("amount"),            
                    rs.getDate("transaction_date")     
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // FIX: If it fails, display the error in a popup so you know exactly why!
            JOptionPane.showMessageDialog(null, "Error loading transactions: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
