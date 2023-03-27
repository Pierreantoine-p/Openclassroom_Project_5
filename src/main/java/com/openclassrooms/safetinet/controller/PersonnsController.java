package com.openclassrooms.safetinet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonnsController {

	/*
	
	@Autowired
	private PersonsData personsData;
	
	@GetMapping
	public List<personsData> getAllPersons(){
		return personsData.findAll();
	}
	
	@GetMapping("/{firstName,lastName")
	public Persons getPersonByName(@PathVariable String firstName, String lastName) {
		return personsData.findByString(firstName,lastName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Persons createPersons(@RequestBody Persons persons){
		return personsData.save(persons);
	}
	
	@PutMapping
	public Persons updatePersons(@PathVariable String firstName, String lastName) {
		if(!personsData.existByString(firstName,lastName)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		personsData.set
		return personsData.save(persons);
	}
	/*
	@DeleteMapping("/{firstName,lastName")
	public Persons getPersonByName(@PathVariable String firstName, String lastName) {
		return personsData.findByString(firstName,lastName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	 */
}
