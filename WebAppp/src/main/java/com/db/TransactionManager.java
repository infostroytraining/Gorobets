package com.db;


import com.dao.exception.DAOException;
import com.db.exception.TransactionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usersdb", "postgres", "programmer88");
            System.out.println(connection +"1");
            connection.setAutoCommit(false);
            ConnectionHolder.setConnection(connection);
            connection.setTransactionIsolation(transactionIsolation);
            T value = transaction.execute();
            connection.commit();
            return value;
        } catch (SQLException | DAOException exeption) {
            tryToRollback(connection);
            throw new TransactionException(exeption);
        } finally {
            tryToCloseConnection(connection);
        }
    }

    private void tryToRollback(Connection connection) throws TransactionException {
        if (connection != null) {
            try {
                LOGGER.info("Try to rollback transaction");
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Error while rolling back the connection", e);
                throw new TransactionException(e);
            }
        }
    }

    private void tryToCloseConnection(Connection connection) throws TransactionException {
        ConnectionHolder.setConnection(null);
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error while closing the connection", e);
            throw new TransactionException(e);
        }
    }
}

