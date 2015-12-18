package com.db;

import com.dao.exception.DAOException;

/**
 * Transaction -it's a interface for flexible executing different methods
 *
 * @param <T>
 */
public interface Transaction<T> {

    /**
     * Execute method - execute some function that Transaction interface get like a generic type
     *
     * @return
     * @throws DAOException
     */
    T execute() throws DAOException;

}
