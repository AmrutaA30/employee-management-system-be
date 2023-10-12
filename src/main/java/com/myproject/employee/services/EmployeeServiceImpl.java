package com.myproject.employee.services;

import com.myproject.employee.entity.EmployeeEntity;
import com.myproject.employee.model.Employee;
import com.myproject.employee.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository empRepository;

  public EmployeeServiceImpl(EmployeeRepository empRepository) {
    this.empRepository = empRepository;
  }

  @Override
  public Employee createEmployee(Employee employee) {
    EmployeeEntity empEntity = new EmployeeEntity();

    BeanUtils.copyProperties(employee, empEntity);
    empRepository.save(empEntity);
    return employee;
  }

  @Override
  public List<Employee> getEmployees() {
    List<EmployeeEntity> employeeEntities = empRepository.findAll();
    List<Employee> employees = employeeEntities
      .stream()
      .map(emp ->
        new Employee(
          emp.getId(),
          emp.getFirstName(),
          emp.getLastName(),
          emp.getEmailId()
        )
      )
      .collect(Collectors.toList());
    return employees;
  }

  @Override
  public boolean deleteEmployee(Long id) {
    EmployeeEntity employeeEntity = empRepository.findById(id).get();
    empRepository.delete(employeeEntity);
    return true;
  }

  @Override
  public Employee getEmployeeById(Long id) {
    EmployeeEntity employeeEntity = empRepository.findById(id).get();
    Employee emp = new Employee();
    BeanUtils.copyProperties(employeeEntity, emp);
    return emp;
  }

  @Override
  public Employee updateEmployeeById(Long id, Employee employee) {
    EmployeeEntity employeeEntity = empRepository.findById(id).get();
    employeeEntity.setEmailId(employee.getEmailId());
    employeeEntity.setFirstName(employee.getFirstName());
    employeeEntity.setLastName(employee.getLastName());
    
    empRepository.save(employeeEntity);
    return employee;
  }
}
