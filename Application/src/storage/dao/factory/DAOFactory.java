package storage.dao.factory;

import storage.bean.*;
import storage.dao.DAO;
import storage.dao.mysql.*;

/**
 * Created by elekhyr on 31/03/16.
 */
public class DAOFactory extends AbstractDAOFactory {

    @Override
    public DAO<Feedback> getFeedBackDAO() {
        return new FeedBackDAO();
    }

    @Override
    public DAO<Level> getLevelDAO() {
        return new LevelDAO();
    }

    @Override
    public DAO<User> getUserDAO() {
        return new UserDAO();
    }

    @Override
    public DAO<Score> getScoreDAO() {
        return new ScoreDAO();
    }

    @Override
    public DAO<RecoveryQuestion> getRecoveryQuestionDAO() {
        return new RecoveryQuestionDAO();
    }

}
