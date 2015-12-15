package com.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.dao.UserDAO;
import com.dao.exception.DAOException;
import com.db.Transaction;
import com.db.TransactionManager;
import com.db.exception.TransactionException;
import com.entity.User;

import com.service.exception.ServiceException;

public class TransactionalUserService implements UserService {

	private TransactionManager transactionManager;
	private UserDAO userDAO;

	public TransactionalUserService(TransactionManager transactionManager, UserDAO userDAO) {
		this.transactionManager = transactionManager;
		this.userDAO = userDAO;
	}

	@Override
	public User add(final User user) throws ServiceException {
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return userDAO.create(user);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	public Map<String, Integer> getStatisticForEachAnswer() {
		return new HashMap<>();
	}



	@Override
	public Map<String, String> getEmailForEachUser() throws ServiceException {
		return null;
	}
}
