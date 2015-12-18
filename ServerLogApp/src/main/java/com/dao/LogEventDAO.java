package com.dao;

import java.util.List;
import java.util.Map;

import com.dao.exception.DAOException;
import com.entity.LogEvent;

public interface LogEventDAO extends DAO<LogEvent> {
	
	LogEvent getLastLogEvent() throws DAOException;

	Map<Integer,LogEvent> getAll() throws DAOException;


}
