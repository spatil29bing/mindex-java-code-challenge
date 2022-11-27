package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employee/reportingStructure/")
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    //dependency injection for reportingStructureService
    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * controller method for reading reportingStructure based on employeeId.These method throws EmployeeNotFoundException
     *
     *  @param id the employeeId
     *
     * @return  : ResponseEntity<List<Object>> contains two output List<List<Employee><ReportingStructure>>
     *     List<Employee> : Details about the employee with given employee id, and it's all directReports.
     *     List<ReportingStructure>: Details about the employee with given employee id, numberOfReports.
     * @throws EmployeeNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Object>>  read(@PathVariable String id) throws EmployeeNotFoundException {
        LOG.debug("Received reporting structure create  request for id [{}]", id);

        return ResponseEntity.ok(reportingStructureService.read(id));
    }
}
