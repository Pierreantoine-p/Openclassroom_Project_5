package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
@Component
public class PersonsService {

    
    public PersonsRepository personsRepository;

    public PersonsService(PersonsRepository personsRepository) {
		this.personsRepository = personsRepository;
	}
    
    //GET
	public List<Person> getAll() {
		return personsRepository.getAll();
	}
	
	//GET ONE
	public Optional<Person> findByName(String firstname, String lastname) {
		return personsRepository.findByName(firstname,lastname);
	}
	
	//POST
	public void save(Person person) {
		personsRepository.save(person);
	}
	
	//DELETE	
	public boolean  delete(String firstname, String lastname) {
		List<Person> persons = personsRepository.getAll();
		for(Person person : persons) {
			if(person.getFirstName().equals(firstname) && person.getLastName().equals(lastname)) {
				return personsRepository.delete(person);
			}
		}
		return false;
		}
	
	//PATCH
	public boolean update(String firstname, String lastname,Person person) {
		List<Person> persons = personsRepository.getAll();
		for(Person person1 : persons) {
			if(person1.getFirstName().equals(firstname) && person1.getLastName().equals(lastname)) {
				return personsRepository.update(person);
			}
		}
		return false;
	}
	
	
	
	
	
	//TODO dto
	
}
