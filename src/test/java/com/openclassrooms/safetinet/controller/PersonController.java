package com.openclassrooms.safetinet.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.PersonsService;

public class PersonController {

	
	@Mock
	private PersonsService personService;
	private PersonsController personsController;
	
	@BeforeAll
	public void setUp() {
		personsController = new PersonsController(PersonsService);

	}
	public void testGetAll() {		
		//ResponseEntity<Object> responseEntity = new ResponseEntity<>( HttpStatus.OK);
		HttpStatus expectedResponse = HttpStatus.OK;

		ResponseEntity<List<Person>> response = responseValue;
		when(personsController.getAll()).thenReturn(response);
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(expectedResponse);
		List<Person> actualResponse = personsController.getAll();
		
		assertEquals(response,responseValue);
	}
}
