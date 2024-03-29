package com.openclassrooms.safetinet.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
	
    private static final Logger logger = LogManager.getLogger(PersonsController.class);

	public PersonsController(PersonsService personsService) {
		this.personsService = personsService;
	}
	
	/**
	 *Get a list of all people
	 * @return List of all persons
	 */
	@GetMapping
	public ResponseEntity <List<Person>> getPersons(){
		logger.info("getPersons");
			return new ResponseEntity<>(personsService.getAll(), HttpStatus.OK);
 	}
	
	/**
	 * Created new person
	 * @RequestBody Person
	 */
	@PostMapping
	public  ResponseEntity<Person> save(@RequestBody Person person)  {
		logger.info("save, RequestBody: person={} ", person );
			 personsService.save(person);
			 return new ResponseEntity<>(person,HttpStatus.OK);	 
	}
	
	
	/**
	 * Update a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 * @return Person update 
	 */
	@PutMapping("/{firstname}/{lastname}")
	public ResponseEntity<Void> update(@PathVariable String firstname,@PathVariable String lastname, @RequestBody Person person)  {
		logger.info("update, params: firstname={}, lastname={}, RequestBody: person={} ", firstname, lastname, person );
            List<Person> existingPerson = personsService.getByName(firstname,lastname);
			if(!existingPerson.isEmpty()) {
				personsService.update(firstname, lastname, person);
	            return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity <>(HttpStatus.NOT_FOUND);
			}
	}
	
	/**
	 * Delete a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 */
	@DeleteMapping("/{firstname}/{lastname}")
	public ResponseEntity<String> delete (@PathVariable String firstName, @PathVariable String lastName)  {
		logger.info("delete, params: firstname={}, lastname={}", firstName, lastName);
			List<Person> existingPerson = personsService.getByName(firstName,lastName);
			if(!existingPerson.isEmpty()) {
			personsService.delete(firstName,lastName);
			return ResponseEntity.ok("Suppression effectuer");
			}else{
				return ResponseEntity.ok("Not found");
			} 
	}


}
