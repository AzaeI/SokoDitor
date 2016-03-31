package storage.dao.factory;

import storage.dao.DAO;

/**
 * Created by elekhyr on 31/03/16.
 */
public abstract class AbstractDAOFactory {

    public abstract DAO getFeedBackDAO();
    public abstract DAO getLevelDAO();
    public abstract DAO getUserDAO();
    public abstract DAO getScoreDAO();
    public abstract DAO getRecoveryQuestionDAO();

    public static AbstractDAOFactory getFactory(FactoryType type){

        if(type.equals(FactoryType.MYSQL_DAO))
            return new DAOFactory();

        if(type.equals(FactoryType.XML_DAO))
            return new XMLDAOFactory();

        return null;
    }
}
