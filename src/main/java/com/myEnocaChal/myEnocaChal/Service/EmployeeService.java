package com.myEnocaChal.myEnocaChal.Service;

import com.myEnocaChal.myEnocaChal.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id,Employee employee);

    void deleteEmployee(Long id);

    List<Employee> getEmployeeByAgeAndWorkingYear(Integer age , Integer working_year);









}
