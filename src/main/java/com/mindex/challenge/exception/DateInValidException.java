package com.mindex.challenge.exception;

/**
 * DateInValidException occurs when Date is invalid for format MM/DD/YYYY eg: 23/01/2023
 * 12/32/2023
 */
public class DateInValidException extends Exception{
    public DateInValidException(String message) {
        super(message);
    }
}
