package storage.dao.mysql;

import storage.bean.RecoveryQuestion;
import storage.bean.User;
import storage.dao.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecoveryQuestionDAO extends DAO <RecoveryQuestion>{
    @Override
    public RecoveryQuestion get(RecoveryQuestion recoveryQuestion) {
       return null;
    }

    @Override
    public RecoveryQuestion create(RecoveryQuestion object) {

        RecoveryQuestion recovery;
        try {
            recovery = new RecoveryQuestion();
            PreparedStatement prepare = this.connection
                    .prepareStatement(
                            "INSERT INTO Account_recovery (user_id,question, answer) VALUES(?, ?, ?)"
                    );
            prepare.setLong(1, object.getUser_id());
            prepare.setString(2, object.getQuestion());
            prepare.setString(3, object.getAnswer());
            recovery.setUser_id(object.getUser_id());
            recovery.setQuestion(object.getQuestion());
            recovery.setAnswer(object.getAnswer());
            prepare.execute();
            return recovery;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public RecoveryQuestion update(RecoveryQuestion object) {
        return null;
    }

    @Override
    public ArrayList<RecoveryQuestion> list(RecoveryQuestion object) {
        ArrayList<RecoveryQuestion> recoveryList = null;
        RecoveryQuestion recovery;
        try {
            recoveryList = new ArrayList<>();
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Account_recovery WHERE user_id = " + object.getUser_id());
            while (result.next()){
                recovery = new RecoveryQuestion();
                recovery.setUser_id(result.getLong("user_id"));
                recovery.setQuestion(result.getString("question"));
                recovery.setAnswer(result.getString("answer"));
                recoveryList.add(recovery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recoveryList;
    }

    @Override
    public void delete(RecoveryQuestion object) {

    }
}
