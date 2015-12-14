package com.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dao.UserDAO;
import com.entity.User;

public class MemoryUserService implements UserServices{

	private UserDAO userDAO;

	public MemoryUserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User add(User user) {
		User createdUser = null;
		if (user != null) {
			createdUser = userDAO.create(user);
		}
		return createdUser;
	}

	public List<User> getAll() {

		return userDAO.getAll();
	}

	/**
	 * This method  returns a map with users emails as a key and
	 * user surname as a value.
	 */
	public Map<String, String> getEmailForEachUser() {
		Map<String, String> mapEmailUser = new HashMap<>();
		Set<String> emails = new HashSet<>();

		for (User user : getAll()) {
			if (user != null) {
				emails.add(user.getEmail());
			}
		}

		for (String email : emails) {

			if (email != null) {
				for (User user : getAll()) {
					if (user != null && email.equals(user.getEmail())) {
                        mapEmailUser.put(email, user.getSurname());
					}

				}
			}
		}
		return mapEmailUser;
	}
}
