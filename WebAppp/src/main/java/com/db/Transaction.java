package com.db;

import com.dao.exception.DAOException;


public interface Transaction<T> {

	public T execute() throws DAOException, DAOException;

}
