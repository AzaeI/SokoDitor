package storage.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String host = "mysql.alwaysdata.com";
    private static final String port = "3306";
    private static final String database = "elekhyr_sokoditor";
    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
    private static final String user = "elekhyr_reader";
    private static final String passwd = "reader";
    private static Connection connect;

    public static Connection getInstance() {
        if (connect == null) {
            try {
                connect = DriverManager.getConnection(url, user, passwd);
                connect.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
