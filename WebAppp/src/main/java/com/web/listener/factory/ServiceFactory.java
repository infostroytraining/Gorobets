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

    /**
     * GetUserService method rely on type of storage return appropriate service
     *
     * @param type -it's a type of storage
     * @return UserService type
     */
    public static UserService getUserService(String type) {
        LOGGER.entry(type);
        if (type == null || type.isEmpty()) {
            LOGGER.fatal("Couldn't initialize application. Source type is null or empty");
            throw new IllegalArgumentException();
        }
        if (type.equals(MEMORY)) {
            LOGGER.info("MemoryService initialized");
            return initMemoryService();
        } else if (type.equals(DB)) {
            loadPostgresDriver();
            LOGGER.info("TransactionService initialized");
            return initTransactionalService();
        } else {
            LOGGER.fatal("Couldn't initialize application with source type {}", type);
            throw new ServiceConfigurationError("Couldn't initialize application with source type [" + type + "]");
        }
    }

    /**
     * LoadPostgresDriver method dose registry for DB Driver
     */
    private static void loadPostgresDriver() {

        try {
            Class.forName(POSTGRES_DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("Couldn't load {} driver", POSTGRES_DRIVER);
            throw new ServiceConfigurationError("Couldn't load" + POSTGRES_DRIVER + "driver");
        }
    }

    /**
     * initMemoryService - initialize memory storage
     *
     * @return UserService
     */
    private static UserService initMemoryService() {
        UserStorage storage = new UserStorage();
        UserDAO answerDAO = new MemoryUserDAO(storage);
        return new MemoryUserService(answerDAO);
    }

    /**
     * initTransactionalService - initialized PostgresDB
     *
     * @return UserService
     */
    private static UserService initTransactionalService() {
        TransactionManager transactionManager = new TransactionManager();
        UserDAO answerDAO = new PostgresUserDAO();
        return new TransactionalUserService(transactionManager, answerDAO);
    }
}
