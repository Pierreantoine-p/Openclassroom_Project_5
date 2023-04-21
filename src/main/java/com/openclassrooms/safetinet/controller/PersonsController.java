package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Iterable<Person> getPersons()throws IOException{
		return personsService.getPersons();
	}
	
	/*
	@PostMapping
	public Person createPerson(@RequestBody Person person) {
		return personsService.savePerson(person);
	}
	
	
	@DeleteMapping("/{firstname}")
	public void deleteById (@PathVariable("firstname") final String firstname){
		personsService.deletePerson(firstname);
	}
	/*
	@GetMapping("/{firstname,lastname}")
	public void deletePerson(String firstname, String lastname) throws IOException{
		return personsService.deletePerson(firstname, lastname);
	}
	*/
	/*
	@PostMapping
	public void save(@RequestBody Persons persons) throws IOException{
		personsService.save(persons);
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
