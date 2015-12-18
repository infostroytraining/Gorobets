package com.dao.postgesql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.LogEventDAO;
import com.dao.exception.DAOException;
import com.entity.LogEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db.ConnectionHolder;

public class PostgresLogEventDAO implements LogEventDAO {


    private static final String INSERT_LOG_EVENT = "INSERT INTO log_events(log_value) values(?);";
    private static final String SELECT_LOG_EVENT = "SELECT * FROM log_events WHERE log_event_id = ?;";
    private static final String SELECT_ALL_LOG_EVENTS = "SELECT * FROM log_events;";
    private static final String DELETE_LOG_EVENT = "DELETE FROM log_events WHERE log_event_id = ?;";


    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public LogEvent create(LogEvent logEvent) throws DAOException {
        LOGGER.entry(logEvent);
        PreparedStatement statement = null;
        Connection connection = ConnectionHolder.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_LOG_EVENT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, logEvent.getLogValue());


            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                logEvent.setId(generatedKeys.getInt(1));
            }
            connection.commit();
        } catch (SQLException ex) {
            LOGGER.error("SQLException during logEvent insert query", ex);
            if (connection != null) {
                try {
                    LOGGER.error("Transaction is being rolled back for logEvent insert query");
                    connection.rollback();
                } catch (SQLException excep) {
                    LOGGER.error("Can't roll back  transaction for logEvent insert query. " + excep.getMessage());
                }
                throw new DAOException(ex);
            }

        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during logEvent insert query :", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for logEvent insert query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(logEvent);
        return logEvent;
    }


    @Override
    public LogEvent get(int id) throws DAOException {

        LOGGER.entry(id);
        PreparedStatement statement = null;
        LogEvent logEvent = null;
        Connection connection = ConnectionHolder.getConnection();
        try {

            statement = connection.prepareStatement(SELECT_LOG_EVENT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next() && (id == generatedKeys.getInt(1))) {

                logEvent = new LogEvent(generatedKeys.getString(2));

            }
        } catch (SQLException ex) {
            LOGGER.error("SQLException during logEvent select query", ex);
            throw new DAOException(ex);
        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during logEvent select query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for logEvent select query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(logEvent);
        return logEvent;

    }


    @Override
    public void remove(int id) throws DAOException {

        LOGGER.entry(id);
        PreparedStatement statement = null;
        Connection connection = ConnectionHolder.getConnection();

        try {

            statement = connection.prepareStatement(DELETE_LOG_EVENT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();

            connection.commit();


        } catch (SQLException ex) {
            LOGGER.error("SQLException during logEvent delete query", ex);
            if (connection != null) {
                try {
                    LOGGER.error("Transaction is being rolled back for logEvent delete query");
                    connection.rollback();
                } catch (SQLException excep) {
                    LOGGER.error("Can't roll back this transaction for logEvent delete query. " + excep.getMessage());
                }
                throw new DAOException(ex);
            }

        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during logEvent delete query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for logEvent update query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        if (get(id) == null) {
            LOGGER.exit("LogEvent was removed ");
        } else {
            LOGGER.exit("LogEvent wasn't removed ");
        }
    }

    @Override
    public LogEvent getLastLogEvent() throws DAOException {

        PreparedStatement statement = null;
        LogEvent logEvent = null;

        Connection connection = ConnectionHolder.getConnection();
        try {

            statement = connection.prepareStatement(SELECT_ALL_LOG_EVENTS, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            while (generatedKeys.next()) {
                if (generatedKeys.isLast()) {
                    logEvent = new LogEvent(generatedKeys.getString(2));
                }
            }

        } catch (SQLException ex) {
            LOGGER.error("SQLException during logEvent select query", ex);
            throw new DAOException(ex);
        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during logEvent select query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for logEvent select query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(logEvent);
        return logEvent;

    }

    @Override
    public Map<Integer,LogEvent> getAll() throws DAOException {

        PreparedStatement statement = null;
        LogEvent logEvent;
        Map<Integer,LogEvent> logEventMap = null;
        Connection connection = ConnectionHolder.getConnection();
        try {

            statement = connection.prepareStatement(SELECT_ALL_LOG_EVENTS, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            logEventMap = new HashMap<>();
            while (generatedKeys.next()) {

                logEvent = new LogEvent(generatedKeys.getString(2));
                logEvent.setId(generatedKeys.getInt(1));
                logEventMap.put(generatedKeys.getInt(1),logEvent);
            }

        } catch (SQLException ex) {
            LOGGER.error("SQLException during logEvent select query", ex);
            throw new DAOException(ex);
        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during logEvent select query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for logEvent select query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(logEventMap);
        return logEventMap;

    }


}
