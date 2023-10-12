package com.myproject.employee.services;

import java.util.List;

import com.myproject.employee.model.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getEmployees();

    public boolean deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    Employee updateEmployeeById(Long id, Employee employee);
    

}
