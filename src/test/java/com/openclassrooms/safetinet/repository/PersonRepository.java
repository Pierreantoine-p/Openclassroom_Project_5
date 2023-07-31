package com.openclassrooms.safetinet.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@SpringBootTest
public class PersonRepository {
	
	private PersonsRepository personsRepository;
	    private DataWrapper dataWrapper;
	
	@Before
	public void setUp() {
		dataWrapper = mock(DataWrapper.class);
		personsRepository = new PersonsRepository();
        personsRepository.dataWrapper = dataWrapper;
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
        List<Person> expectedPersons = new ArrayList<>();
        Person personToAdd = (new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
        List<Person> mockList = new ArrayList<>();
        
        doNothing().when()
        
        
        assertEquals(expectedPersons, result);

	}
	
}
