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
    Data data = new Data();
    
    
    /**
	 * GET ALL
	 *  Get all person
	 *  @return
	 */
	public List<Person> getAll() {
		try {
			return data.getAllPersons();
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
	public boolean save(Person person){
		try {
			return data.getAllPersons().add(person);
	   	}catch(Exception e) {
			logger.error("Error save : " + e);
			return false;
	   	}
	}
	
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public Optional<Person> findByName(String firstname,String lastname) {
		try {
			return data.getPersonByName(lastname, lastname);
	   	}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
	   	}
		 
		
	}
	
	/**
	 * PUT
	 * mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés)
	 * @param Person person
	 * @return
	 */
	public Optional<Person> update(Person person)  {
		try {
			return data.updatePerson(person);
	   	}catch(Exception e) {
			logger.error("Error update person : " + e);
	        return Optional.empty();
	   	}
	}
	
	/**
	 * DELETE
	 * supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 * @return
	 */
	public boolean delete(Person person) {
		try {
			data.deletePerson(person);
			return true;
	   	}catch(Exception e) {
			logger.error("Error delete person : " + e);
			return false;
	   	}	 
	}
	
	
	
	//DTO

	public List<Person> getPersonByAddress(String address) {
		try {
			//System.out.println("address" + address);
			//System.out.println("Data" +  Data.getPersonByAddress(address));
			return data.getPersonByAddress(address);
		}catch(Exception e) {
			logger.error("Error get person : " + e);
	        return new ArrayList<Person>();
	   	}
	}
	
	public List<Person>findEmailByCity(String city){
		try { return data.getEmailByCity(city);
			
		}catch(Exception e) {
			logger.error("Error get person : " + e);
	        return new ArrayList<>();
	   	}
	}
	
	
	 
	
}
