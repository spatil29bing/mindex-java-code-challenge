package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 ReportingStructureServiceImpl class
 * This is a Service call for ReportingStructureController.
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    @Autowired
    private EmployeeService employeeService;
    private List<Object> list = new ArrayList<>();

    /**
     * service method for reading reportingStructure based on employeeId.These method throws EmployeeNotFoundException
     *
     *  @param id the employeeId
     *
     * @return  : ResponseEntity<List<Object>> contains two output List<List<Employee><ReportingStructure>>
     *     List<Employee> : Details about the employee with given employee id, and it's all directReports.
     *     List<ReportingStructure>: Details about the employee with given employee id, numberOfReports.
     * @throws EmployeeNotFoundException
     */
    @Override
    public List<Object> read(String id) throws EmployeeNotFoundException {
        //checking whether employee exist in db
        Employee employee = employeeService.findEmployeeByEmployeeId(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Invalid employeeId: " + id);
        }
        //creates a new reporting structure for employee with given employeeId
        ReportingStructure reportingStructure = calculateAllReporties(employee);
        //adds reporting structure in the output list
        list.add(reportingStructure);
        return list;
    }

    /**
     * This method calculates the all directReports i.e. numberOfReports on bases on employeeId.
     * I have used Depth First Search(DFS) to calculate the numberOfReports for the given employeeId
     * @param employee
     * @return
     * @throws EmployeeNotFoundException
     */
    private ReportingStructure calculateAllReporties(Employee employee) throws EmployeeNotFoundException {
        ReportingStructure reportingStructure = new ReportingStructure(employee);
        reportingStructure.setNumberOfReports(dfs(employee));
        return reportingStructure;
    }

    /**
     * DFS Algorithm to calculate the numberOfReports for the given employee's employeeId
     * @param employee
     * @return numberOfReports
     * @throws EmployeeNotFoundException
     */

    public int dfs(Employee employee) throws EmployeeNotFoundException {
        list.add(employee);
        List<Employee>  directReports = employee.getDirectReports();
        int numberOfReports = 0;
        if(directReports != null) {
            numberOfReports = directReports.size();

            for (Employee emp : directReports) {
                numberOfReports += dfs(employeeService.findEmployeeByEmployeeId(emp.getEmployeeId()));
            }
        }

        return numberOfReports;
    }
}
