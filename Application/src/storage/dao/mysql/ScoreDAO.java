package storage.dao.mysql;

import storage.bean.Level;
import storage.bean.Score;
import storage.dao.DAO;
import storage.dao.factory.DAOFactory;
import storage.dao.factory.FactoryType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreDAO extends DAO<Score> {
    @Override
    public Score get(Score score) {
        Score scoreNew = new Score();
        try {
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Score WHERE level_id = " + score.getLevel());
            if (result.first())
                scoreNew.setLevel(result.getLong("level_id"));
            scoreNew.setScore(result.getFloat("score"));
            scoreNew.setUsername(result.getLong("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreNew;
    }

    @Override
    public Score create(Score object) {
        Score score;
        try {
            score = new Score();
            PreparedStatement prepare = this.connection
                    .prepareStatement(
                            "INSERT INTO Score(level_id, user_id, score) VALUES (?,?,?)"
                    );

            prepare.setLong(1, object.getLevel());
            prepare.setLong(2, object.getUsername());
            prepare.setFloat(3, object.getScore());

            score.setLevel(object.getLevel());
            score.setUsername(object.getUsername());
            score.setScore(object.getScore());

            prepare.execute();

            return score;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Score update(Score object) {
        try {
            this.connection
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                    "UPDATE Score SET level_id="
                            + object.getLevel()
                            + ",user_id="
                            + object.getUsername()
                            // + ",rank="
                            // + object.getScore()
                            + ",score="
                            + object.getScore()
                            + " WHERE 1"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public ArrayList<Score> list(Score object) {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            ResultSet result = this.connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT score FROM Score WHERE level_id = " + object.getLevel() + " ORDER BY score DESC ");

            while (result.next()){
                Score scoreNew = new Score();
                scoreNew.setLevel(object.getLevel());
                scoreNew.setScore(result.getFloat(1));
                scores.add(scoreNew);
            }

            return scores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Score object) {

    }
}
