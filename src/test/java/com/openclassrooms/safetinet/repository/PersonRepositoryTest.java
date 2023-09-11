package com.openclassrooms.safetinet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.Person;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class PersonRepositoryTest {

	@Autowired
	private DataWrapper dataWrapper;
	@Autowired
	private PersonsRepository personsRepository;


	@BeforeAll
	void before() {
		dataWrapper.setPersons(new ArrayList<>());
	}


	@Test
	@Order(1)
	public void testGetAll() {
		List<Person> expectedPersons = new ArrayList<>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		expectedPersons.add(new Person("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"));
		dataWrapper.setPersons(expectedPersons);
		List<Person> result = personsRepository.getAll();
		assertEquals(expectedPersons, result);
	}


	@Test
	@Order(2)
	public void testSave() {
		List<Person> expectedPersons = new ArrayList<Person>();
		Person person  = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		expectedPersons.add(person);
		dataWrapper.setPersons(expectedPersons);
		Optional<Person> result = personsRepository.save(person);
		assertEquals(person, result.get());
	}

	@Test
	@Order(3)
	public void testGetByName() {
		List<Person> expectedPersons = new ArrayList<>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		dataWrapper.setPersons(expectedPersons);
		List<Person> actualPersons = personsRepository.getByName("John","Boyd");
		assertEquals(expectedPersons, actualPersons);
	}

	@Test
	@Order(4)
	public void testUpdate() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		dataWrapper.setPersons(persons);
		boolean result = personsRepository.update("John", "Boyd",personToUpdate);
		assertTrue(result);	
	}

	@Test
	@Order(5)
	public void testDelete() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		dataWrapper.setPersons(persons);
		boolean result = personsRepository.delete("John","Boyd");
		assertTrue(result);
	}

	@Test
	@Order(6)
	public void testGetPersonByAddress() {
		List<Person> expectedPersons = new ArrayList<>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		dataWrapper.setPersons(expectedPersons);
		List<Person> actualPersons = personsRepository.getPersonByAddress("1509 Culver St");
		assertEquals(expectedPersons, actualPersons);
	}

	@Test
	@Order(7)
	public void testGetEmailByCity() {
		List<Person> expectedPersons = new ArrayList<>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		dataWrapper.setPersons(expectedPersons);
		List<Person> actualPersons = personsRepository.getEmailByCity("Culver");
		assertEquals(expectedPersons, actualPersons);
	}

}
