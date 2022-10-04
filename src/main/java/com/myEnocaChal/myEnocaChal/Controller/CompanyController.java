package com.myEnocaChal.myEnocaChal.Controller;

import com.myEnocaChal.myEnocaChal.Entity.Company;
import com.myEnocaChal.myEnocaChal.Repository.CompanyRepository;
import com.myEnocaChal.myEnocaChal.Response.Response;
import com.myEnocaChal.myEnocaChal.Service.CompanyService;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/saveCompany")
    public ResponseEntity<Response> saveCompany(@RequestBody Company company){

        companyService.saveCompany(company);
        String company_name = company.getName();

        Response response =new Response();

        response.setStatusCode("200");
        response.setStatusMsg("Company "+company_name+" saved with success ");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    //we can get All companies from this get annotation
    @GetMapping("/getAllCompanies")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    //To get a company by a given id
    @GetMapping("/getCompanyById/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
            Response response = new Response();
            Company company = new Company();
            company = companyService.getCompanyById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(company);
    }

    @PutMapping("/updateCompany/{id}")
    public ResponseEntity<Response> updateCompany(@PathVariable Long id,@RequestBody Company company){

        Response response = new Response();


        try{
            companyService.updateCompany(id,company);
            String updating_c_name = company.getName();

            response.setStatusCode("200");
            response.setStatusMsg(" is successfully updated to : "+ updating_c_name );

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

        }catch (Exception e){

            response.setStatusCode("400");
            response.setStatusMsg("No such a company to be updated ");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);

        }




    }



    @DeleteMapping("/deleteOneCompany/{id}")
    public ResponseEntity<Response> deleteOneCompany(@PathVariable Long id){
        Response response =new Response();

        try{
            companyService.deleteCompany(id);
            response.setStatusCode("200");
            response.setStatusMsg("Company is deleted successfully ");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);

        }catch (Exception e){
            response.setStatusCode("401");
            response.setStatusMsg("No such a company to be deleted .");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);

        }
    }












}
