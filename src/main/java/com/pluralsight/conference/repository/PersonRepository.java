package com.pluralsight.conference.repository;

import com.pluralsight.conference.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByMail(String mail);
    Person findByPersonId(int id);
    Person findByUserLogin(String login);
    void deleteByPersonId(int id);
    Optional <Person> getPersonByPersonId(int id);
}
