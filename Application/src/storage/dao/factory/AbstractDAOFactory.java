package storage.dao.factory;

import storage.bean.*;
import storage.dao.DAO;

/**
 * Created by elekhyr on 31/03/16.
 */
public abstract class AbstractDAOFactory {

    public abstract DAO<Feedback> getFeedBackDAO();
    public abstract DAO<Level> getLevelDAO();
    public abstract DAO<User> getUserDAO();
    public abstract DAO<Score> getScoreDAO();
    public abstract DAO<RecoveryQuestion> getRecoveryQuestionDAO();

    public static AbstractDAOFactory getFactory(FactoryType type){

        if(type.equals(FactoryType.MYSQL_DAO))
            return new DAOFactory();

        return null;
    }
}
