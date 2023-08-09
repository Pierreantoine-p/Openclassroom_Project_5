package com.openclassrooms.safetinet.service;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;
import com.openclassrooms.safetinet.service.PersonsService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class PersonServiceTest {

	@Autowired
	private DataWrapper dataWrapper;
	@Autowired
	private PersonsService personService;

	@BeforeAll
	void before() {
		dataWrapper.setFirestations(new ArrayList<>());
		dataWrapper.setMedicalrecords(new ArrayList<>());
		dataWrapper.setPersons(new ArrayList<>());
	}

	@Test
	@Order(1)
	public void testGetAll() {
		List<Person> expectedPersons = new ArrayList<Person>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		expectedPersons.add(new Person("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"));
		dataWrapper.setPersons(expectedPersons);

		List<Person> actualPersons = personService.getAll();

		assertEquals(expectedPersons, actualPersons);
	}
/*
	@Test
	@Order(2)
	public void testGetAll_Exception() {

		when(personsRepository.getAll()).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getAll();

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}
	*/
	

	@Test
	@Order(3)
	public void testGetPersonByAddress() {
		List<Person> expectedPersons = new ArrayList<Person>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		dataWrapper.setPersons(expectedPersons);
		List<Person> actualPersons = personService.getPersonByAddress("1509 Culver St");

		assertEquals(expectedPersons, actualPersons);
		
	}
/*
	@Test
	@Order(4)
	public void testGetPersonByAddress_Exception() {

		when(personsRepository.getPersonByAddress("1509 Culver St")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getPersonByAddress("1509 Culver St");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}
*/
	@Test
	@Order(5)
	public void testGetByName() {
		List<Person> expectedPersons = new ArrayList<Person>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		dataWrapper.setPersons(expectedPersons);

		List<Person> actualPersons = personService.getByName("John", "Boyd");

		assertEquals(expectedPersons, actualPersons);
	}
	/*
	@Test
	@Order(6)
	public void testGetByName_Exception() {

		when(personsRepository.getByName("John", "Boyd")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getByName("John", "Boyd");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());
	}
	*/

	@Test
	@Order(7)
	public void testSave() {
		List<Person> expectedPersons = new ArrayList<Person>();
		Person person = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		expectedPersons.add(person);
		
		dataWrapper.setPersons(expectedPersons);
		Optional<Person> savedPerson = personService.save(person);
		System.out.println("savedPerson + " + savedPerson);

		assertTrue(savedPerson.isPresent());
		assertEquals(person, savedPerson.get());
	}
	/*
	@Test
	@Order(8)
	public void testSave_Exception() {
		Person person = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");

		doThrow(new RuntimeException("Database connection error")).when(personsRepository).save(person);
		Optional<Person> savedPerson = personService.save(person);
		
		assertNotNull(savedPerson);
		assertFalse(savedPerson.isPresent());

	}

*/
	@Test
	@Order(9)
	public void testUpdate() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;

		dataWrapper.setPersons(persons);
		boolean result = personService.update("John", "Boyd", personToUpdate);
	
		//System.out.println("updatedPerson" + updatedPerson.get());
		System.out.println("personToUpdate" + personToUpdate);

		//assertTrue(updatedPerson.isPresent());
		assertTrue(result);	
	}
	/*
	@Test
	@Order(10)
	public void testUpdate_Exception() {
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> updatedPerson = personService.update("John", "Boyd", personToUpdate);

		assertNotNull(updatedPerson);
		assertFalse(updatedPerson.isPresent());
	}
*/
	@Test
	@Order(11)
	public void testDelete() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		dataWrapper.setPersons(persons);
		boolean result = personService.delete("John","Boyd");

		assertTrue(result);
	}
	/*
	@Test
	@Order(11)
	public void testDelete() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		boolean result = personService.delete("John","Boyd");

		assertTrue(deletedPerson.isPresent());
	}
/*
	
/*
	@Test
	@Order(12)
	public void testDelete_NotFound() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> deletedPerson = personService.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(personsRepository, never()).delete(any(), null);

	}
	*/
	@Test
	@Order(13)
	public void testFindEmailByCity() {
		List<Person> expectedEmails = new ArrayList<Person>();

		expectedEmails.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		dataWrapper.setPersons(expectedEmails);
		
		List<Person> actualEmail = personService.findEmailByCity("Culver");

		assertEquals(expectedEmails, actualEmail);
		
	}
	/*
	@Test
	@Order(14)
	public void testFindEmailByCity_NotFound() {
		List<Person> expectedEmails = new ArrayList<Person>();
        String city = "Invalid City";
        
        when(personsRepository.getEmailByCity(city)).thenThrow(new RuntimeException("Database connection error"));
		
        List<Person> actualPersons = personService.findEmailByCity(city);
        
        assertNotNull(actualPersons);
        assertTrue(actualPersons.isEmpty());
	}
	*/
	
}
