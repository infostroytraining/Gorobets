package com.service.exception;

/**
 * ServiceException class- make a own exception by extending a Exception class
 */
public class ServiceException extends Exception {
    /**
     * Throwable exception that wrapped into own ServiceException exception
     *
     * @param excep Throwable exception
     */
    public ServiceException(Throwable excep) {
        super(excep);
    }
}
