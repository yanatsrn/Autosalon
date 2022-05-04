package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.Person;
import com.pluralsight.conference.report.PeopleReport;
import com.pluralsight.conference.repository.PersonRepository;
import com.pluralsight.conference.validator.Validator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> list() {
        return personRepository.findAll();
    }

    public void deletePerson(int id) {
        personRepository.deleteByPersonId(id);
    }

    public String correctFields(Person person) {
        String personError = " ";
        if (!Validator.isValidSurnameAndName(person.getSurname(), person.getName()))  {
            personError += "Некорректное имя или фамилия\n";
        }
        if (!Validator.isValidAge(person.getAge())) {
            personError += "Некорректный возраст\n";
        }
        if (!Validator.isValidPhone(person.getPhone())) {
            personError += "Некорректный телефон\n";
        }
        if (!Validator.isValidMail(person.getMail())) {
            personError += "Некорректная почта\n";
        }
        if (!Validator.isValidLogin(person.getUser().getLogin())) {
            personError += "Некорректный логин\n";
        }
        if (!Validator.isValidPassword(person.getUser().getPassword())) {
            personError += "Некорректный пароль\n";
        }
        return personError;
    }

    public String correctPerson(Person person) {
        String personError = correctFields(person);
        if (personError.equals(" ")) {
            if (!uniqueLogin(person.getUser().getLogin()))
                personError += "Такой логин уже зарегистрирован\n";
            if (!uniqueMail(person.getMail()))
                personError += "Такая почта уже зарегистрирована\n";
        }
        return personError;
    }

    public Person addPerson(Person pErson) {
        return personRepository.save(pErson);
    }

    public boolean uniqueMail(String mail) {
        List<Person> people = personRepository.findByMail(mail);
        return people.isEmpty();
    }

    public boolean uniqueLogin(String login) {
        boolean isUnique = true;
        Person person = personRepository.findByUserLogin(login);
        if (person != null)
            isUnique = false;
        return isUnique;
    }

    public Person findByPersonId(int personId) {
        return personRepository.findByPersonId(personId);
    }

    public Person signInSystem(Person person) {
        Person currentPerson = null;
        if(Validator.isValidSignIn(person.getUser().getLogin(), person.getUser().getPassword())) {
            Person foundPerson = findByUserLogin(person.getUser().getLogin());
            if (foundPerson != null) {
                if (foundPerson.getUser().getPassword().equals(person.getUser().getPassword()))
                    currentPerson = foundPerson;
            }
        }
        return currentPerson;
    }

    public void unblockPerson(Person person) {
        person.getUser().setActive(true);
        updatePerson(person);
    }

    public void blockPerson(Person person) {
        if (person.getUser().getRole().equals("USER")) {
            person.getUser().setActive(false);
            updatePerson(person);
        }
    }

    public Person findByUserLogin(String login) {
        return personRepository.findByUserLogin(login);
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public Optional<Person> getPersonByPersonId(int id) {
        return personRepository.getPersonByPersonId(id);
    }

}
