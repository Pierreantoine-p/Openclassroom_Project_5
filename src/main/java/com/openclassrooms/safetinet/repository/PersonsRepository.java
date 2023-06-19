package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.data.DataPerson;
import com.openclassrooms.safetinet.model.Person;
import java.lang.String;


@Repository
@Component
//public interface PersonsRepository extends CrudRepository {
public class PersonsRepository{
	
    private final List<Person> persons = new ArrayList<>();
    
    /**
	 * GET ALL
	 *  Get all person
	 *  @return
	 */
	public List<Person> getAll() {
		return Data.getAllPersons();
	}
	
	/**
	 * POST
	 * add new person
	 *  @param Person person
	 */
	public Person save(Person person){
		Data.getAllPersons().add(person);
		return person;
	}
	
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public Optional<Person> findByName(String firstname,String lastname) {
		return Data.getPersonByName(lastname, lastname);
		
	}
	
	/**
	 * PUT
	 * mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés)
	 * @param Person person
	 * @return
	 */
	public boolean update(Person person) {
		Data.updatePerson(person);
		return false;
	}
	
	/**
	 * DELETE
	 * supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 * @return
	 */
	public boolean delete(Person person) {
		Data.deletePerson(person);
		return false;
	}
	
	
	 
	
}
