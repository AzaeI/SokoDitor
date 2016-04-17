package storage.dao.mysql;

import storage.bean.User;
import storage.dao.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DAO<User> {

    @Override
    public User get(User user) {
        if(user.getUsername() != null){
            user = get(user.getUsername());
        }
        else{
            user = get(user.getId());
        }
        return user;
    }

    public User get(String username){
        User user;
        try {

            user = new User();
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Users WHERE username = '" + username+"'");

            if (result.first()){
                user.setId(result.getInt("id"));
                user.setAccount_activated(result.getBoolean("account_activated"));
                user.setPassword(result.getString("password"));
                user.setUsername(result.getString("username"));
            }
            else
                return null;

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User get(long id){
        User user;
        try {
            user  = new User();
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Users WHERE id = " + id);
            if (result.first()){
                user.setId(result.getInt("id"));
                user.setAccount_activated(result.getBoolean("account_activated"));
                user.setPassword(result.getString("password"));
                user.setUsername(result.getString("username"));
            }
            else
                throw new SQLException();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User create(User object) {
        User user;
        try {
            user  = new User();
            PreparedStatement prepare = this.connection
                    .prepareStatement(
                            "INSERT INTO Users (username, password) VALUES(?, ?)"
                    );
            prepare.setString(1, object.getUsername());
            prepare.setString(2, object.getPassword());
            user.setUsername(object.getUsername());
            user.setPassword(object.getPassword());
            prepare.execute();
            user = get(user.getUsername());
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(User object) {
        User user = new User();
        try {
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("UPDATE Users SET id=?,username=?,password=?,account_activated=? WHERE 1");
            if (result.first()){
                user.setId(result.getInt("id"));
                user.setAccount_activated(result.getBoolean("account_activated"));
                user.setPassword(result.getString("password"));
                user.setUsername(result.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public ArrayList<User> list(User object) {
        return null;
    }

    @Override
    public void delete(User object) {

    }
}
