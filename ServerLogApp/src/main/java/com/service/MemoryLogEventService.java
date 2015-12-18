package com.service;

import java.util.Map;

import com.entity.LogEvent;
import com.dao.LogEventDAO;
import com.dao.exception.DAOException;
import com.service.exception.ServiceException;

public class MemoryLogEventService implements LogEventService {

	private LogEventDAO logEventDAO;

	public MemoryLogEventService(LogEventDAO logEventDAO) {
		this.logEventDAO = logEventDAO;
	}

	public Map<Integer, LogEvent> getAll() throws ServiceException {
		try {
			return logEventDAO.getAll();
		} catch (DAOException e) {
			throw  new ServiceException(e);
		}
	}

	@Override
	public LogEvent add(LogEvent logEvent) throws ServiceException {
		LogEvent createdLogEvent = null;
		if (logEvent != null) {
			try {
				createdLogEvent = logEventDAO.create(logEvent);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return createdLogEvent;
	}


}
