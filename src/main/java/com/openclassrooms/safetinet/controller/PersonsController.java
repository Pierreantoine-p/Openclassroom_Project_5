package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	

	public PersonsController(PersonsService personsService) {
		this.personsService = personsService;
	}
	
	
	@GetMapping
	public ResponseEntity <Iterable<Person>> getPersons()throws IOException{
		return new ResponseEntity<>(personsService.getPersons(), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Person person) throws IOException{
		personsService.save(person);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{firstName}")
	public ResponseEntity<Void> deletePersonByName (@PathVariable String firstName){
		Optional<Person> existingPerson = personsService.findPersonByName(firstName);
		if(existingPerson.isPresent()) {
		personsService.deletePerson(firstName);
		return new ResponseEntity <>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/{firstName}")
	public ResponseEntity<Void> updatePerson(@PathVariable String firstName, @RequestBody Person person){
		Optional<Person> existingPerson = personsService.findPersonByName(firstName);
		if(existingPerson.isPresent()) {
			personsService.updatePerson(person);
            return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{firstname}")
	public ResponseEntity<Person> getPersonByName(@PathVariable String firstName ) throws IOException{
		return personsService.findPersonByName(firstName)
				.map(person -> new ResponseEntity<>(person, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	

}
