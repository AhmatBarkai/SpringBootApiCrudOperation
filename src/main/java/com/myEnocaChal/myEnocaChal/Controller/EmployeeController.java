package com.myEnocaChal.myEnocaChal.Controller;

import com.myEnocaChal.myEnocaChal.Entity.Company;
import com.myEnocaChal.myEnocaChal.Entity.Employee;
import com.myEnocaChal.myEnocaChal.Repository.EmployeeRepository;
import com.myEnocaChal.myEnocaChal.Response.Response;
import com.myEnocaChal.myEnocaChal.Service.CompanyService;
import com.myEnocaChal.myEnocaChal.Service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.PreferencesEvent;
import java.util.List;

@Slf4j
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyService companyService ;

    //save an Employee to a company
    @PostMapping("/saveEmployee/{company_id}")
    public ResponseEntity<Response> saveEmployee(@RequestBody Employee employee, @PathVariable Long company_id){

        //getting an existing company first
        Company company =  companyService.getCompanyById(company_id);
        //adding a new employee to the company that we found
        company.getEmployees().add(employee);
        //saving the new Employee and the company that we added him an new employee (the 2 first lines)
        employeeService.saveEmployee(employee);
        companyService.saveCompany(company);
        //the left are only message to the users
        Response response = new Response();

        String employee_name = employee.getName();
        String company_name = company.getName();

        response.setStatusCode("200");
        response.setStatusMsg( "Employee " + employee_name + " is successfully saved to : " + company_name);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

    // A revoir plus tard que pouquoi si on cherche a obtanir un employee qui n'existe pas on ne peut pas avoir un message de la part de server(response)
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = new Employee();
        employee = employeeService.getEmployeeById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employee);
    }
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee , @PathVariable Long id){
        Response response = new Response();

        try{
            Employee employee1 = new Employee();
            employee1 = employeeService.updateEmployee(id,employee);
            employeeRepository.save(employee);
            String employee_name = employee1.getName();
            response.setStatusCode("200");
            response.setStatusMsg("Employee : "+employee_name+"is updated successfully in the Db.");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            response.setStatusCode("400");
            response.setStatusMsg("no such an employee in the Db to be updated.");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable Long id){
        Response response = new Response();
        try{
            employeeService.deleteEmployee(id);
            response.setStatusCode("200");
            response.setStatusMsg("Deleting an employee From the Db is done successfully.");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }catch (Exception e){
            response.setStatusCode("400");
            response.setStatusMsg(" no such an employee in the Db .");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }

    @PutMapping("/updateSalaryByAgeAndWorkingYear/{age}/{working_year}")
    public List<Employee> updateEmployeeByAgeAndWorkingYear(@PathVariable Integer age , @PathVariable Integer working_year){
        return employeeService.getEmployeeByAgeAndWorkingYear(age ,working_year);
    }


}
