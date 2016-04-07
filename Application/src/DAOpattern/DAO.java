package DAOpattern;

import java.sql.Connection;

public abstract interface DAO<T> {

    public Connection connect = SQLConnection.getInstance();
    public abstract T find(long id);
    public abstract T create(T obj);
    public abstract T update(T obj);
    public abstract void delete(T obj);
}

