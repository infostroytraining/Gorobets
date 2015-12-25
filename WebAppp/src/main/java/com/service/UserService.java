package com.service;

import com.entity.User;
import com.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * UserService - interface that give behavior for actions with storage at service level
 * There is three methods : add(), getAll(), getEmailForEachUser().
 */
public interface UserService {
    /**
     * Add method - adds User to storage
     *
     * @param user User type instance
     * @return User user
     * @throws ServiceException
     */
    User add(User user) throws ServiceException;

    /**
     * GetAll method -gets all users from the the storage
     *
     * @return List<User> of users
     * @throws ServiceException
     */
    List<User> getAll() throws ServiceException;

    /**
     * GetEmailForEachUser method - gets map of pair email,surname of users
     *
     * @return Map<String, String> -map with K-email and V- user Surname
     * @throws ServiceException
     */
    Map<String, String> getEmailForEachUser() throws ServiceException;

    void remove(int id) throws ServiceException;
}
