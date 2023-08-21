package com.openclassrooms.safetinet.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.Person;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class PersonControllerTest {


	@Autowired
	private DataWrapper dataWrapper;
	@Autowired
	private PersonsController personsController;

	@BeforeAll
	void before() {
		dataWrapper.setPersons(new ArrayList<>());
	}

	@Test
	@Order(1)
	public void testGetAll() {		
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = personsController.getPersons();
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(2)
	public void testSave() {		
		Person person = new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com");
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = personsController.save(person);
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(3)
	public void testUpdate() {	
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com"));
		Person personToUpdate = new Person("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com") ;
		dataWrapper.setPersons(persons);
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = personsController.update("John","Boyd", personToUpdate);
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(4)
	public void testDelete() {		
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = personsController.delete("John","Boyd");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
}
