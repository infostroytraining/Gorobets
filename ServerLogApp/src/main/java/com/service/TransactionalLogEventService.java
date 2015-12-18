package com.service;

import java.sql.Connection;
import java.util.Map;

import com.dao.LogEventDAO;
import com.dao.exception.DAOException;
import com.db.Transaction;
import com.db.TransactionManager;
import com.db.exception.TransactionException;
import com.entity.LogEvent;
import com.service.exception.ServiceException;

public class TransactionalLogEventService implements LogEventService {

    private TransactionManager transactionManager;
    private LogEventDAO logEventDAO;

    public TransactionalLogEventService(TransactionManager transactionManager, LogEventDAO logEventDAO) {
        this.transactionManager = transactionManager;
        this.logEventDAO = logEventDAO;
    }

    @Override
    public LogEvent add(final LogEvent logEvent) throws ServiceException {
        try {
            return transactionManager.doTask(new Transaction<LogEvent>() {
                @Override
                public LogEvent execute() throws DAOException {
                    return logEventDAO.create(logEvent);
                }
            }, Connection.TRANSACTION_READ_COMMITTED);
        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Integer, LogEvent> getAll() throws ServiceException {
        try {

                return transactionManager.doTask(new Transaction<Map<Integer, LogEvent>>() {
                    @Override
                    public Map<Integer, LogEvent> execute() throws DAOException {
                        return logEventDAO.getAll();
                    }
                }, Connection.TRANSACTION_READ_COMMITTED);

        } catch (TransactionException e) {
            throw new ServiceException(e);
        }
    }

}
