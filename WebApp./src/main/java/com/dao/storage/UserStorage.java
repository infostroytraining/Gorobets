package com.dao.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.entity.User;

public class UserStorage {

	private Map<Integer, User> storage;
	private AtomicInteger id = new AtomicInteger();

	public UserStorage() {
		storage = new HashMap<>();
	}

	public User add(User user) {
		int id = generateId();
		user.setId(id);
		storage.put(id, user);
		return user;
	}

	public List<User> all() {
		return new ArrayList<>(storage.values());
	}

	private int generateId() {
		return id.incrementAndGet();
	}
}
