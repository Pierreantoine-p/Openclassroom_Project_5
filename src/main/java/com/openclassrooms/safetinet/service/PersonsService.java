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
	
	/**
	 *Get a list of all people
	 * @return List of all persons
	 */
	public List<Person> getAll() {
		return personsRepository.getAll();	
	}
	
	/**
	 *Get a list of person by address
	 *@Param String : address
	 * @return List of persons
	 */
	public List<Person> getPersonByAddress(String address)  {
		return personsRepository.getPersonByAddress(address);
	}

	/**
	 *Get a list of person by firstName and lastName
	 *@Param String : firstName
	 *@Param String : lastName
	 * @return List of person
	 */
	public List<Person> getByName(String firstName, String lastName)  {
		return personsRepository.getByName(firstName,lastName);
	}
	
	/**
	 *Get a list of email sort by city
	 *@Param String : city
	 * @return List String : email
	 */
	public List<Person> findEmailByCity(String city){
		return personsRepository.getEmailByCity(city);
	}
	
	/**
	 * Created new person
	 * @RequestBody Person
	 */
	public Optional<Person> save(Person person)  {
		return personsRepository.save(person);
	}
	
	/**
	 * Update a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 * @return Person update 
	 */
	public boolean update(String firstname, String lastname,Person person) {
		boolean result = false;
		result = personsRepository.update(firstname,lastname, person);
		return result;
	}
	
	/**
	 * Delete a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 */
	public boolean delete(String firstname, String lastname)  {
		boolean result = false;
		result = personsRepository.delete(firstname, lastname);
		return result;
	}

}
