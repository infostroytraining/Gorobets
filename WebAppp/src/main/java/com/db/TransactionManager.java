package com.db;


import com.dao.exception.DAOException;
import com.db.exception.TransactionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TransactionManager -it's a class that help execute another methods with different types
 */
public class TransactionManager {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * DoTask method- get transaction and use generic execute method for executing any methods what he wrap
     *
     * @param transaction
     * @param transactionIsolation
     * @param <T>
     * @return <T> result
     * @throws TransactionException
     */
    public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
        try {
            LOGGER.entry(transaction, transactionIsolation);
            LOGGER.info("DB connection  initialized ");

            T result = transaction.execute();
            LOGGER.info("DB connection  closed ");

            LOGGER.exit();
            return result;
        } catch (DAOException e) {
            throw new TransactionException(e);
        }
    }
}
