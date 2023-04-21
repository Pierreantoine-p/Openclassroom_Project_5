package com.openclassrooms.safetinet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	/*
	public Person savePerson(Person person) {
		Person savedPerson = personsRepository.save(person);
		return savedPerson;
	}
	
	public void deletePerson(String firstname) {
		personsRepository.deleteByName(firstname);
	}
	
    /*
	public void deletePerson(String firstname) {
		personsRepository.deleteByName(firstname);
		
	
	}*/
	
	//TODO dto
	
}
