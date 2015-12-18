package com.db.exception;

/**
 * TransactionException class- make a own exception by extending a Exception class
 */
public class TransactionException extends Exception {
    /**
     * Throwable exception that wrapped into own TransactionException exception
     *
     * @param e Throwable exception
     */
    public TransactionException(Throwable e) {
        super(e);
    }
}
