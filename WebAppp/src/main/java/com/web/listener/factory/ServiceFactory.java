package com.web.listener.factory;

import java.util.ServiceConfigurationError;

import com.dao.UserDAO;
import com.dao.memory.MemoryUserDAO;
import com.dao.postgesql.PostgresUserDAO;
import com.dao.storage.UserStorage;
import com.db.TransactionManager;
import com.service.MemoryUserService;
import com.service.TransactionalUserService;
import com.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ServiceFactory {

	public static final String MEMORY = "memory";
	public static final String DB = "db";
	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

	private static final Logger LOGGER = LogManager.getLogger();

	public static UserService getUserService(String type) {
		if (type == null || type.isEmpty()) {
			LOGGER.fatal("Could initialize application. Source type is null or empty");
			throw new IllegalArgumentException();
		}
		if (type.equals(MEMORY)) {
			return initMemoryService();
		} else if (type.equals(DB)) {
			loadPostgresDriver();
			return initTransactionalService();
		} else {
			LOGGER.fatal("Could initialize application with source type {}", type);
			throw new ServiceConfigurationError("Could initialize application with source type [" + type + "]");
		}
	}

	private static void loadPostgresDriver() {
		try {
			Class.forName(POSTGRES_DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.fatal("Could load {} driver", POSTGRES_DRIVER);
			throw new ServiceConfigurationError("Could load" + POSTGRES_DRIVER + "driver");
		}
	}

	private static UserService initMemoryService() {
		UserStorage storage = new UserStorage();
		UserDAO answerDAO = new MemoryUserDAO(storage);
		return new MemoryUserService(answerDAO);
	}

	private static UserService initTransactionalService() {
		TransactionManager transactionManager = new TransactionManager();
		UserDAO answerDAO = new PostgresUserDAO();
		return new TransactionalUserService(transactionManager, answerDAO);
	}
}
