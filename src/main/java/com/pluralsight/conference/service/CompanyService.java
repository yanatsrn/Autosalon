package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.Company;
import com.pluralsight.conference.repository.CompanyRepository;
import com.pluralsight.conference.validator.CarValidator;
import com.pluralsight.conference.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public void deleteByCompanyId(int id) {
        Company company = companyRepository.findByCompanyId(id);
        companyRepository.delete(company);
    }

    public void deleteByCarsCarId(int id) {
        companyRepository.deleteByCarsCarId(id);
    }

    public Company showPresentCars(Company company) {
        if (!company.getCars().isEmpty()) {
            for(int i = 0; i<company.getCars().size(); i++) {
                if (company.getCars().get(i).isBought())
                    company.getCars().remove(company.getCars().get(i));
            }

        }
        return company;
    }

    public List<Company> findByCompanyName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    public Company findByCompanyId(int id) {
        return companyRepository.findByCompanyId(id);
    }

    public String updateCompany(Company company) {
        String error = " ";
        if (!CarValidator.isValidNameAndCountry(company.getCompanyName(), company.getCompanyCountry())) {
            error += "Некорректные значения полей\n";
        }
        else {
            List<Company> companies = findByCompanyName(company.getCompanyName());
            if (!companies.isEmpty())
                error += "Такое название компании уже есть\n";
            else
                companyRepository.save(company);
        }
        return error;
    }

    public String addCompany(Company company) {
        String error = " ";
        if (!CarValidator.isValidNameAndCountry(company.getCompanyName(), company.getCompanyCountry())) {
            error += "Некорректные значения полей\n";
        }
        else {
            List<Company> companies = findByCompanyName(company.getCompanyName());
            if (!companies.isEmpty())
                error += "Такое название компании уже есть\n";
            else
                companyRepository.save(company);
        }
        return error;
    }
}
