package com.myEnocaChal.myEnocaChal.Service;

import com.myEnocaChal.myEnocaChal.Entity.Company;
import com.myEnocaChal.myEnocaChal.Repository.CompanyRepository;
import com.myEnocaChal.myEnocaChal.Response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CompanyService {

    Company saveCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void deleteCompany(Long id);

    Company updateCompany(Long id,Company company);







}
