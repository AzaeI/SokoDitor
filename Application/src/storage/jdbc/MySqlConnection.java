package storage.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static String host = "mysql.alwaysdata.com";
    private static String port = "3306";
    private static String database = "elekhyr_sokoditor";
    private static String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    private static String user = "elekhyr_reader";
    private static String passwd = "reader";
    private static Connection connect;

    public static Connection getInstance() {
        System.out.println("Tentative de connection");
        if (connect == null) {
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connection réussi");
        return connect;
    }
}
