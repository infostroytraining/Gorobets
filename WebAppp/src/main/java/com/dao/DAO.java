package com.dao;

import com.dao.exception.DAOException;

import java.util.List;

public interface DAO<T> {

	T create(T entity) throws DAOException;

	T get(int id) throws DAOException;// read

	T update(T entity) throws DAOException;

	void remove(int id) throws DAOException;

	List<T> getAll() throws DAOException;
}
