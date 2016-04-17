package storage.dao;

import storage.jdbc.MySqlConnection;

import java.sql.Connection;

public abstract class DAO<T> {

    public Connection connection = MySqlConnection.getInstance();

    public abstract T get (T object);

    public abstract T create (T object);

    public abstract T update (T object);

    public abstract void delete (T object);
}
