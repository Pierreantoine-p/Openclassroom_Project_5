package com.openclassrooms.safetinet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
public class PersonsService {


	public PersonsRepository personsRepository;

	private static final Logger logger = LogManager.getLogger(PersonsService.class);


	public PersonsService(PersonsRepository personsRepository) {
		this.personsRepository = personsRepository;
	}






	//GET
	public List<Person> getAll() {
		try {
			return personsRepository.getAll();

		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}
	}
	
	public List<Person> getPersonByAddress(String address)  {
		try {
			List<Person> result = personsRepository.getPersonByAddress(address);
			System.out.println("result + " + result);
			return result;
		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}
	}


	//GET ONE
	public List<Person> getByName(String firstname, String lastname)  {
		try {
			return personsRepository.getByName(firstname,lastname);

		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}
	}

	//POST
	public Optional<Person> save(Person person)  {
		try {
			return personsRepository.save(person);
		}catch(Exception e) {
			logger.error("Error : " + e);
			return Optional.empty();
		}
	}

	//DELETE
	public boolean delete(String firstname, String lastname)  {

		boolean result = false;

		try {
			result = personsRepository.delete(firstname, lastname);
		} catch(Exception e) {
			logger.error("Error : " + e);
		}

		return result;
	}

	//PATCH
	/*
	public Optional<Person> update(String firstname, String lastname,Person person) {
		try {
			List<Person> persons = personsRepository.getAll();
			for(Person person1 : persons) {
				if(person1.getFirstName().equals(firstname) && person1.getLastName().equals(lastname)) {
					return personsRepository.update(person);
				}
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
			return Optional.empty();
		}
		return null;
	}
	*/
	
	public boolean update(String firstname, String lastname,Person person) {
		
		boolean result = false;
		
		try {
			result = personsRepository.update(firstname,lastname, person);
		}catch(Exception e) {
			logger.error("Error : " + e);
		}
		return result;

	}


	public List<Person> findEmailByCity(String city){

		try {
			return personsRepository.getEmailByCity(city);

		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}
	}





}
