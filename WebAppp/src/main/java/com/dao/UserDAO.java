package com.dao;

import java.util.List;

import com.dao.exception.DAOException;
import com.entity.User;

public interface UserDAO extends DAO<User>  {
	

	User getUserByUserSurName(String userName) throws DAOException;

	User getUserByUserEmail(String userName) throws DAOException;


}
