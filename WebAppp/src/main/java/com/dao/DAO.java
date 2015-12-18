package com.dao;

import com.dao.exception.DAOException;

import java.util.List;

/**
 * DAO - data access object interface for connection with DB.
 *
 * @param <T> - generic type of object
 */
public interface DAO<T> {
    /**
     * Create method - adds object to the storage
     *
     * @param entity - object instance with <T> type
     * @return <T> type
     * @throws DAOException
     */
    T create(T entity) throws DAOException;

    /**
     * Get method - get object from the storage
     *
     * @param id - id of object instance with int type
     * @return <T> type
     * @throws DAOException
     */
    T get(int id) throws DAOException;

    /**
     * Update method - update object at the storage
     *
     * @param entity - object instance with <T> type
     * @return <T> type
     * @throws DAOException
     */
    T update(T entity) throws DAOException;

    /**
     * Remove method - remove object from the storage
     *
     * @param id - id of object instance with int type
     * @return <T> type
     * @throws DAOException
     */
    void remove(int id) throws DAOException;

    /**
     * GetAll method - get all objects from the storage
     *
     * @return List<T> -list with objects
     * @throws DAOException
     */
    List<T> getAll() throws DAOException;
}
