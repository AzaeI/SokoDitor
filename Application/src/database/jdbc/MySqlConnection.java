package database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String URL = "jdbc:mysql://localhost:5432/db";

    private static final String USERNAME = "player";

    private static final String PASSWORD = "player";

    private static Connection connection;


    public static Connection getInstance(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  connection;
    }

}
