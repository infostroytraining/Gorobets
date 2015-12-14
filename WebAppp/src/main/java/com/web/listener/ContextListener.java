package com.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.UserDAO;
import com.dao.memory.MemoryUserDAO;
import com.dao.storage.UserStorage;
import com.service.MemoryUserService;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		UserStorage storage = new UserStorage();
		UserDAO userDAO = new MemoryUserDAO(storage);
		MemoryUserService memoryUserService = new MemoryUserService(userDAO);
		sce.getServletContext().setAttribute("memoryUserService", memoryUserService);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// we need to  add log there
	}
}