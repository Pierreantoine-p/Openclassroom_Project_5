package com.openclassrooms.safetinet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.DataWrapper;

import com.openclassrooms.safetinet.model.Person;

import lombok.RequiredArgsConstructor;

import java.lang.String;


@Repository
@RequiredArgsConstructor
public class PersonsRepository{

	private final DataWrapper dataWrapper;


	/**
	 *Get a list of all people
	 * @return List of all persons
	 */
	public List<Person> getAll() {
		return dataWrapper.getAllPersons();
	}
	
	/**
	 *Get a list of person by address
	 *@Param String : address
	 * @return List of persons
	 */
	public List<Person> getPersonByAddress(String address) {
		return dataWrapper.getPersonByAddress(address);
	}
	
	/**
	 *Get a list of email sort by city
	 *@Param String : city
	 * @return List String : email
	 */
	public List<Person>getEmailByCity(String city){
		return dataWrapper.getEmailByCity(city);	
	}
	
	/**
	 *Get a list of person by firstName and lastName
	 *@Param String : firstName
	 *@Param String : lastName
	 * @return List of person
	 */
	public List<Person> getByName(String firstname,String lastname) {
		return dataWrapper.getPersonByName(firstname, lastname);
	}
	
	/**
	 * Created new person
	 * @RequestBody Person
	 */
	public Optional<Person> save(Person person){
		boolean isAdded = dataWrapper.getAllPersons().add(person);
		if (isAdded) {
			return Optional.of(person);
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Update a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 * @return Person update 
	 */
	public boolean update(String firstname, String lastname,Person person)  {
		boolean result = false;
		result = dataWrapper.updatePerson(firstname,lastname,person);
		return result;
	}

	/**
	 * Delete a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 */
	public boolean delete(String firstname, String lastname) {
		boolean result = false;
		result = dataWrapper.deletePerson(firstname,lastname);
		return result;
	}

	


}
