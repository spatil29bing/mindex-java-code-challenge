package com.mindex.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.NotNull;


/**
 *  This class validates the input given by user for adding new compensation
 */
@RestControllerAdvice
public class UserRequest {
    @NotNull(message = "Salary should not be null")
    private float salary;
    @NotNull(message = "effectiveDate should not be null ")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private String  effectiveDate;

    public UserRequest(float salary, String effectiveDate) {
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public UserRequest() {
    }
}
