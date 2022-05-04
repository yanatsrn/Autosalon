package com.pluralsight.conference;

import com.pluralsight.conference.entity.*;
import com.pluralsight.conference.repository.CarRepository;
import com.pluralsight.conference.repository.PersonRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ConferenceApplicationTests {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private CarRepository carRepository;

	@Test
	@Description("checkPeople")
	public void checkPeople() {
		List<Person> people = new ArrayList<>();
		people.add(new Person("q", "q", 23, "q", "q"));
		people.add(new Person("q", "q", 23, "q", "q"));
		people.add(new Person("q", "q", 23, "q", "q"));
		List<Person> peopleFromDB = personRepository.findAll();
		Assertions.assertNotNull(peopleFromDB);
		Assertions.assertNotEquals(peopleFromDB, people);
	}

	@Test
	@Description("checkAddToCompany")
	public void testCompanyAdd() {
		Company company1 = new Company();
		company1.setCompanyId(3);
		Company company2 = new Company();
		company2.setCompanyId(3);
		Assertions.assertNotEquals(company1, company2);
	}

	@Test
	@Description("work with Person entity")
	public void checkPersonMethods() {
		Person testPerson = new Person("surname", "name", 22, "phone", "mail");
		Assertions.assertEquals("surname", testPerson.getSurname());
		Assertions.assertEquals(22, testPerson.getAge());
	}

	@Test
	@Description("checkPerson")
	public void checkPerson() {
		Person testPerson = new Person("surname", "name", 22, "phone", "mail");
		User user = new User("login", "password", "USER", true, 0);
		testPerson.setUser(user);
		personRepository.save(testPerson);
		Person person0 = personRepository.findAll().get(0);
		Assertions.assertEquals(person0, testPerson);
	}


//	@Test
//	@Description("work with entity Car")
//	public void checkCarMethods() {
//		Car car = new Car("");
//
//	}
}
