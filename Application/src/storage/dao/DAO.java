package storage.dao;

import storage.jdbc.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {

    public Connection connection = MySqlConnection.getInstance();

    public abstract T get (T object);

    public abstract T create (T object);

    public abstract T update (T object);

    public abstract ArrayList<T> list (T object);

    public abstract void delete (T object);

    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}