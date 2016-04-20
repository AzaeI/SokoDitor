package storage.dao.mysql;

import storage.bean.Level;
import storage.dao.DAO;

import javax.swing.text.Document;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LevelDAO extends DAO<Level>{

    @Override
    public Level get(Level level) {

        try {
            ResultSet result;
            result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT level_id, name, user_id, rank, is_genuine, file FROM Level ");


            if (result.first()){
                level.setFile(null);
                level.setId(result.getLong(1));
                level.setUser(result.getString(3));
                level.setName(result.getString(2));
                level.setRank(result.getInt(4));
                level.setIs_genuine(result.getBoolean(5));
                level.setFile(result.getBlob(6).getBinaryStream());
            }

            return level;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Level create(Level object) {
        Level user;
        try {
            user  = new Level();
            PreparedStatement prepare = this.connection
                    .prepareStatement(
                            "INSERT INTO Level (name, user_id) VALUES(?, ?)"
                    );
            /*prepare.setString(1, object.getUsername());
            prepare.setString(2, object.getPassword());
            user.setUsername(object.getUsername());
            user.setPassword(object.getPassword());
            prepare.execute();
            user = get(user.getUsername());*/
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Level update(Level object) {
        return null;
    }

    @Override
    public ArrayList<Level> list(Level object) {
        Level level;
        ArrayList<Level> levels;
        try {
            levels = new ArrayList<>();
            level = new Level();
            ResultSet result;
            if (object.getUser() == null){
                result = this.connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT level_id, name, user_id, rank, is_genuine FROM Level ");
            }
            else{
                result = this.connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT level_id, name, user_id, rank, is_genuine FROM Level WHERE user_id = '" + object.getUser()+"'");
            }


            while (result.next()){
                level.setFile(null);
                level.setId(result.getLong(1));
                level.setUser(result.getString(3));
                level.setName(result.getString(2));
                level.setRank(result.getInt(4));
                level.setIs_genuine(result.getBoolean(5));
                levels.add(level);
            }

            return levels;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Level object) {

    }
}
