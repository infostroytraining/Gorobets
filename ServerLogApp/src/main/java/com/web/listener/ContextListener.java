package com.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.service.LogEventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.listener.factory.ServiceFactory;

public class ContextListener implements ServletContextListener {

	private static Logger LOGGER = LogManager.getLogger(ContextListener.class);
	private static final String STORAGE_INIT_PARAMETER = "memory";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String storageMode = context.getInitParameter(STORAGE_INIT_PARAMETER);

		LOGGER.debug("Try to initialize service for {} storage mode", storageMode);
		LogEventService logEventService = ServiceFactory.getLogEventService(storageMode);
		LOGGER.debug("service initialized. Service: {}", logEventService);

		context.setAttribute("logEventService", logEventService);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.debug("servlet context is destroyed");
	}
}
