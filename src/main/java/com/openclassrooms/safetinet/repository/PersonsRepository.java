package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.DataWrapper;

import com.openclassrooms.safetinet.model.Person;

import lombok.RequiredArgsConstructor;

import java.lang.String;


@Repository
@RequiredArgsConstructor
public class PersonsRepository{

	private final DataWrapper dataWrapper;


	private static final Logger logger = LogManager.getLogger(PersonsRepository.class);


	/**
	 * GET ALL
	 *  Get all person
	 *  @return
	 */
	public List<Person> getAll() {
		try {
			return dataWrapper.getAllPersons();
		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<Person>();
		}

	}



	/**
	 * POST
	 * add new person
	 *  @param Person person
	 */
	public Optional<Person> save(Person person){
		try { 
			boolean isAdded = dataWrapper.getAllPersons().add(person);
			if (isAdded) {
				return Optional.of(person);
			} else {
				return Optional.empty();
			}
		}catch(Exception e) {
			logger.error("Error save : " + e);
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
		try {
			return dataWrapper.getPersonByName(firstname, lastname);
		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}


	}

	/**
	 * PUT
	 * mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés)
	 * @param Person person
	 * @return
	 */
	public boolean update(String firstname, String lastname,Person person)  {
		boolean result = false;

		try {
			result = dataWrapper.updatePerson(firstname,lastname,person);
		}catch(Exception e) {
			logger.error("Error update person : " + e);
		}
		return result;

	}

	/**
	 * DELETE
	 * supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 * @return
	 */
	public boolean delete(String firstname, String lastname) {

		boolean result = false;
		try {
			result = dataWrapper.deletePerson(firstname,lastname);
		}catch(Exception e) {
			logger.error("Error delete person : " + e);
		}	 

		return result;
	}



	//DTO

	public List<Person> getPersonByAddress(String address) {
		try {
			List<Person> result = dataWrapper.getPersonByAddress(address);
			System.out.println("result repository : " + result);
			return result;
		}catch(Exception e) {
			logger.error("Error get person : " + e);
			return new ArrayList<>();
		}
	}

	public List<Person>getEmailByCity(String city){
		try { return dataWrapper.getEmailByCity(city);	
		}catch(Exception e) {
			logger.error("Error get person : " + e);
			return new ArrayList<>();
		}
	}




}
