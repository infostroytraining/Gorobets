package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDAO extends DAO<User> {
	

	User getUserByUserSurName(String userName);

	User getUserByUserEmail(String userName);


}
