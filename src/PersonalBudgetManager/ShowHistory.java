package PersonalBudgetManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sujan  Chauhan on 5/12/2016.
 */
public class ShowHistory extends JFrame {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/personalbudgetmanager";

    static final String USERNAME = "root";
    static final String PASSWORD = "";

    public JLabel lbdate, lbcategory, lbdescription, lbamount;
    public JTextArea areaDate, areacategory, areaDescription, areaAmount;
    public JButton showHistory;
    JPanel showPanel, pane, showPanel1;
    GridLayout layout, layout1;
    JTable jTable;


    public ShowHistory(){
        lbdate = new JLabel("Date");
        lbcategory = new JLabel("Category");
        lbdescription = new JLabel("Description");
        lbamount = new JLabel("Amount");


        areaDate = new JTextArea(20,20);
        areacategory = new JTextArea(20,20);
        areaDescription = new JTextArea(20,20);
        areaAmount = new JTextArea(20,20);
        showPanel = new JPanel();

        showHistory = new JButton("Show History");

        layout = new GridLayout(1,4);
        showPanel.setLayout(layout);

        showPanel.add(lbdate);
        showPanel.add(lbcategory);
        showPanel.add(lbdescription);
        showPanel.add(lbamount);


//        showPanel1.setLayout(layout1);

        showPanel1 = new JPanel();
        showPanel1.setLayout(new GridLayout(1,4));
        showPanel1.add(areaDate);
        showPanel1.add(areacategory);
        showPanel1.add(areaDescription);
        showPanel1.add(areaAmount);


        add(showPanel, BorderLayout.NORTH);
        add(showPanel1, BorderLayout.CENTER);

        pane = new JPanel();
        pane.add(showHistory, BorderLayout.SOUTH);
        add(pane,BorderLayout.SOUTH);

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                System.out.println("query written.......");
                String select = "SELECT * FROM manager";
                System.out.println(select);
                System.out.println("query written.......");

                try {
                    Class.forName(JDBC_DRIVER);
                    conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    stmt = conn.prepareStatement(select);

//                    String dateDate = areaDate.getText();
//                    System.out.println(areaDate.getText());

                    System.out.println("Query executing");
                    rs = stmt.executeQuery();

                    List<Details> historyList = new LinkedList<Details>();

                    while (rs.next()) {
                        Details history = new Details();

                        history.setAmount(rs.getString("amount"));
                        history.setCategory(rs.getString("category"));
                        history.setDate(rs.getString("date"));
                        history.setDescription(rs.getString("description"));

                        historyList.add(history);
                    }

                    Object [][] data = new Object[historyList.size()][5];

                    int i = 0;

                    for (Details d : historyList){
                        data[i][0] = d.getDate();
                        data[i][1] = d.getCategory();
                        data[i][2] = d.getDescription();
                        data[i][3] = d.getAmount();
                        i++;
                    }

                    String [] columNames = {"Date","Category","Description","Amount"};

                    jTable = new JTable(data,columNames);
                    jTable.setPreferredScrollableViewportSize(new Dimension(500,100));
                    jTable.setFillsViewportHeight(true);

                    add(jTable,BorderLayout.CENTER);
                    setVisible(true);
                    setSize(500,500);

                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
