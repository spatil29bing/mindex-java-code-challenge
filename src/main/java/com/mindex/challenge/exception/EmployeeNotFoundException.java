package com.mindex.challenge.exception;

/**
 * EmployeeNotFoundException when employee is not found for given employeeId
 */
public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
