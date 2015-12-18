package com.dao.exception;

/**
 * DAOException class- make a own exception by extending a Exception class
 */
public class DAOException extends Exception {
    /**
     * * Throwable exception that wrapped into own TransactionException exception
     *
     * @param exeption -Throwable exception
     */
    public DAOException(Throwable exeption) {
        super(exeption);
    }
}
