package com.mindex.challenge.service.impl;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exception.CompensationNotFoundException;
import com.mindex.challenge.exception.DateInValidException;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.exception.SalaryNegativeException;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.validator.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * CompensationService class
 * This is a Service call for CompensationController.
 */

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    //dependency injection for compensation compensationRepository
    @Autowired
    private CompensationRepository compensationRepository;

    //dependency injection for employeeService
    @Autowired
    private EmployeeService employeeService;

    /**
     * service method for creating a new compensation based on employeeId.
     *       @param compensation the Compensation
     *      This method throws and all exceptions are handled
     *      1.) EmployeeNotFoundException
     *      If the employee with given employeeId is not found in database. It only add
     *                           compensation for existing employees.
     *      For adding Compensation for new Employee first create new Employee then add
     *                           compensation for that employee.
     *      2.) SalaryNegativeException : if salary is negative
     *      3.)DateInValidException : if date is not valid
     *      4.)All types of JSON parser error for invalid datatype, unable serialize and
     *                           deserialize are also handled
     * @return creates new compensation in repository
     * @throws EmployeeNotFoundException, SalaryNegativeException, DateInValidException
     */
    @Override
    public Compensation create(Compensation compensation) throws EmployeeNotFoundException,
            SalaryNegativeException, DateInValidException {
            LOG.debug("Creating compensation [{}]", compensation);
           //gets employee from compensation
            Employee emp = compensation.getEmployee();

            //extracts employeeId of the employee
            String empId = emp.getEmployeeId();

            //check whether employee exist in db with requested employeeId
            Employee confirmEmpExist = employeeService.findEmployeeByEmployeeId(empId);
            //sets the details of existing employee
            compensation.setEmployee(confirmEmpExist);

            //check whether date is valid
            LocalDate parsedLocalDate  = DateValidator.validateEffectiveDate(compensation.getEffectiveDate());

           //check whether salary is valid i.e is it a positive or negative
            if (!isSalaryValid(compensation.getSalary())) {
                throw new SalaryNegativeException("Salary amount cannot be negative or zero.");
            }
        return compensationRepository.insert(compensation);
    }

    /**
     * This method validate whether salary is positive
     * @param salary
     * @return true or false
     */
    private boolean isSalaryValid(double salary) {
        return salary > 0;
    }

    /**
     *service method for reading compensation based on employeeId.These method throws EmployeeNotFoundException
     *  if compensation of employee doesnt exist.
     *
     * @param id the employeeId
     * @return compensation details if employee's compensation exist.
     */
    @Override
    public Compensation read(String id) throws EmployeeNotFoundException, CompensationNotFoundException {
        LOG.debug("Creating compensation with id [{}]", id);
        //check whether employee exist in DB.
        Employee employeeInDB = employeeService.findEmployeeByEmployeeId(id);
        //check whether compensation of employee exist
        Compensation compensationPresent = findCompensation(employeeInDB);
        return compensationPresent;
    }

    /**This method gives details of the compensation of existing employee from the compensationRepository.
     *
     * @param employee
     * @return
     * @throws CompensationNotFoundException
     */
    private Compensation findCompensation(Employee employee) throws CompensationNotFoundException {

        Compensation compensation = compensationRepository.findByEmployee(employee);
        if (compensation == null) {
            throw new CompensationNotFoundException("No compensation data for employeeId: " + employee);
        }
        return compensation;
    }
}
