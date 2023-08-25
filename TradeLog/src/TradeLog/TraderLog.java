/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TradeLog;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author gomim
 */
public class TraderLog extends javax.swing.JFrame {
    
    public TraderLog() {
        initComponents();
        
        String iconPath = "Assets/chart.png";
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        this.setIconImage(icon.getImage());
        
        
        TableUpdate();
        StartingBalancetxt.setText(GetBalance());
        CurrentBalancetxt.setText(GetCurrentBalance());
        
    }
    
    public String GetBalance()
    {
        String data = null;
        String userHomeDirectory = System.getProperty("user.home");
        String filePath = userHomeDirectory + "/Balance.txt";
        try 
        {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
        return data;
    }
    
    public String GetCurrentBalance()
    {
        String data = null;
        String userHomeDirectory = System.getProperty("user.home");
        String filePath = userHomeDirectory + "/CurrentBalance.txt";
        try 
        {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              data = myReader.nextLine();
              System.out.println("Current account: " + data);
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
        return data;
    }
    
    public void TableUpdate()
    {
        Connection con;
        try 
        {
            String JDBC_URL = "jdbc:sqlite:TradeInfoDB.sqlite";
            con = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connected to the SQLite database.");
            
            // Create a SQL statement
            Statement statement = con.createStatement();
            
            // Execute the query to fetch data from the table
            String query = "SELECT * FROM TradeData;";
            ResultSet resultSet = statement.executeQuery(query);
            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = (DefaultTableModel) tradeTable.getModel();
            // Clear the existing data in the table
            model.setRowCount(0);
            while (resultSet.next()) 
            {
                int tradeNo = resultSet.getInt("TradeNumber");
                String date = resultSet.getString("Date");
                String position = resultSet.getString("Position");
                float profits = resultSet.getFloat("Profits");
                String note = resultSet.getString("Note");
                
                // Add the fetched data to the table mode
                model.addRow(new Object[]{tradeNo, date, position, profits, note});
            }
            
            // Close the resources
            resultSet.close();
        }
        
        catch (SQLException e) 
        {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
    
    private String calculateAccountBalance()
    {
        float balance = Float.parseFloat(StartingBalancetxt.getText());

        DefaultTableModel model = (DefaultTableModel) tradeTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            float profit = (float) model.getValueAt(row, 3); // Assuming the profit is in column index 3
            balance += profit;
        }
        
        String currentB = String.valueOf(balance);
        
        String userHomeDirectory = System.getProperty("user.home");
        String filePath = userHomeDirectory + "/CurrentBalance.txt";
        
        try
        {
            FileWriter Writer = new FileWriter(filePath);
            Writer.write(currentB);
            Writer.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Something wrong happened try again", "Error", 0);
        }
        return currentB;
    }
    
    //Delete all data
    private boolean deleteAllData() 
    {
        String JDBC_URL = "jdbc:sqlite:TradeInfoDB.sqlite";
        String deleteAllQuery = "DELETE FROM TradeData"; // Replace "your_table_name" with your actual table name
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteAllQuery)) {
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to delete data for a specific trade ID
    private boolean deleteDataByTradeID(int tradeID) 
    {
        String JDBC_URL = "jdbc:sqlite:TradeInfoDB.sqlite";
        String deleteByTradeIDQuery = "DELETE FROM TradeData WHERE TradeNumber = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteByTradeIDQuery)) {
            preparedStatement.setInt(1, tradeID);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        LayoutPanel = new javax.swing.JPanel();
        exportBtn = new javax.swing.JButton();
        addToTableBtn = new javax.swing.JButton();
        tradenumtxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        positiontxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        profittxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        notetxt = new javax.swing.JTextField();
        datetxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tradeTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        StartingBalancetxt = new javax.swing.JLabel();
        addaccountBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        CurrentBalancetxt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        removeaccountBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TradeLog");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setBackground(new java.awt.Color(60, 60, 60));

        LayoutPanel.setBackground(new java.awt.Color(30, 30, 30));

        exportBtn.setBackground(new java.awt.Color(14, 90, 57));
        exportBtn.setForeground(new java.awt.Color(255, 255, 255));
        exportBtn.setText("Export");
        exportBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportBtnMouseClicked(evt);
            }
        });

        addToTableBtn.setBackground(new java.awt.Color(14, 90, 57));
        addToTableBtn.setForeground(new java.awt.Color(255, 255, 255));
        addToTableBtn.setText("Add to table");
        addToTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToTableBtnActionPerformed(evt);
            }
        });

        tradenumtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trade no.");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Position");

        positiontxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Profit");

        profittxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Note");

        notetxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        datetxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Date");

        javax.swing.GroupLayout LayoutPanelLayout = new javax.swing.GroupLayout(LayoutPanel);
        LayoutPanel.setLayout(LayoutPanelLayout);
        LayoutPanelLayout.setHorizontalGroup(
            LayoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayoutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LayoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addToTableBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(tradenumtxt)
                    .addComponent(positiontxt)
                    .addComponent(profittxt)
                    .addComponent(notetxt)
                    .addComponent(datetxt))
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LayoutPanelLayout.setVerticalGroup(
            LayoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayoutPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(tradenumtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(positiontxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(profittxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(notetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addToTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(exportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        TablePanel.setBackground(new java.awt.Color(43, 43, 43));

        tradeTable.setAutoCreateRowSorter(true);
        tradeTable.setBackground(new java.awt.Color(43, 43, 43));
        tradeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Trade ID", "Date", "Position", "Profit", "Note"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tradeTable.setGridColor(new java.awt.Color(255, 255, 255));
        tradeTable.setSelectionBackground(new java.awt.Color(14, 90, 57));
        tradeTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tradeTable);

        javax.swing.GroupLayout TablePanelLayout = new javax.swing.GroupLayout(TablePanel);
        TablePanel.setLayout(TablePanelLayout);
        TablePanelLayout.setHorizontalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TablePanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TablePanelLayout.setVerticalGroup(
            TablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Starting Balance:");

        StartingBalancetxt.setForeground(new java.awt.Color(255, 255, 255));
        StartingBalancetxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartingBalancetxt.setText("0.00");

        addaccountBtn.setBackground(new java.awt.Color(14, 90, 57));
        addaccountBtn.setForeground(new java.awt.Color(255, 255, 255));
        addaccountBtn.setText("Add balance");
        addaccountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addaccountBtnActionPerformed(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(14, 90, 57));
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("Clear Table");
        clearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearBtnMouseClicked(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Current Balance:");

        CurrentBalancetxt.setForeground(new java.awt.Color(255, 255, 255));
        CurrentBalancetxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CurrentBalancetxt.setText("0.00");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        removeaccountBtn.setBackground(new java.awt.Color(14, 90, 57));
        removeaccountBtn.setForeground(new java.awt.Color(255, 255, 255));
        removeaccountBtn.setText("Remove balance");
        removeaccountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeaccountBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(LayoutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(StartingBalancetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CurrentBalancetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeaccountBtn)
                            .addGap(18, 18, 18)
                            .addComponent(addaccountBtn))
                        .addGroup(panelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LayoutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(StartingBalancetxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addaccountBtn)
                        .addComponent(jLabel7)
                        .addComponent(CurrentBalancetxt)
                        .addComponent(removeaccountBtn))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(TablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addToTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToTableBtnActionPerformed
        // TODO add your handling code here:
        String JDBC_URL = "jdbc:sqlite:TradeInfoDB.sqlite";

        try {
            if("0.00".equals(StartingBalancetxt.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Add a starting balance first", "Error", 0);
                    tradenumtxt.setText(null);
                    datetxt.setText(null);
                    positiontxt.setText(null);
                    profittxt.setText(null);
                    notetxt.setText(null);
                }
            
            else
                {
                    // Establish the connection to the database
                    Connection connection = DriverManager.getConnection(JDBC_URL);

                    // Create a SQL statement
                    Statement statement = connection.createStatement();


                    // Define the data to be inserted
                    int tradeNo = Integer.parseInt(tradenumtxt.getText()); 
                    String date = datetxt.getText();
                    String position = positiontxt.getText();
                    float profits = Float.parseFloat(profittxt.getText());
                    String note = notetxt.getText();

                    // Build the SQL INSERT query
                    String insertQuery = "INSERT INTO TradeData (TradeNumber, Date, Position, Profits, Note) " +
                            "VALUES (" + tradeNo + ", '" + date + "', '" + position + "', " + profits + ", '" + note + "');";

                    // Execute the INSERT query
                    statement.executeUpdate(insertQuery);
                    // Execute the query to fetch data from the table
                    String query = "SELECT * FROM TradeData;";
                    ResultSet resultSet = statement.executeQuery(query);
                    // Create a DefaultTableModel to hold the data
                    DefaultTableModel model = (DefaultTableModel) tradeTable.getModel();
                    // Clear the existing data in the table
                    model.setRowCount(0);
                    while (resultSet.next()) 
                    {
                        tradeNo = resultSet.getInt("TradeNumber");
                        date = resultSet.getString("Date");
                        position = resultSet.getString("Position");
                        profits = resultSet.getFloat("Profits");
                        note = resultSet.getString("Note");

                        // Add the fetched data to the table mode
                        model.addRow(new Object[]{tradeNo, date, position, profits, note});
                    }

                    // Close the resources
                    resultSet.close();

                    statement.close();
                    connection.close();

                    System.out.println("Data inserted successfully.");
                    tradenumtxt.setText(null);
                    datetxt.setText(null);
                    positiontxt.setText(null);
                    profittxt.setText(null);
                    notetxt.setText(null);

                    CurrentBalancetxt.setText(calculateAccountBalance());
                    
            } 
        }
        catch (SQLException e) 
        {
            System.err.println("Error inserting data: " + e.getMessage());
        }  
            
    }//GEN-LAST:event_addToTableBtnActionPerformed

    private void addaccountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addaccountBtnActionPerformed
        // TODO add your handling code here:
        String userInput = JOptionPane.showInputDialog(null, "Enter your account balance:");
        
        
        String userHomeDirectory = System.getProperty("user.home");
        String filePath = userHomeDirectory + "/Balance.txt";
        try 
        {
            FileWriter Writer = new FileWriter(filePath);
            StartingBalancetxt.setText(userInput);
            Writer.write(userInput);
            Writer.close();
        } 
        
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_addaccountBtnActionPerformed

    private void clearBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBtnMouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Remove all data ?",
                "Clear Data", JOptionPane.YES_NO_CANCEL_OPTION);
        
        switch (result) 
        {
            case JOptionPane.YES_OPTION:
                // Delete all data in the database table
                if (deleteAllData()) 
                {
                    JOptionPane.showMessageDialog(null, "Successfull", "Data has been removed", JOptionPane.INFORMATION_MESSAGE);
                    TableUpdate();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Error", "Failed to remove data", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case JOptionPane.NO_OPTION:
                // Delete data for a specific trade ID
                String userInput = JOptionPane.showInputDialog(null, "Enter the trade ID you wish to remove:", "Trade ID");
                if (userInput != null) {
                    int tradeID = Integer.parseInt(userInput);

                    if (deleteDataByTradeID(tradeID)) 
                    {
                        JOptionPane.showMessageDialog(null, "Successfull", "Data for Trade ID " + tradeID + " has been removed", JOptionPane.INFORMATION_MESSAGE);
                        TableUpdate();
                    } 

                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Error", "Failed to remove data for Trade ID " + tradeID, JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            default:
                break;
        }
        
        StartingBalancetxt.setText("0.00");
        CurrentBalancetxt.setText("0.00");
    }//GEN-LAST:event_clearBtnMouseClicked

    private void exportBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportBtnMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tradeTable.getModel();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("TradeData");

            // Copy the table headers to Excel
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < model.getColumnCount(); col++) 
            {
                headerRow.createCell(col).setCellValue(model.getColumnName(col));
            }
            
            // Copy the table data to Excel
            for (int row = 0; row < model.getRowCount(); row++) 
            {
                Row dataRow = sheet.createRow(row + 1);
                for (int col = 0; col < model.getColumnCount(); col++) 
                {
                    Object cellValue = model.getValueAt(row, col);
                    if (cellValue != null) 
                    {
                        dataRow.createCell(col).setCellValue(cellValue.toString());
                    } 
                    
                    else 
                    {
                        dataRow.createCell(col).setCellValue("");
                    }
                }
            }
            
            // Get the user's home directory and append "Desktop" to it
            String desktopPath = System.getProperty("user.home") + File.separator + "Downloads";
            String filePath = desktopPath + File.separator + "TradeData.xlsx";

            // Save the Excel file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            JOptionPane.showMessageDialog(this, "View 'Downloads folder' for Excel file", 
                    "Export Complete", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting data to Excel.", 
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_exportBtnMouseClicked

    private void removeaccountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeaccountBtnActionPerformed
        // TODO add your handling code here:
        
        String userHomeDirectory = System.getProperty("user.home");
        String filePath = userHomeDirectory + "/Balance.txt";
        
        try
        {
            FileWriter Writer = new FileWriter(filePath);
            Writer.write("0.00");
            Writer.close();
            JOptionPane.showMessageDialog(null, "Balance was removed");
             try 
             {
                File myObj = new File(filePath);
                Scanner Reader = new Scanner(myObj);
                while (Reader.hasNextLine()) 
                    {
                        String data = Reader.nextLine();
                        StartingBalancetxt.setText(data);
                    }
                Reader.close();
            } 
             
            catch (FileNotFoundException e) 
            {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
              
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Something wrong happened try again", "Error", 0);
        }
    }//GEN-LAST:event_removeaccountBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try 
        {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } 
        catch( Exception ex ) 
        {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>
        /* Create and display the form */
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TraderLog().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CurrentBalancetxt;
    private javax.swing.JPanel LayoutPanel;
    private javax.swing.JLabel StartingBalancetxt;
    private javax.swing.JPanel TablePanel;
    private javax.swing.JButton addToTableBtn;
    private javax.swing.JButton addaccountBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField datetxt;
    private javax.swing.JButton exportBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField notetxt;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField positiontxt;
    private javax.swing.JTextField profittxt;
    private javax.swing.JButton removeaccountBtn;
    private javax.swing.JTable tradeTable;
    private javax.swing.JTextField tradenumtxt;
    // End of variables declaration//GEN-END:variables
}
