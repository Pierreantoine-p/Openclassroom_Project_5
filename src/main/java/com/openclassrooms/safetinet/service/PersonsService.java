package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
public class PersonsService {


	public PersonsRepository personsRepository;


	public PersonsService(PersonsRepository personsRepository) {
		this.personsRepository = personsRepository;
	}

	public List<Person> getAll() {
		return personsRepository.getAll();	
	}

	public List<Person> getPersonByAddress(String address)  {
		return personsRepository.getPersonByAddress(address);
	}

	public List<Person> getByName(String firstname, String lastname)  {
		return personsRepository.getByName(firstname,lastname);
	}

	public Optional<Person> save(Person person)  {
		return personsRepository.save(person);
	}

	public boolean delete(String firstname, String lastname)  {
		boolean result = false;
		result = personsRepository.delete(firstname, lastname);
		return result;
	}

	public boolean update(String firstname, String lastname,Person person) {
		boolean result = false;
		result = personsRepository.update(firstname,lastname, person);
		return result;
	}

	public List<Person> findEmailByCity(String city){
		return personsRepository.getEmailByCity(city);
	}

}
