package com.dao.memory;

import java.util.List;

import com.dao.UserDAO;
import com.dao.storage.UserStorage;
import com.entity.User;

public class MemoryUserDAO implements UserDAO {

	private UserStorage storage;
	
	public MemoryUserDAO(UserStorage storage) {
		this.storage = storage;
	}
	
	@Override
	public User create(User user) {
		return storage.add(user);
	}

	@Override
	public User get(int id) {

		return null;
	}

	@Override
	public User update(User entity) {

		return null;
	}

	@Override
	public void remove(int id) {

		
	}

	@Override
	public List<User> getAll() {
		return storage.all();
	}


	@Override
	public User getUserByUserName(String userName) {
		return null;
	}

	@Override
	public User getUserByUserSurName(String userName) {
		return null;
	}

	@Override
	public User getUserByUserEmail(String userName) {
		return null;
	}

	@Override
	public List<User> getUsersByCity(String language) {
		return null;
	}
}
