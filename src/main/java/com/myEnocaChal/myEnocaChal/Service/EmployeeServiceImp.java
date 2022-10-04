package com.myEnocaChal.myEnocaChal.Service;

import com.myEnocaChal.myEnocaChal.Entity.Employee;
import com.myEnocaChal.myEnocaChal.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Long id,Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).get();
        if(existingEmployee != null) {
            existingEmployee.setName(existingEmployee.getName());
            existingEmployee.setAge(existingEmployee.getAge());
            existingEmployee.setSalary(existingEmployee.getSalary());
            existingEmployee.setWorking_year(existingEmployee.getWorking_year());
            existingEmployee.setCompany(existingEmployee.getCompany());
        }
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id).get();
        if(null != existingEmployee){
            employeeRepository.delete(existingEmployee);
        }

    }




    public double NewSalaryCalculation(Employee employee) {
        double  new_salary ;
        new_salary = employee.getSalary() + employee.getWorking_year() * (employee.getSalary()*0.1) ;
        double salary_with_bonus = new_salary;
        if(employee.getAge()>= 20 && employee.getAge()<=25){
            salary_with_bonus = new_salary + employee.getSalary()*0.1;
            return salary_with_bonus;
        }
        else if(employee.getAge()>= 26 && employee.getAge()<= 30){
            salary_with_bonus = new_salary + employee.getSalary()*0.08;
            return salary_with_bonus;
        }
        else if(employee.getAge()>= 31 && employee.getAge()<=36){
            salary_with_bonus = new_salary + employee.getSalary()*0.05;
            return salary_with_bonus;
        }
        else if(employee.getAge()>36){
            salary_with_bonus = new_salary + employee.getSalary()*0.03;
            return salary_with_bonus;
        }
        else{
            return new_salary;
        }
    }

    @Override
    public List<Employee> getEmployeeByAgeAndWorkingYear(Integer age, Integer working_year) {
        List<Employee> employees = employeeRepository.getEmployeesByAgeAndWorkYear(age,working_year);
        employees.stream().forEach(employee ->{
                    employee.setSalary(NewSalaryCalculation(employee));
                    employeeRepository.save(employee);
                }
                );
        return employeeRepository.saveAll(employees);

    }


}
