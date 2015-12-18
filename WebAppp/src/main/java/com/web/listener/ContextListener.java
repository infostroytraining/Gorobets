package com.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.UserDAO;
import com.dao.memory.MemoryUserDAO;
import com.dao.storage.UserStorage;
import com.service.MemoryUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ContextListener - it's a class that initialize  a MemoryUserService instance in ServletContext and after
 * using it, destroy those instance
 */
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * ContextInitialized method initialized a MemoryUserService instance in ServletContext
     *
     * @param sce ServletContextEvent instance
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserStorage storage = new UserStorage();
        UserDAO userDAO = new MemoryUserDAO(storage);
        MemoryUserService memoryUserService = new MemoryUserService(userDAO);
        sce.getServletContext().setAttribute("memoryUserService", memoryUserService);
    }

    /**
     * ContextDestroyed method destroyed a MemoryUserService instance in ServletContext
     *
     * @param sce ServletContextEvent instance
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("memoryUserService");
        LOGGER.info("ServletContext was destroyed");
    }
}
