package com.openclassrooms.safetinet.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.controller.PersonsController;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
@Component
public class PersonsService {

    
    public PersonsRepository personsRepository;

    private static final Logger logger = LogManager.getLogger(PersonsService.class);

    
    public PersonsService(PersonsRepository personsRepository) {
		this.personsRepository = personsRepository;
	}
    
  
    
   
    
    //GET
	public List<Person> getAll()throws IOException {
		try {
			return personsRepository.getAll();

		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
	}
	
	public List<Person> getPersonByAddress(String address)throws IOException {
		try {
			return personsRepository.getPersonByAddress(String address);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
	}
	
	
	//GET ONE
	public Optional<Person> findByName(String firstname, String lastname)throws IOException {
		try {
			return personsRepository.findByName(firstname,lastname);

		}catch(Exception e) {
			logger.error("Error : " + e);

	        throw new IOException();
	}
 	}
	
	//POST
	public void save(Person person)throws IOException {
		try {
			personsRepository.save(person);

		}catch(Exception e) {
			logger.error("Error : " + e);

	        throw new IOException();
	}
 	}
	
	//DELETE	
	public boolean  delete(String firstname, String lastname)throws IOException {
		try {
			List<Person> persons = personsRepository.getAll();
			for(Person person : persons) {
				if(person.getFirstName().equals(firstname) && person.getLastName().equals(lastname)) {
					return personsRepository.delete(person);
				}
			}
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);

	        throw new IOException();
	}
	 
		}
	
	//PATCH
	public boolean update(String firstname, String lastname,Person person)throws IOException {
		try {
			List<Person> persons = personsRepository.getAll();
			for(Person person1 : persons) {
				if(person1.getFirstName().equals(firstname) && person1.getLastName().equals(lastname)) {
					return personsRepository.update(person);
				}
			}
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);

	        throw new IOException();
	}
		 
	}
	
	
	
	
	
	//TODO dto
	
}
