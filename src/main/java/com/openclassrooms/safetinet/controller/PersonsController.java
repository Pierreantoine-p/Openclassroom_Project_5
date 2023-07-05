package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
@RequestMapping("/person")
public class PersonsController {
	
	private PersonsService personsService;
	
    private static final Logger logger = LogManager.getLogger(PersonsController.class);

	public PersonsController(PersonsService personsService) {
		this.personsService = personsService;
	}
	
	

	
	@GetMapping
	public ResponseEntity <List<Person>> getAll(){
		try {
			return new ResponseEntity<>(personsService.getAll(), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
 	}
	
	
	@GetMapping("/{firstname}/{lastname}")
	public ResponseEntity<Person> getOne(@PathVariable String firstname, @PathVariable String lastname)  {
		try {
			return personsService.findByName(firstname, lastname)
					.map(person -> new ResponseEntity<>(person, HttpStatus.OK))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	
	@PostMapping
	public  ResponseEntity<Person> save(@RequestBody Person person)  {
		try {
			 personsService.save(person);
			 return new ResponseEntity<>(person,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	 
	}
	
	
	
	@PutMapping("/{firstname}/{lastname}")
	public ResponseEntity<Void> update(@PathVariable String firstname,@PathVariable String lastname, @RequestBody Person person)  {
		try {
			Optional<Person> existingPerson = personsService.findByName(firstname,lastname);
			if(existingPerson.isPresent()) {
				personsService.update(firstname, lastname, person);
	            return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity <>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	@DeleteMapping("/{firstname}/{lastname}")
	public ResponseEntity<String> delete (@PathVariable String firstname, @PathVariable String lastname)  {
		try {
			Optional<Person> existingPerson = personsService.findByName(firstname,lastname);
			if(existingPerson.isPresent()) {
			personsService.delete(firstname,lastname);
			return ResponseEntity.ok("Suppression effectuer");
			}else{
				return ResponseEntity.ok("Not found");
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	
	

}
