package com.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.service.UserService;
import com.web.listener.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ContextListener - it's a class that initialize  a MemoryUserService instance in ServletContext and after
 * using it, destroy those instance
 */
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String STORAGE_INIT_PARAMETER = "storage";

    /**
     * ContextInitialized method initialized a MemoryUserService instance in ServletContext
     *
     * @param sce ServletContextEvent instance
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String storageMode = context.getInitParameter(STORAGE_INIT_PARAMETER);

        LOGGER.debug("Try to initialize service for {} storage mode", storageMode);
        UserService userService;

        userService = ServiceFactory.getUserService(storageMode);
        LOGGER.debug("service initialized. Service: {}", userService);

        context.setAttribute("userService", userService);

    }

    /**
     * ContextDestroyed method destroyed a MemoryUserService instance in ServletContext
     *
     * @param sce ServletContextEvent instance
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("userService");
        LOGGER.info("ServletContext was destroyed");
    }
}
