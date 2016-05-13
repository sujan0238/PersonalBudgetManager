package PersonalBudgetManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Sujan  Chauhan on 5/10/2016.
 */
public class Manage extends JFrame {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/personalbudgetmanager";

    static final String USERNAME = "root";
    static final String PASSWORD = "";

    public  JLabel ldate, lcategory, ldescription, lamount;
    public  JPanel jPanel, jPanel1;
    public  JTextField dateTextField, categoryTextField, descriptionTextField, amountTextField;
    public  JButton save, show, close;
    public  GridLayout gridLayout;
    public  FlowLayout flowLayout;

    public Manage() {
        ldate = new JLabel("Date");
        lcategory = new JLabel("Category");
        ldescription = new JLabel("Description");
        lamount = new JLabel("Amount");

        dateTextField = new JTextField(20);
        categoryTextField = new JTextField(20);
        descriptionTextField = new JTextField(20);
        amountTextField = new JTextField(20);

        save = new JButton("Save");
        show = new JButton("Show ");
        close = new JButton("Close");

        jPanel = new JPanel();
        jPanel1 = new JPanel();

        gridLayout = new GridLayout(2,4);
    //   flowLayout = new FlowLayout();

        jPanel.setLayout(gridLayout);
        jPanel.add(ldate);
        jPanel.add(lcategory);
        jPanel.add(ldescription);
        jPanel.add(lamount);
        jPanel.add(dateTextField);
        jPanel.add(categoryTextField);
        jPanel.add(descriptionTextField);
        jPanel.add(amountTextField);

        jPanel1.add(save);
        jPanel1.add(show);
        jPanel1.add(close);

        add(jPanel, BorderLayout.NORTH);
        add(jPanel1, BorderLayout.SOUTH);



        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateTextField.getText();
                dateTextField.setText("");
                String category = categoryTextField.getText();
                categoryTextField.setText("");
                String description = descriptionTextField.getText();
                descriptionTextField.setText("");
                int amount = Integer.parseInt(amountTextField.getText());
                amountTextField.setText("");


                //Database connection and insert
                Connection connection = null;
                PreparedStatement stmt = null;
                String insert = "INSERT INTO manager VALUES (?,?,?,?)";
                try {
                    Class.forName(JDBC_DRIVER);
                    connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    stmt = connection.prepareStatement(insert);
                    stmt.setString(1,date);
                    stmt.setString(2,category);
                    stmt.setString(3, description);
                    stmt.setInt(4, amount);

//                    System.out.println(stmt.executeUpdate());
                    stmt.executeUpdate();

                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window w = SwingUtilities.getWindowAncestor(show);
                if(w != null){
                    w.setVisible(false);

                    Login login = new Login();
                    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    login.setSize(400, 200);
                    login.setVisible(true);
                }
                JFrame frame = new ShowHistory();
                frame.setVisible(true);
                frame.setSize(500,200);

            }
        });
    }

   /* public static void main(String[] args) {
        Manage manage = new Manage();
        manage.setVisible(true);
        manage.setSize(500,200);
    }*/
        }
