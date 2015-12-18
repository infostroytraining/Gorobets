package com.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.dao.UserDAO;
import com.dao.exception.DAOException;
import com.db.Transaction;
import com.db.TransactionManager;
import com.db.exception.TransactionException;
import com.entity.User;
import com.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TransactionalUserService- it's a class that use TransactionManager for getting data from a DB storage
 */
public class TransactionalUserService implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    private TransactionManager transactionManager;
    private UserDAO userDAO;

    public TransactionalUserService(TransactionManager transactionManager, UserDAO userDAO) {
        this.transactionManager = transactionManager;
        this.userDAO = userDAO;
    }

    /**
     * Add method - adds User to storage
     *
     * @param user User type instance
     * @return User user
     * @throws ServiceException
     */
    @Override
    public User add(final User user) throws ServiceException {
        LOGGER.entry(user);
        try {
            return transactionManager.doTask(new Transaction<User>() {
                @Override
                public User execute() throws DAOException {
                    return userDAO.create(user);
                }
            }, Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            LOGGER.error("Transaction exception in add method at TransactionUserManager");
            throw new ServiceException(e);
        }
    }


    /**
     * GetEmailForEachUser method - gets map of pair email,surname of users
     *
     * @return Map<String, String> -map with K-email and V- user Surname
     * @throws ServiceException
     */
    @Override
    public Map<String, String> getEmailForEachUser() throws ServiceException {
        return null;
    }

    /**
     * GetAll method -gets all users from the the storage
     *
     * @return List<User> of users
     * @throws ServiceException
     */
    @Override
    public List<User> getAll() throws ServiceException {
        try {

            return transactionManager.doTask(new Transaction<List<User>>() {
                @Override
                public List<User> execute() throws DAOException {
                    return userDAO.getAll();
                }
            }, Connection.TRANSACTION_READ_COMMITTED);

        } catch (TransactionException e) {
            LOGGER.error("Exception in getEmailForEachUser method at TransactionalUserService class");
            throw new ServiceException(e);
        }
    }
}
