package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.exception.CompensationNotFoundException;
import com.mindex.challenge.exception.DateInValidException;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.exception.SalaryNegativeException;

/**
 * Interface for service CompensationServiceImpl
 */
public interface CompensationService {

    /**
     * service method for creating a new compensation based on employeeId.
     * @param compensation
     * @return
     * @throws EmployeeNotFoundException
     * @throws SalaryNegativeException
     * @throws DateInValidException
     */

    Compensation create(Compensation compensation) throws EmployeeNotFoundException, SalaryNegativeException, DateInValidException;

    /**
     * service method for reading compensation based on employeeId
     * @param id
     * @return
     * @throws EmployeeNotFoundException
     * @throws CompensationNotFoundException
     */
    Compensation read(String id) throws EmployeeNotFoundException, CompensationNotFoundException;
}
