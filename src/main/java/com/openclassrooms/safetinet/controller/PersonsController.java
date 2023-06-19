package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
	

	public PersonsController(PersonsService personsService) {
		this.personsService = personsService;
	}
	
	
	@GetMapping
	public List<Person> getAll()throws IOException{
		return personsService.getAll();
	}
	
	
	@GetMapping("/{firstname}/{lastname}")
	public ResponseEntity<Person> getOne(@PathVariable String firstname, @PathVariable String lastname) throws IOException{
		return personsService.findByName(firstname, lastname)
				.map(person -> new ResponseEntity<>(person, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping
	public Person save(@RequestBody Person person) throws IOException{
		personsService.save(person);
		return person;
	}
	
	
	
	@PutMapping("/{firstname}/{lastname}")
	public ResponseEntity<Void> update(@PathVariable String firstname,@PathVariable String lastname, @RequestBody Person person) throws IOException{
		Optional<Person> existingPerson = personsService.findByName(firstname,lastname);
		if(existingPerson.isPresent()) {
			personsService.update(firstname, lastname, person);
            return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{firstname}/{lastname}")
	public ResponseEntity<String> delete (@PathVariable String firstname, @PathVariable String lastname)throws IOException {
		Optional<Person> existingPerson = personsService.findByName(firstname,lastname);
		if(existingPerson.isPresent()) {
		personsService.delete(firstname,lastname);
		return ResponseEntity.ok("Suppression effectuer");
		}else{
			return ResponseEntity.ok("Not found");
		}
	}
	
	
	

}
