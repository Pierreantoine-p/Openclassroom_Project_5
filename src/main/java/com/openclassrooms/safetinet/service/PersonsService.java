package com.openclassrooms.safetinet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
public class PersonsService {

    
    public PersonsRepository personsRepository;

    public PersonsService(PersonsRepository personsRepository) {
		this.personsRepository = personsRepository;
	}
    
	
	public List<Person> getPersons() {
		return personsRepository.getPersons();
	}
	
	public Person savePerson(Person person) {
		return personsRepository.savePerson(person);
	}
	
	/*
	public void deletePerson(String firstname, String lastname) {
		personsRepository.deleteByName(firstname, lastname);
		
	}
	*/
	
	
	
	
}
