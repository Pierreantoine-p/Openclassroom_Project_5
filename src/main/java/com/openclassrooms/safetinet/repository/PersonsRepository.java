package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.Data;

import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.PersonsService;

import java.io.IOException;
import java.lang.String;


@Repository
@Component
//public interface PersonsRepository extends CrudRepository {
public class PersonsRepository{
	
    private final List<Person> persons = new ArrayList<>();
    
    private static final Logger logger = LogManager.getLogger(PersonsRepository.class);

    
    
    /**
	 * GET ALL
	 *  Get all person
	 *  @return
	 */
	public List<Person> getAll()throws IOException {
		try {
			return Data.getAllPersons();
	   	}catch(Exception e) {
			logger.error("Error : " + e);

	           throw new IOException();
	   	}
		 
	}
	
	/**
	 * POST
	 * add new person
	 *  @param Person person
	 */
	public Person save(Person person)throws IOException{
		try {
			Data.getAllPersons().add(person);
			return person;
	   	}catch(Exception e) {
			logger.error("Error : " + e);

	           throw new IOException();
	   	}
		 
	}
	
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public Optional<Person> findByName(String firstname,String lastname)throws IOException {
		try {
			return Data.getPersonByName(lastname, lastname);
	   	}catch(Exception e) {
			logger.error("Error : " + e);

	           throw new IOException();
	   	}
		 
		
	}
	
	/**
	 * PUT
	 * mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés)
	 * @param Person person
	 * @return
	 */
	public boolean update(Person person)throws IOException {
		try {
			Data.updatePerson(person);
			return false;
	   	}catch(Exception e) {
			logger.error("Error : " + e);

	           throw new IOException();
	   	}
		 
	}
	
	/**
	 * DELETE
	 * supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 * @return
	 */
	public boolean delete(Person person)throws IOException {
		try {
			Data.deletePerson(person);
			return false;
	   	}catch(Exception e) {
			logger.error("Error : " + e);

	           throw new IOException();
	   	}
		 
	}
	
	
	
	//DTO

	public List<Person> getPersonByAddress(String address)throws IOException {
		try {
			return Data.getPersonByAddress(address);
		}catch(Exception e) {
			logger.error("Error : " + e);
	           throw new IOException();
	   	}
	}
	
	
	 
	
}
