package CRUD_Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public static void DeleteAll()
    {
        Connection connection = null;
        Statement statement = null;
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tradelog_db";
        String username = "root";
        String password = "Gomolemo$1";
        
        try
        {
            // Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Create a SQL statement
            statement = connection.createStatement();
            
            // Delete query
            String deleteSql = "DELETE FROM trade_data";
            
            // Execute the DELETE statement
            statement.executeUpdate(deleteSql);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }
    
    public static void DeleteID(int tradeNo)
    {
        Connection connection = null;
        Statement statement = null;
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tradelog_db";
        String username = "root";
        String password = "Gomolemo$1";
        
        try
        {
            // Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Create a SQL statement
            statement = connection.createStatement();
            
            // Delete query
            String deleteSql = "DELETE FROM trade_data WHERE trade_ID = "+tradeNo;
            
            // Execute the DELETE statement
            statement.executeUpdate(deleteSql);
            
            // Close the statement and connection
            statement.close();
            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }
}
