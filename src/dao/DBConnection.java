
// This class manages the database connection for the Campus Mail Client application. It provides a method to establish a connection to the MySQL database using JDBC.
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DBConnection class to manage database connections
public class DBConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/maildb";   // Database URL, including the database name (campus_mail_client)
    private static final String USER = "root";                         // Database username
    private static final String PASSWORD = "2290";                 // Database password


// Method to establish and return a connection to the database
public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); // Establish and return a connection to the database   
}
}