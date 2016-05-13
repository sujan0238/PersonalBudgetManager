package PersonalBudgetManager;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Sujan  Chauhan on 5/3/2016.
 */
public class Login extends JFrame {
    public JTextField username;
    public JPasswordField password;
    public JLabel lUsername;
    public JLabel lPassword;
    public JButton login;
    public JButton cancel;
    public boolean succeeded;
    SQL sqlObj;

  /* public static boolean authenticate(String username, String password) {
        if (username.equals("sujan") && password.equals("sujan")) {
            return true;
        }
        return false;
    }*/

    public Login() {
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        lUsername = new JLabel("Username");
        lUsername.setSize(30,30);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(lUsername,c);

        username = new JTextField(40);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(username,c);


        lPassword = new JLabel("Password");
        lPassword.setSize(30,30);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(lPassword,c);


        password = new JPasswordField(40);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(password,c);
        add(panel);
        panel.setBorder(new LineBorder(Color.GRAY));

        login = new JButton("Login");
        panel1.add(login);
        cancel = new JButton("Cancel");
        panel1.add(cancel);
        add(panel1,BorderLayout.SOUTH);

        sqlObj = new SQL();


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value1 = username.getText();
                String value2 = password.getText();

                boolean flag = sqlObj.loginValidate(value1,value2);



               if(flag)
              /* if (Login.authenticate(getUsername(),getPassword()))*/ {
                    JFrame manage = new Manage();
                   manage.setTitle("Wel-Come to the Personal Budget Manager");
                   manage.setVisible(true);
                   manage.setSize(800,400);
                    succeeded = true;
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    username.setText("");
                    password.setText("");
                    succeeded = false;
                }
            }
        });


        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



   /* public String getUsername() {
        return username.getText().trim();
    }

    public String getPassword() {
        return password.getText();*/


}

}
