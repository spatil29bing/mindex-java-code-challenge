package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.exception.CompensationNotFoundException;
import com.mindex.challenge.exception.DateInValidException;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.exception.SalaryNegativeException;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*Creating a REST controller for Compensation*/
@RestController
@RequestMapping("/employee/compensation")
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    //Dependency injection for compensationService
    @Autowired
    private CompensationService compensationService;
    /**
     * controller method for creating a new compensation based on employeeId.
     *  @param compensation the Compensation
     * This method throws and all exceptions are handled
     * 1.) EmployeeNotFoundException
     * If the employee with given employeeId is not found in database. It only add compensation for existing employees.
     * For adding Compensation for new Employee first create new Employee then add compensation for that employee.
     *2.) SalaryNegativeException : if salary is negative
     * 3.)DateInValidException : if date is not valid
     * 4.)All types of JSON parser error for invalid datatype, unable serialize and deserialize are also handled
     * @Valid : is added to validate the compensation object requested by user before creating it
     */
    @PostMapping
    public ResponseEntity<Compensation> create(@RequestBody @Valid Compensation compensation) throws EmployeeNotFoundException,
            SalaryNegativeException, DateInValidException {
        LOG.debug("Received compensation create request for [{}]", compensation);
        return new ResponseEntity<>(compensationService.create(compensation), HttpStatus.CREATED) ;
    }

    /**
     *controller method for reading compensation based on employeeId.These method throws
     * 1.)EmployeeNotFoundException
     *  if compensation of employee doesnt exist.
     *  2.) CompensationNotFoundException : if compensation details doesn't exist
     *
     * @param id the employeeId
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<Compensation>  read(@PathVariable String id) throws EmployeeNotFoundException, CompensationNotFoundException {
        LOG.debug("Received compensation create request for id [{}]", id);
        return ResponseEntity.ok(compensationService.read(id));
    }

}
