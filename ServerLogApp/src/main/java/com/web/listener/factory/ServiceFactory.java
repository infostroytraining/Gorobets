package com.web.listener.factory;

import java.util.ServiceConfigurationError;

import com.dao.memory.MemoryLogEventDAO;
import com.service.LogEventService;
import com.service.TransactionalLogEventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dao.LogEventDAO;
import com.dao.postgesql.PostgresLogEventDAO;
import com.dao.storage.LogEventStorage;
import com.db.TransactionManager;
import com.service.MemoryLogEventService;

public class ServiceFactory {

	public static final String MEMORY = "memory";
	public static final String DB = "db";
	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

	private static Logger logger = LogManager.getLogger();

	public static LogEventService getLogEventService(String type) {
		if (type == null || type.isEmpty()) {
			logger.fatal("Could initialize application. Source type is null or empty");
			throw new IllegalArgumentException();
		}
		if (type.equals(MEMORY)) {
			return initMemoryService();
		} else if (type.equals(DB)) {
			loadPostgresDriver();
			return initTransactionalService();
		} else {
			logger.fatal("Could initialize application with source type {}", type);
			throw new ServiceConfigurationError("Could initialize application with source type [" + type + "]");
		}
	}

	private static void loadPostgresDriver() {
		try {
			Class.forName(POSTGRES_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.fatal("Could load {} driver", POSTGRES_DRIVER);
			throw new ServiceConfigurationError("Could load " + POSTGRES_DRIVER + " driver");
		}
	}

	private static LogEventService initMemoryService() {
		LogEventStorage storage = new LogEventStorage();
		LogEventDAO logEventDAO = new MemoryLogEventDAO(storage);
		return new MemoryLogEventService(logEventDAO);
	}

	private static LogEventService initTransactionalService() {
		TransactionManager transactionManager = new TransactionManager();
		LogEventDAO logEventDAO = new PostgresLogEventDAO();
		return new TransactionalLogEventService(transactionManager, logEventDAO);
	}
}
