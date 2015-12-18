package com.dao;

import com.dao.exception.DAOException;
import com.entity.User;

/**
 * UserDAO interface - data access object interface specified for User type for connection with DB.
 */
public interface UserDAO extends DAO<User> {

    /**
     * This method  get a User by userSurName from the storage.
     *
     * @param userSurName -SurName of user
     * @return User
     * @throws DAOException
     */
    User getUserByUserSurName(String userSurName) throws DAOException;

    /**
     * This method  get a User by email from the storage.
     *
     * @param email -email of user
     * @return User
     * @throws DAOException
     */
    User getUserByUserEmail(String email) throws DAOException;


}
