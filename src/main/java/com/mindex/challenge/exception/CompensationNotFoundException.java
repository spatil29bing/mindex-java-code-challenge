package com.mindex.challenge.exception;

/**
 * CompensationNotFoundException occurs when compensation details are not found
 */
public class CompensationNotFoundException extends Exception{
    public CompensationNotFoundException(String message) {
        super(message);
    }
}
