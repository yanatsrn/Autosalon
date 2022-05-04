package com.pluralsight.conference.repository;

import com.pluralsight.conference.entity.Company;
import com.pluralsight.conference.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyId(int id);
    List<Company> findByCompanyName(String name);
    void deleteByCarsCarId(int id);
    void deleteByCompanyId(int id);
}
