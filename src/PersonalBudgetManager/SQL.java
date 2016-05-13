package PersonalBudgetManager;

import java.sql.*;

/**
 * Created by Sujan  Chauhan on 5/13/2016.
 */
public class SQL {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/personalbudgetmanager";

    static final String USERNAME = "root";
    static final String PASSWORD = "";

            Statement stmt;
    private Connection conn;
    private PreparedStatement preparedStatement;

    public SQL(){
        try{
            Class.forName(JDBC_DRIVER);

//            System.out.println("Connecting to " + dbName + " database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//            System.out.println("Connected to " +dbName+" database successfully...");
        } catch (Exception e) {
            System.out.println("Problem in connecting to database");
        }
    }

    public boolean loginValidate(String username, String password){
        boolean valid = false;
        String query = "select * from user where username = ? && password = ?";
        ResultSet result = null;

        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                if(username.equals(result.getString("username"))){
                    if (password.equals(result.getString("password"))){
                        valid = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valid;
    }
}
