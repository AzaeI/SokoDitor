package storage.dao.mysql;

import storage.bean.User;
import storage.dao.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DAO<User> {

    @Override
    public User get(long id) {
        User user = new User();
        try {
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Users WHERE id = " + id);
            if(result.first())
                user.setId(result.getInt("id"));
            user.setAccount_activated(result.getBoolean("account_activated"));
            user.setMail(result.getString("mail"));
            user.setPassword(result.getString("password"));
            user.setUsername(result.getString("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public void delete(User object) {

    }
}
