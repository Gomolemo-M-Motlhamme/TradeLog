//This class will deal with the following:
// 1) Get user data
// 2) Store user data to a relational database
package CRUD_Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Save{
    
    // 1) Get user data
    public static void SaveData(int tradeNo, String date, String position, String outcome, String note)
    {
        // 2) Store user data to a relational database
        
        Connection connection = null;
        Statement statement = null;
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tradelog_db";
        String username = "root";
        String password = "Gomolemo$1";
        
        try 
        {
            // Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            // call a SQL statement
            statement = connection.createStatement();
            
            // Build the SQL INSERT query
            String insertQuery = "INSERT INTO trade_data (trade_ID, date, position, outcome, note) " +
                    "VALUES (" + tradeNo + ", '" + date + "', '" + position + "', '" + outcome + "', '" + note + "');";
            
            // Execute the INSERT query
            statement.executeUpdate(insertQuery);
            
            // Close the resources
            statement.close();
            connection.close();
        }
        catch (SQLException e) 
        {
            int errorCode = e.getErrorCode();
            
            if(errorCode == 1062)
            {
                JOptionPane.showMessageDialog(null, "Trade "+ tradeNo + " already exsits", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            System.err.println("Error inserting data: " + e.getMessage()+"Error code: " + e.getErrorCode());
        }
        
    }
    
}
