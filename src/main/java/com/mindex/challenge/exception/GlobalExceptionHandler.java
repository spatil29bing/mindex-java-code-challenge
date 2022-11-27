package com.mindex.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** @ControllerAdvice is used to handle exception globally
 * This class handles All the runtime exceptions
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle specific exception
    //@ ExceptionHandler is used to handle specific exception and  specific response to client

    /**
     * Exception Handler for ResourceNotFoundException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request)
    {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("timestamp", String.valueOf(new Date()));
        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception Handler for APIException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(APIException.class)
    public ResponseEntity<Map<String,String>> handleAPIException(APIException exception, WebRequest request)
    {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("timestamp", String.valueOf(new Date()));
        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception Handler for SalaryNegativeException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(SalaryNegativeException.class)
    public ResponseEntity<Map<String,String>> handleSalaryNegativeException(SalaryNegativeException exception, WebRequest request)
    {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("timestamp", String.valueOf(new Date()));
        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception Handler for All other Runtime Exception which don't have specific exception handlers
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request)
    {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("timestamp", String.valueOf(new Date()));
        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception Handler for MethodArgumentNotValidException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request)
    {
        Map<String,String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().
                forEach(error -> { errorMap.put(error.getField(),error.getDefaultMessage());});
        return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception Handler for EmployeeNotFoundException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleEmployeeNotFoundException(EmployeeNotFoundException exception, WebRequest request)
    {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception Handler for DateInValidException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(DateInValidException.class)
    public ResponseEntity<Map<String,String>> handleDateTimeParseException(DateInValidException exception, WebRequest request)
    {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
