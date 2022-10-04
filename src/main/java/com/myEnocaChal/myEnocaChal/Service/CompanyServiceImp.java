package com.myEnocaChal.myEnocaChal.Service;

import com.myEnocaChal.myEnocaChal.Entity.Company;
import com.myEnocaChal.myEnocaChal.Repository.CompanyRepository;
import com.myEnocaChal.myEnocaChal.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;



    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();

    }

    @Override
    public void deleteCompany(Long id) {
        Company isExistingCompany = new Company();
        isExistingCompany = companyRepository.findById(id).get();
        if (isExistingCompany != null) {
            companyRepository.deleteById(id);
        }

    }

    @Override
    public Company updateCompany(Long id,Company company) {
        Company existingCompany = new Company();
        existingCompany = companyRepository.findById(id).get();
        if(existingCompany != null){

            existingCompany.setName(existingCompany.getName());
            existingCompany.setEmployees(existingCompany.getEmployees());

        }
        companyRepository.save(existingCompany);

        return  existingCompany;
    }


}
