package com.framework.exceptions;

/**
 * Custom framework exception class for handling framework-specific errors
 * This demonstrates exception handling and encapsulation of error information
 */
public class FrameworkException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor with message
     * @param message Exception message
     */
    public FrameworkException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause
     * @param message Exception message
     * @param cause Root cause of the exception
     */
    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with cause
     * @param cause Root cause of the exception
     */
    public FrameworkException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructor with message, cause, suppression and writable stack trace flags
     * @param message Exception message
     * @param cause Root cause of the exception
     * @param enableSuppression Whether suppression is enabled
     * @param writableStackTrace Whether the stack trace should be writable
     */
    public FrameworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
