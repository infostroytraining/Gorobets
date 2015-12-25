package com.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dao.UserDAO;
import com.dao.exception.DAOException;
import com.entity.User;
import com.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MemoryUserService implements UserService {

    private UserDAO userDAO;

    private static final Logger LOGGER = LogManager.getLogger();

    public MemoryUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * This method  add  a instance of user to the memory storage.
     *
     * @param user
     * @return User instance
     * @throws ServiceException
     */
    public User add(User user) throws ServiceException {
        LOGGER.entry(user);
        User createdUser = null;
        if (user != null) {
            try {
                createdUser = userDAO.create(user);
            } catch (DAOException e) {
                LOGGER.error("Exception in Add method at MemoryUserService class");
                throw new ServiceException(e);
            }
        }
        LOGGER.exit(createdUser);
        return createdUser;
    }

    /**
     * Remove method - remove User from the memory storage (Map<Integer,String>) by their id
     *
     * @param id - id of User instance with int type
     */
    @Override
    public void remove(int id) throws ServiceException {
        try {
            userDAO.remove(id);
        } catch (DAOException e) {
            LOGGER.error("Exception in remove method at MemoryUserService class");
            throw new ServiceException(e);
        }
    }

    /**
     * GetAll method -gets all users from the the memory storage
     *
     * @return List<User> of users
     * @throws ServiceException
     */
    public List<User> getAll() throws ServiceException {
        LOGGER.entry("Entry to getAll method at MemoryUserService");
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error("Exception in getAll method at MemoryUserService class");
            throw new ServiceException(e);
        }
    }

    /**
     * This method  get a map with users emails as a key and
     * user surname as a value from the memory storage.
     *
     * @return Map<String, String> -map with K-email and V- user Surname
     * @throws ServiceException(DAOException)
     */

    public Map<String, String> getEmailForEachUser() throws ServiceException {
        LOGGER.entry("Entry to getEmailForEachUser method at MemoryUserService");
        Map<String, String> mapEmailUser = new HashMap<>();
        Set<String> emails = new HashSet<>();

        try {
            for (User user : getAll()) {
                if (user != null) {
                    emails.add(user.getEmail());
                }

            }
            for (String email : emails) {

                if (email != null) {

                    for (User user : getAll()) {
                        if (user != null && email.equals(user.getEmail())) {
                            mapEmailUser.put(email, user.getSurname());
                        }

                    }
                }
            }
        } catch (ServiceException e) {
            LOGGER.error("Exception at getEmailForEachUser method at MemoryUserService");
            throw new ServiceException(e);
        }

        LOGGER.exit(mapEmailUser);
        return mapEmailUser;
    }
}
