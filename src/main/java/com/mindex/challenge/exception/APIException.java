package com.mindex.challenge.exception;

/**
 * APIException is for all Exceptions
 */
public class APIException extends RuntimeException {
    private static final  long serialVersionUID = 1L;
    public APIException(String message) {
        super(message);
    }
}
