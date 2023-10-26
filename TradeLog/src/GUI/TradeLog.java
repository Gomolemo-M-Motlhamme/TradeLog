package GUI;

import Theme_Class.LookAndFeel;
import CRUD_Class.Save;
import CRUD_Class.Edit;
import CRUD_Class.Delete;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class TradeLog extends javax.swing.JFrame {

    /**
     * Creates new form TradeLog
     */
    public TradeLog() {
        initComponents();
        FetchData();
        WinRate();
    }
    
    public void FetchData()
    {
        Connection con;
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/tradelog_db";
        String username = "root";
        String password = "Gomolemo$1";
        try 
        {
            con = DriverManager.getConnection(jdbcUrl, username, password);
            
            // Create a SQL statement
            Statement statement = con.createStatement();
            
            // Execute the query to fetch data from the table
            String query = "SELECT * FROM trade_data;";
            ResultSet resultSet = statement.executeQuery(query);
            
            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = (DefaultTableModel) Table.getModel();
            // Clear the existing data in the table
            model.setRowCount(0);
            while (resultSet.next()) 
            {
                int trade_ID = resultSet.getInt("trade_ID");
                String date = resultSet.getString("date");
                String position = resultSet.getString("position");
                String outcome = resultSet.getString("outcome");
                String note = resultSet.getString("note");

                // Add the fetched data to the table mode
                model.addRow(new Object[]{trade_ID, date, position, outcome, note});
            }
            
            // Close the resources
            resultSet.close();
        }
        
        catch (SQLException e) 
        {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
    
    public void WinRate()
    {
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
        int winCount = 0;
        int totalTrades = model.getRowCount();
        for (int i = 0; i < totalTrades; i++) 
        {
            String outcome = (String) model.getValueAt(i, 3);
            if(outcome.equals("Win"))
            {
                winCount++;
            }
        }
        
        double winRate = (double) winCount / totalTrades;
        winRate = Math.round(winRate*100.0)/100.0;
        
        winRate_txt.setText(Double.toString(winRate * 100));
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        display_panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tradeNo_txt = new javax.swing.JTextField();
        day_CB = new javax.swing.JComboBox<>();
        position_CB = new javax.swing.JComboBox<>();
        note_txt = new javax.swing.JTextField();
        toggel_Btn = new javax.swing.JButton();
        Del_Btn = new javax.swing.JButton();
        Export_Btn1 = new javax.swing.JButton();
        Save_Btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        Edit_Btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        winRate_txt = new javax.swing.JLabel();
        outcome_CB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TradeLog");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        display_panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Day");

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Position");

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Note");

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Trade No.");

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Outcome");

        tradeNo_txt.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        tradeNo_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tradeNo_txt.setActionCommand("<Not Set>");

        day_CB.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        day_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<--Select-->", "Mon", "Tue", "Wed", "Thur", "Fri" }));

        position_CB.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        position_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<--Select-->", "Long", "Short" }));

        note_txt.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        note_txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        toggel_Btn.setBackground(new java.awt.Color(75, 110, 175));
        toggel_Btn.setFont(new java.awt.Font("Microsoft JhengHei", 0, 12)); // NOI18N
        toggel_Btn.setForeground(new java.awt.Color(255, 255, 255));
        toggel_Btn.setText("Dark/Light");
        toggel_Btn.setFocusPainted(false);
        toggel_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggel_BtnActionPerformed(evt);
            }
        });

        Del_Btn.setBackground(new java.awt.Color(75, 110, 175));
        Del_Btn.setFont(new java.awt.Font("Microsoft JhengHei", 1, 10)); // NOI18N
        Del_Btn.setForeground(new java.awt.Color(255, 255, 255));
        Del_Btn.setText("Delete");
        Del_Btn.setFocusPainted(false);
        Del_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Del_BtnActionPerformed(evt);
            }
        });

        Export_Btn1.setBackground(new java.awt.Color(75, 110, 175));
        Export_Btn1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 10)); // NOI18N
        Export_Btn1.setForeground(new java.awt.Color(255, 255, 255));
        Export_Btn1.setText("Export");
        Export_Btn1.setFocusPainted(false);
        Export_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Export_Btn1ActionPerformed(evt);
            }
        });

        Save_Btn.setBackground(new java.awt.Color(75, 110, 175));
        Save_Btn.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        Save_Btn.setForeground(new java.awt.Color(255, 255, 255));
        Save_Btn.setText("Save");
        Save_Btn.setFocusPainted(false);
        Save_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_BtnActionPerformed(evt);
            }
        });

        Table.setAutoCreateRowSorter(true);
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trade No.", "Day", "Position", "Outcome", "Note"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        Table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Table.setSelectionBackground(new java.awt.Color(75, 110, 175));
        Table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        Table.setShowGrid(false);
        jScrollPane2.setViewportView(Table);

        Edit_Btn.setBackground(new java.awt.Color(75, 110, 175));
        Edit_Btn.setFont(new java.awt.Font("Microsoft JhengHei", 1, 10)); // NOI18N
        Edit_Btn.setForeground(new java.awt.Color(255, 255, 255));
        Edit_Btn.setText("Edit");
        Edit_Btn.setFocusPainted(false);
        Edit_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edit_BtnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(75, 110, 175));
        jPanel1.setPreferredSize(new java.awt.Dimension(198, 56));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Win Rate");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        winRate_txt.setFont(new java.awt.Font("Microsoft JhengHei", 0, 24)); // NOI18N
        winRate_txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winRate_txt.setText("0.00%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(winRate_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(winRate_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        outcome_CB.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        outcome_CB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<--Select-->", "Win", "Loss" }));

        javax.swing.GroupLayout display_panelLayout = new javax.swing.GroupLayout(display_panel);
        display_panel.setLayout(display_panelLayout);
        display_panelLayout.setHorizontalGroup(
            display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, display_panelLayout.createSequentialGroup()
                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(display_panelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toggel_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, display_panelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(display_panelLayout.createSequentialGroup()
                                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tradeNo_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(day_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(display_panelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(position_CB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(outcome_CB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(display_panelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(note_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addComponent(Save_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(display_panelLayout.createSequentialGroup()
                                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(display_panelLayout.createSequentialGroup()
                                        .addComponent(Del_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Edit_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2))
                                .addGap(18, 18, 18)
                                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Export_Btn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(25, 25, 25))
        );
        display_panelLayout.setVerticalGroup(
            display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, display_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(toggel_Btn)
                .addGap(18, 18, 18)
                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Del_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Edit_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Export_Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(display_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tradeNo_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(day_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(note_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Save_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(position_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outcome_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(display_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void toggel_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggel_BtnActionPerformed
        // TODO add your handling code here:
        if (UIManager.getLookAndFeel() instanceof FlatLightLaf) 
        {
            LookAndFeel.LookAndFeel_Dark();
            display_panel.setBackground(Color.DARK_GRAY);
            jPanel2.setBackground(Color.DARK_GRAY);
        } 
        
        else 
        {
            LookAndFeel.LookAndFeel_Light();
            display_panel.setBackground(Color.WHITE);
            jPanel2.setBackground(Color.WHITE);
        }

        // Update the UI to reflect the new look and feel
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_toggel_BtnActionPerformed

    private void Save_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_BtnActionPerformed
        // TODO add your handling code here:
        if(tradeNo_txt.getText().isEmpty() || day_CB.getSelectedItem().equals("<--Select-->") || position_CB.getSelectedItem().equals("<--Select-->") || outcome_CB.getSelectedItem().equals("<--Select-->") || note_txt.getText().isEmpty())
        {
            // Error handeling to make sure no null values are sent to the datatable
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", 2);
        }
        else
        {
            // Get values
            int GettradeNo = Integer.parseInt(tradeNo_txt.getText());
            String Getdate = day_CB.getSelectedItem().toString();
            String Getposition = position_CB.getSelectedItem().toString();
            String Getoutcome = outcome_CB.getSelectedItem().toString();
            String Getnote =  note_txt.getText();
            
            // Send to Save class to save to DataBase
            Save.SaveData(GettradeNo, Getdate, Getposition, Getoutcome, Getnote);
            
            // Rest textfileds to null
            tradeNo_txt.setText(null); day_CB.setSelectedIndex(0); position_CB.setSelectedIndex(0); outcome_CB.setSelectedIndex(0); note_txt.setText(null);
            FetchData();
            WinRate();
        }
    }//GEN-LAST:event_Save_BtnActionPerformed

    private void Del_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Del_BtnActionPerformed
        // TODO add your handling code here:
        
        int result = JOptionPane.showConfirmDialog(null, "Remove all trades ?",
                "Clear Data", JOptionPane.YES_NO_CANCEL_OPTION);
        
        switch (result) 
        {
            case JOptionPane.YES_OPTION:
                Delete.DeleteAll();
                JOptionPane.showMessageDialog(null, "Successful", "All trades have been deleted", JOptionPane.INFORMATION_MESSAGE);
                FetchData();
                winRate_txt.setText("0.00%");
                break;
                
            case JOptionPane.NO_OPTION:
                // Delete data for a specific trade ID
                String userInput = JOptionPane.showInputDialog(null, "Enter the trade no. you wish to remove:", "Trade no.");
                if (userInput != null) 
                {
                    int tradeID = Integer.parseInt(userInput);
                    Delete.DeleteID(tradeID);
                    JOptionPane.showMessageDialog(null, "Successful", "Trade has been deleted", JOptionPane.INFORMATION_MESSAGE);
                    FetchData();
                    WinRate();
                }
                break;
            default:
                break;
        }
       
    }//GEN-LAST:event_Del_BtnActionPerformed

    private void Edit_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edit_BtnActionPerformed
        // TODO add your handling code here:
        String userInput = JOptionPane.showInputDialog(null, "Trade no.", "Trade no.");
        try{
                if (userInput != null) 
                {
                    int tradeID = Integer.parseInt(userInput);
                    Edit.EditData(tradeID);
                    JOptionPane.showMessageDialog(null, "Successful", "Data has been updated", JOptionPane.INFORMATION_MESSAGE);
                    FetchData();
                    WinRate();
                }
            }
        
        catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Trade No. error", "Error", JOptionPane.ERROR_MESSAGE);
            } 
    }//GEN-LAST:event_Edit_BtnActionPerformed

    private void Export_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Export_Btn1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) Table.getModel();
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
            String filePath = desktopPath + File.separator + "Trade Data.xlsx";

            // Save the Excel file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            JOptionPane.showMessageDialog(this, "View your 'Downloads folder'", 
                    "Export Complete", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception ex) 
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting data to Excel.", 
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Export_Btn1ActionPerformed
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //Look and feel light mode
        try 
        {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } 
        catch( Exception ex ) 
        {
            System.err.println( "Failed to initialize LaF" );
        }
        //End of look and feel code

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TradeLog().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Del_Btn;
    private javax.swing.JButton Edit_Btn;
    private javax.swing.JButton Export_Btn1;
    private javax.swing.JButton Save_Btn;
    private javax.swing.JTable Table;
    private javax.swing.JComboBox<String> day_CB;
    private javax.swing.JPanel display_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField note_txt;
    private javax.swing.JComboBox<String> outcome_CB;
    private javax.swing.JComboBox<String> position_CB;
    private javax.swing.JButton toggel_Btn;
    private javax.swing.JTextField tradeNo_txt;
    private javax.swing.JLabel winRate_txt;
    // End of variables declaration//GEN-END:variables
}
