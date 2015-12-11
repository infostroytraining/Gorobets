package com.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dao.UserDAO;
import com.entity.User;

public class UserService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
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
	 * This method returns a map with programming language name as a key and
	 * count of answers for this language as a value.
	 */
	public Map<String, Integer> getStatisticForEachAnswer() {
		Map<String, Integer> statisticMap = new HashMap<>();
		Set<String> emails = new HashSet<>();

		for (User user : getAll()) {
			if (user != null) {
				emails.add(user.getEmail());
			}
		}

		for (String email : emails) {
			int answersCount = 0;
			if (email != null) {
				for (User user : getAll()) {
					if (user != null && email.equals(user.getEmail())) {
						answersCount += 1;
					}
					statisticMap.put(email, answersCount);
				}
			}
		}
		return statisticMap;
	}
}
