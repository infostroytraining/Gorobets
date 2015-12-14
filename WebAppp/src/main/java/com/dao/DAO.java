package com.dao;

import java.util.List;

public interface DAO<T> {

	T create(T entity);

	T get(int id);// read

	T update(T entity);

	boolean remove(int id);

	List<T> getAll();
}
