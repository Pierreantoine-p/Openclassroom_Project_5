package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<Person> findAll()throws IOException{
		return personsService.getPersons();
	}
	
	@PostMapping
	public Person save(@RequestBody Person person) throws IOException{
		personsService.savePerson(person);
		return person;
	}
	/*
	@GetMapping("/{firstName}")
	public Person findFirstName (@PathVariable int id) throws IOException{
		return personsService.findFirstName();
	}

	
	@GetMapping("/{id}")
	public Persons findById (@PathVariable int id) throws IOException{
		return personsService.findById(id);
	}
	
	@GetMapping
	public List<Persons> findAll()throws IOException{
		return personsService.findAll();
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById (@PathVariable int id){
		personsService.deleteById;
	}
	
	
	@PutMapping("/{id}")
	public void replacePersons (@RequestBody Persons newPersons, @PathVariable int id){
		newPersons.setId(id);
	}		
*/
}
