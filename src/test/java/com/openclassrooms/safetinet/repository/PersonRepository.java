package com.openclassrooms.safetinet.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@SpringBootTest
public class PersonRepository {
	
	@Mock
	private PersonsRepository personsRepository;
	private DataWrapper dataWrapper;
	
	@Before
	public void setUp() {
		personsRepository = new PersonsRepository(dataWrapper);
	}

	@Test
	public void testGetAll() {
        List<Person> expectedPersons = new ArrayList<>();
        
        expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		expectedPersons.add(new Person("Jacob","Boyd","1509 Culver St","Culver","97451","841-874-6513","drk@email.com"));

        List<Person> result = personsRepository.getAll();
        
        assertEquals(expectedPersons, result);
	}
	
	@Test
	public void testGetAll_Exception() {
		
        when(dataWrapper.getAllPersons()).thenThrow(new RuntimeException("Simulated exception"));
        
        List<Person> result = personsRepository.getAll();
        
        assertEquals(new ArrayList<Person>(), result);
	}
	
	@Test
	public void testSave() {
		
		 Person person  = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
        when(dataWrapper.getAllPersons()).thenReturn(new ArrayList<>());
        
        PersonsRepository personsRepository = new PersonsRepository(dataWrapper);
        Optional<Person> result = personsRepository.save(person);
        
        assertEquals(person, result.get());

	}
	@Test
	public void testSave_Exception() {
		Person person  = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
        when(dataWrapper.getAllPersons()).thenReturn(new ArrayList<>());
        
       // when(dataWrapper.getAllPersons()).thenReturn(Collections.singletonList(person));
        
        PersonsRepository personsRepository = new PersonsRepository(dataWrapper);
        Optional<Person> result = personsRepository.save(person);
        
        assertFalse(result.isPresent());

        verify(dataWrapper, times(1)).getAllPersons();
        //verify(times(1)).error(anyString());
        
	}
	
	@Test
	public void testGetByName() {
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
        
        when(dataWrapper.getPersonByName("John","Boyd")).thenReturn(expectedPersons);
		List<Person> actualPersons = personsRepository.getByName("John","Boyd");
		
		assertEquals(expectedPersons, actualPersons);
	}
	
	@Test
	public void testGetByName_Exception() {

		when(dataWrapper.getPersonByName("John", "Boyd")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personsRepository.getByName("John", "Boyd");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());
	}

	@Test
	public void testUpdate() {
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(dataWrapper.getAllPersons()).thenReturn(persons);
		when(dataWrapper.updatePerson(personToUpdate)).thenReturn(Optional.of(personToUpdate));
		Optional<Person> updatedPerson = personsRepository.update(personToUpdate);
	
		
		//assertTrue(updatedPerson.isPresent());
		assertEquals(personToUpdate, updatedPerson.get());	
	}
	
	@Test
	public void testUpdate_Exception() {
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(personsRepository.getAll()).thenReturn(persons);
		Optional<Person> updatedPerson = personsRepository.update(personToUpdate);

		assertNotNull(updatedPerson);
		assertFalse(updatedPerson.isPresent());
	}
	
	@Test
	public void testDelete() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(dataWrapper.getAllPersons()).thenReturn(persons);
		Optional<Person> deletedPerson = personsRepository.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(personsRepository, times(1)).delete(any(), null);
	}
	
	@Test
	public void testDelete_NotFound() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		when(dataWrapper.getAllPersons()).thenReturn(persons);
		Optional<Person> deletedPerson = personsRepository.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(personsRepository, never()).delete(any(), null);
	}
	
	
	@Test
	public void testGetPersonByAddress() {
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
        
        when(dataWrapper.getPersonByAddress("1509 Culver St")).thenReturn(expectedPersons);
		List<Person> actualPersons = personsRepository.getPersonByAddress("1509 Culver St");
		
		assertEquals(expectedPersons, actualPersons);
	}
	
	@Test
	public void testGetPersonByAddress_Exception() {

		when(dataWrapper.getPersonByAddress("1509 Culver St")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personsRepository.getPersonByAddress("1509 Culver St");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());
	}
	
	@Test
	public void testGetEmailByCity() {
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
        
        when(dataWrapper.getEmailByCity("1509 Culver St")).thenReturn(expectedPersons);
		List<Person> actualPersons = personsRepository.getEmailByCity("1509 Culver St");
		
		assertEquals(expectedPersons, actualPersons);
	}
	
	@Test
	public void testGetEmailByCity_Exception() {

		when(dataWrapper.getPersonByAddress("1509 Culver St")).thenThrow(new RuntimeException("Database connection error"));
		List<Person> actualPersons = personsRepository.getPersonByAddress("1509 Culver St");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());
	}
	
}
