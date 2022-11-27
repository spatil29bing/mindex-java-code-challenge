package com.mindex.challenge.service;

import com.mindex.challenge.exception.EmployeeNotFoundException;

import java.util.List;

/**
 * Interface for service ReportingStructureServiceImpl
 */
public interface ReportingStructureService {
    /**
     * service method for reading reportingStructure based on employeeId
     * @param id
     * @throws EmployeeNotFoundException
     */
    List<Object> read(String id) throws EmployeeNotFoundException;

}
