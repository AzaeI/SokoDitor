package storage.dao.mysql;

import storage.bean.Feedback;
import storage.bean.Level;
import storage.bean.User;
import storage.dao.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedBackDAO extends DAO<Feedback>{

    @Override
    public Feedback get(Feedback f) {
        Feedback feedback;
        try {
            feedback = new Feedback();
            ResultSet result;

            result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT user_id, level_id, rating, commentary FROM Feedback WHERE user_id = '"+f.getUser_id()+"' " +
                    "AND level_id = '"+f.getLevel_id()+"'");



            if (result.first()){
                feedback.setUser_id(result.getLong(1));
                feedback.setLevel_id(result.getLong(2));
                feedback.setSatisfaction_ladder(result.getShort(3));
                feedback.setCommentary(result.getString(4));
            }

            return feedback;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Feedback create(Feedback object) {
        try {
            PreparedStatement prepare = this.connection
                    .prepareStatement(
                            "INSERT INTO Feedback (user_id, level_id, commentary, rating) VALUES(?, ?, ?, ?)"
                    );
            prepare.setLong(1, object.getUser_id());
            prepare.setLong(2, object.getLevel_id());
            prepare.setShort(4, object.getSatisfaction_ladder());
            prepare.setString(3, object.getCommentary());
            prepare.execute();

            return object;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Feedback update(Feedback object) {
        return null;
    }

    @Override
    public ArrayList<Feedback> list(Feedback object) {
        Feedback feedback;
        ArrayList<Feedback> feedbacks;
        try {
            feedbacks = new ArrayList<>();
            feedback = new Feedback();
            ResultSet result;

            result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT user_id, level_id, rating, commentary FROM Feedback");



            while (result.next()){
                feedback.setUser_id(result.getLong(1));
                feedback.setLevel_id(result.getLong(2));
                feedback.setSatisfaction_ladder(result.getShort(3));
                feedback.setCommentary(result.getString(4));
                feedbacks.add(feedback);
            }

            return feedbacks;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Feedback object) {

    }
}
