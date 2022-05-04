package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.Company;
import com.pluralsight.conference.entity.Type;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Facade {

    private final CompanyService companyService;
    private final TypeService typeService;

    private List<Company> companies;
    private List<Type> types;

    public Facade(CompanyService companyService, TypeService typeService) {
        this.companyService = companyService;
        this.typeService = typeService;
        companies = new ArrayList<>();
        types = new ArrayList<>();
    }

    public List<Company> getCompanies() {
        companies = companyService.getCompanies();
        return companies;
    }

    public List<Type> getTypes() {
        types = typeService.getTypes();
        return types;
    }

    public Company findCompanyByName(String name) {
        return companyService.findByCompanyName(name).get(0);
    }

    public Type findTypeByTypeId(String typeStr) {
        String[] typeIsStr = typeStr.split(" ");
        return typeService.findByTypeId(Integer.parseInt(typeIsStr[0]));
    }
}

