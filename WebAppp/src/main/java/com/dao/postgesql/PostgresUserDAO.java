package com.dao.postgesql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.UserDAO;
import com.dao.exception.DAOException;
import com.db.ConnectionHolder;
import com.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class PostgresUserDAO implements UserDAO {

    private static final String UPDATE_USER = "UPDATE users SET name = ?, surname = ?, email = ?, password = ? WHERE user_id = ?;";
    private static final String INSERT_USER = "INSERT INTO users(name,surname,email,password) values(?,?,?,?);";
    private static final String SELECT_USER = "SELECT * FROM users WHERE user_id = ?;";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?;";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";


    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Add method - adds User to the DB storage
     *
     * @param user - User instance
     * @return User type
     * @throws DAOException
     */
    @Override
    public User create(User user) throws DAOException {
        LOGGER.entry(user);
        PreparedStatement statement = null;
        Connection connection = ConnectionHolder.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            connection.commit();
        } catch (SQLException ex) {
            LOGGER.error("SQLException during user insert query", ex);
            if (connection != null) {
                try {
                    LOGGER.error("Transaction is being rolled back for user insert query");
                    connection.rollback();
                } catch (SQLException excep) {
                    LOGGER.error("Can't roll back  transaction for user insert query. " + excep.getMessage());
                }
                throw new DAOException(ex);
            }

        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during user insert query :", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for user insert query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(user);
        return user;
    }

    /**
     * Get method - get User from the DB storage by their id
     *
     * @param id - id of User instance with int type
     * @return User type
     * @throws DAOException
     */
    @Override
    public User get(int id) throws DAOException {

        LOGGER.entry(id);
        PreparedStatement statement = null;
        User user = null;
        Connection connection = ConnectionHolder.getConnection();
        try {

            statement = connection.prepareStatement(SELECT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next() && (id == generatedKeys.getInt(1))) {

                user = new User(generatedKeys.getString(2),
                        generatedKeys.getString(3), generatedKeys.getString(4), generatedKeys.getString(5));

            }
        } catch (SQLException ex) {
            LOGGER.error("SQLException during user select query", ex);
            throw new DAOException(ex);
        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during user select query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for user select query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(user);
        return user;

    }

    /**
     * Update method - update User at the DB storage
     *
     * @param entity - User instance
     * @return User type
     * @throws DAOException
     */
    @Override
    public User update(User entity) throws DAOException {
        LOGGER.entry(entity);
        PreparedStatement statement = null;

        User user = null;
        Connection connection = ConnectionHolder.getConnection();
        try {

            statement = connection.prepareStatement(UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
            connection.commit();

            user = get(entity.getId());

        } catch (SQLException ex) {
            LOGGER.error("SQLException during user update query", ex);
            if (connection != null) {
                try {
                    LOGGER.error("Transaction is being rolled back for user update query");
                    connection.rollback();
                } catch (SQLException excep) {
                    LOGGER.error("Can't roll back this transaction for user update query. " + excep.getMessage());
                }
                throw new DAOException(ex);
            }

        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during user update query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for user update query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(user);
        return user;

    }

    /**
     * Remove method - remove User from the DB storage by their id
     *
     * @param id - id of User instance with int type
     * @throws DAOException
     */
    @Override
    public void remove(int id) throws DAOException {

        LOGGER.entry(id);
        PreparedStatement statement = null;
        Connection connection = ConnectionHolder.getConnection();

        try {

            statement = connection.prepareStatement(DELETE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();

            connection.commit();


        } catch (SQLException ex) {
            LOGGER.error("SQLException during user delete query", ex);
            if (connection != null) {
                try {
                    LOGGER.error("Transaction is being rolled back for user delete query");
                    connection.rollback();
                } catch (SQLException excep) {
                    LOGGER.error("Can't roll back this transaction for user delete query. " + excep.getMessage());
                }
                throw new DAOException(ex);
            }

        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during user delete query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for user update query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        if (get(id) == null) {
            LOGGER.exit("User was removed ");
        } else {
            LOGGER.exit("User wasn't removed ");
        }
    }

    /**
     * GetAll method - get all User from the DB storage
     *
     * @return List<T> -list with Users
     * @throws DAOException
     */
    @Override
    public List<User> getAll() throws DAOException {

        PreparedStatement statement = null;
        User user;
        List<User> list = null;
        Connection connection = ConnectionHolder.getConnection();
        try {

            statement = connection.prepareStatement(SELECT_ALL_USERS, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            list = new ArrayList<>();
            while (generatedKeys.next()) {

                user = new User(generatedKeys.getString(2),
                        generatedKeys.getString(3), generatedKeys.getString(4), generatedKeys.getString(5));
                user.setId(generatedKeys.getInt(1));
                list.add(user);
            }

        } catch (SQLException ex) {
            LOGGER.error("SQLException during user select query", ex);
            throw new DAOException(ex);
        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during user select query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for user select query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(list);
        return list;

    }

    /**
     * This method  get a User by userSurName from the DB storage.
     *
     * @param userName
     * @return User
     * @throws DAOException
     */
    @Override
    public User getUserByUserSurName(String userName) throws DAOException {
        return null;
    }

    /**
     * This method  get a User by email from the DB storage.
     *
     * @param email -email of user
     * @return User
     * @throws DAOException
     * @returnUser
     */
    @Override
    public User getUserByUserEmail(String email) throws DAOException {
        LOGGER.entry(email);
        PreparedStatement statement = null;
        User user = null;
        Connection connection = ConnectionHolder.getConnection();
        try {

            statement = connection.prepareStatement(SELECT_USER_BY_EMAIL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, email);

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {

                user = new User(generatedKeys.getString(2),
                        generatedKeys.getString(3), generatedKeys.getString(4), generatedKeys.getString(5));

            }
        } catch (SQLException ex) {
            LOGGER.error("SQLException during user select_by_email query", ex);
            throw new DAOException(ex);
        } catch (NullPointerException exe) {
            LOGGER.error("NullPointerException during user select_by_email query", exe);
            throw new DAOException(exe);
        } finally {
            if (statement != null) {
                try {
                    statement.close();

                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error("Can't close PreparedStatement for user select_by_email query" + statement + " Cause: ", ex);
                    throw new DAOException(ex);
                }
            }

        }
        LOGGER.exit(user);
        return user;

    }
}
