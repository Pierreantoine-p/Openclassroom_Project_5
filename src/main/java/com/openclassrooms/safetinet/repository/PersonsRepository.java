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
	 * @return All persons
	 */
	public List<Person> getAll() {
		return dataWrapper.getAllPersons();
	}

	/**
	 * POST
	 * add new person
	 *  @param Person person
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
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public List<Person> getByName(String firstname,String lastname) {
		return dataWrapper.getPersonByName(firstname, lastname);
	}

	/**
	 * PUT
	 * @param Person person
	 * @return
	 */
	public boolean update(String firstname, String lastname,Person person)  {
		boolean result = false;
		result = dataWrapper.updatePerson(firstname,lastname,person);
		return result;
	}

	/**
	 * DELETE
	 * @param String firstname
	 * @return
	 */
	public boolean delete(String firstname, String lastname) {
		boolean result = false;
		result = dataWrapper.deletePerson(firstname,lastname);
		return result;
	}

	public List<Person> getPersonByAddress(String address) {
		return dataWrapper.getPersonByAddress(address);
	}

	public List<Person>getEmailByCity(String city){
		return dataWrapper.getEmailByCity(city);	
	}


}
