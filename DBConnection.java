/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplebankingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/simple_banking_systems",
                        "root",
                        "SkennMYpets0025" 
                );  
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("DATABASE ERROR: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return connection;
    }
}
