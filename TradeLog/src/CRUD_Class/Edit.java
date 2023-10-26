package CRUD_Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Edit {
    public static void EditData(int tradeID)
    {
        Connection connection = null;
        Statement statement = null;
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tradelog_db";
        String username = "root";
        String password = "Gomolemo$1";
        
            try{
                String day = JOptionPane.showInputDialog(null, "Enter the day the trade was taken ", "Mon-Fri");
                String position = JOptionPane.showInputDialog(null, "Enter the trade postition ", "Long/Short");
                String outcome = JOptionPane.showInputDialog(null, "Enter the trade outcome ", "Win/Loss");
                String note = JOptionPane.showInputDialog(null, "Trade notes ", "");
                
                if (outcome != null && !outcome.isEmpty() && position != null && !position.isEmpty()) 
                {
                    outcome = outcome.substring(0, 1).toUpperCase() + outcome.substring(1);
                    position = position.substring(0, 1).toUpperCase() + position.substring(1);
                    
                    try 
                    {
                        // Connect to the database
                        connection = DriverManager.getConnection(jdbcUrl, username, password);

                        // call a SQL statement
                        statement = connection.createStatement();

                        // Build the SQL INSERT query
                        String insertQuery = "UPDATE trade_data SET " +
                        "date = '" + day + "', " +
                        "position = '" + position + "', " +
                        "outcome = '" + outcome + "', " +
                        "note = '" + note + "' " +
                        "WHERE trade_ID = " + tradeID;


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
                            JOptionPane.showMessageDialog(null, "Trade "+ tradeID + " does not exsits", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        System.err.println("Error inserting data: " + e.getMessage()+"Error code: " + e.getErrorCode());
                    }
                }

                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            } 
    }
}
