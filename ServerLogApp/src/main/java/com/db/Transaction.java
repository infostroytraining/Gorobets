package com.db;

import com.dao.exception.DAOException;

public interface Transaction<T> {

	 T execute() throws DAOException;

}
