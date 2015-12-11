package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDAO extends DAO<User> {
	
	User getUserByUserName(String userName);

	User getUserByUserSurName(String userName);

	User getUserByUserEmail(String userName);

	List<User> getUsersByCity (String language);
}
