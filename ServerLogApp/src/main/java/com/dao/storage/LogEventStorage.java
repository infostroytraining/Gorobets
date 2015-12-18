package com.dao.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.entity.LogEvent;

public class LogEventStorage {

	private Map<Integer, LogEvent> storage;
	private AtomicInteger id = new AtomicInteger();

	public LogEventStorage() {
		storage = new HashMap<>();
	}

	public LogEvent add(LogEvent logEvent) {
		int id = generateId();
		logEvent.setId(id);
		storage.put(id, logEvent);
		return logEvent;
	}

	public Map<Integer,LogEvent> all() {
		return storage;
	}

	private int generateId() {
		return id.incrementAndGet();
	}
}
