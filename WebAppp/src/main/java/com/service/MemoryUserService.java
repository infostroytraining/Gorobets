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

public class MemoryUserService implements UserService {

    private UserDAO userDAO;


    public MemoryUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    /**
     * This method  returns a instance of user.
     * @param user
     * @throws ServiceException(DAOException)
     */
    public User add(User user) throws ServiceException {
        User createdUser = null;
        if (user != null) {
            try {
                createdUser = userDAO.create(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return createdUser;
    }

    public List<User> getAll() throws ServiceException {

        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * This method  returns a map with users emails as a key and
     * user surname as a value.
     *
     * @throws  ServiceException(DAOException)
     */

    public Map<String, String> getEmailForEachUser() throws ServiceException {
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
            throw new ServiceException(e);
        }


        return mapEmailUser;
    }
}
