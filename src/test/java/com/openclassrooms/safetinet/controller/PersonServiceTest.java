package com.openclassrooms.safetinet.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.PersonsService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

	@Test
	@Order(2)
	public void testGetPersonByAddress() {
		List<Person> expectedPersons = new ArrayList<Person>();
		expectedPersons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));

		dataWrapper.setPersons(expectedPersons);

		List<Person> actualPersons = personService.getPersonByAddress("1509 Culver St");
		System.out.println("actualPersons" + actualPersons);
		assertEquals(expectedPersons, actualPersons);
		
	}
	//TODO test service integration C/C logique 
	//TODO test unitaire controller + mock 
	//TODO test erreur manque parametre
	
}
