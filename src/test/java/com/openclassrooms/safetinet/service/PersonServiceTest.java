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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class PersonServiceTest {

	@Mock
	private PersonsRepository personsRepository;
	private PersonsService personService;

	@BeforeAll
	public void setUp() {
		personService = new PersonsService(personsRepository);
	}

	@Test
	public void testGetAll() {
		List<Person> expectedPersons = new ArrayList<Person>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		expectedPersons.add(new Person("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"));

		when(personsRepository.getAll()).thenReturn(expectedPersons);

		List<Person> actualPersons = personService.getAll();

		assertEquals(expectedPersons, actualPersons);
	}

	@Test
	public void testGetAll_Exception() {

		when(personsRepository.getAll()).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getAll();

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}
	@Test
	public void testGetPersonByAddress() {
		List<Person> expectedPersons = new ArrayList<Person>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getPersonByAddress("1509 Culver St")).thenReturn(expectedPersons);

		List<Person> actualPersons = personService.getPersonByAddress("1509 Culver St");

		assertEquals(expectedPersons, actualPersons);
		
	}


	@Test
	public void testGetPersonByAddress_Exception() {

		when(personsRepository.getPersonByAddress("1509 Culver St")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getPersonByAddress("1509 Culver St");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}

	@Test
	public void testGetByName() {
		List<Person> expectedPersons = new ArrayList<Person>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getByName("John", "Boyd")).thenReturn(expectedPersons);

		List<Person> actualPersons = personService.getByName("John", "Boyd");

		assertEquals(expectedPersons, actualPersons);
	}
	@Test
	public void testGetByName_Exception() {

		when(personsRepository.getByName("John", "Boyd")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personService.getByName("John", "Boyd");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());
	}

	@Test
	public void testSave() {
		Person person = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");

		when(personsRepository.save(person));
		Optional<Person> savedPerson = personService.save(person);

		assertTrue(savedPerson.isPresent());
		assertEquals(person, savedPerson.get());
	}
	@Test
	public void testSave_Exception() {
		Person person = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");

		doThrow(new RuntimeException("Database connection error")).when(personsRepository).save(person);
		Optional<Person> savedPerson = personService.save(person);
		assertNotNull(savedPerson);
		assertFalse(savedPerson.isPresent());

	}

	@Test
	public void testUpdate() {
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		when(personsRepository.update(personToUpdate)).thenReturn(Optional.of(personToUpdate));
		Optional<Person> updatedPerson = personService.update("John", "Boyd", personToUpdate);

		assertTrue(updatedPerson.isPresent());
		assertEquals(personToUpdate, updatedPerson.get());	
	}
	@Test
	public void testUpdate_Exception() {
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> updatedPerson = personService.update("John", "Boyd", personToUpdate);

		assertNotNull(updatedPerson);
		assertFalse(updatedPerson.isPresent());
	}

	@Test
	public void testDelete() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> deletedPerson = personService.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(personsRepository, times(1)).delete(any(), null);
	}

	@Test
	public void testDelete_NotFound() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> deletedPerson = personService.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(personsRepository, never()).delete(any(), null);

	}
	
	@Test
	public void testFindEmailByCity() {
		List<Person> expectedEmails = new ArrayList<Person>();

		expectedEmails.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		when(personsRepository.findEmailByCity("Culver")).thenReturn(expectedEmails);

		List<Person> actualEmail = personService.findEmailByCity("Culver");

		assertEquals(expectedEmails, actualEmail);
		
	}
	
	@Test
	public void testFindEmailByCity_NotFound() {
        String city = "Invalid City";
        
        when(personsRepository.findEmailByCity(city)).thenThrow(new RuntimeException("Database connection error"));
		
        List<Person> actualPersons = personService.findEmailByCity(city);
        
        assertNotNull(actualPersons);
        assertTrue(actualPersons.isEmpty());
	}
	
	
}
