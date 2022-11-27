package com.mindex.challenge.exception;

/**
 * SalaryNegativeException occurs when salary is negative
 */
public class SalaryNegativeException extends Exception{
    public SalaryNegativeException(String message) {
        super(message);
    }
}
