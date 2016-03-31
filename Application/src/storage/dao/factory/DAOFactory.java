package storage.dao.factory;

import storage.dao.DAO;
import storage.dao.mysql.*;

/**
 * Created by elekhyr on 31/03/16.
 */
public class DAOFactory extends AbstractDAOFactory {

    @Override
    public DAO getFeedBackDAO() {
        return new FeedBackDAO();
    }

    @Override
    public DAO getLevelDAO() {
        return new LevelDAO();
    }

    @Override
    public DAO getUserDAO() {
        return new UserDAO();
    }

    @Override
    public DAO getScoreDAO() {
        return new ScoreDAO();
    }

    @Override
    public DAO getRecoveryQuestionDAO() {
        return new RecoveryQuestionDAO();
    }
}
