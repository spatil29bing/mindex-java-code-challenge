package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);

    List<Employee> readAll();

    Employee findEmployeeByEmployeeId(String id) throws EmployeeNotFoundException;
}
