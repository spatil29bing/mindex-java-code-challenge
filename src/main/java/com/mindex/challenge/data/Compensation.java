package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class Compensation {
    @Id
    private Employee employee;
    private float salary;

    //effectiveDate is taken in String format to validate whether it is a valid date or not. Date Format : "MM/dd/yyyy"
    @JsonFormat(pattern = "MM/dd/yyyy")
    private String effectiveDate;


    Employee emp;
    public Compensation(Employee employeeInDB)
    {
        this.emp = employeeInDB;

    }

    public Compensation() {
    }

    public Compensation(Employee employee, float salary, String effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public double getSalary() {
        return salary;
    }


   public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


}
