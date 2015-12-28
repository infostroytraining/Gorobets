package com.service;



import com.entity.LogEvent;
import com.service.exception.ServiceException;

import java.util.Map;

public interface LogEventService {

	 LogEvent add(LogEvent logEvent) throws ServiceException;

	Map<Integer, LogEvent> getAll() throws ServiceException;


}
