package com.dao;

import java.util.List;

import com.dao.exception.DAOException;

public interface DAO<T> {

	T create(T entity) throws DAOException;

	T get(int id) throws DAOException;


	void remove(int id) throws DAOException;


}
