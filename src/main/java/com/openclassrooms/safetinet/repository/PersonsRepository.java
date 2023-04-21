package com.openclassrooms.safetinet.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.model.Person;

@Repository
//public interface PersonsRepository extends CrudRepository {
public class PersonsRepository{

	
	@Autowired
	public List<Person> getPersons() {
		return Data.getPersons();
	}
	
	

	/**
	 * supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique).
	 */
	@Autowired
	public void Person deletePerson() {
		Person.delete();
	}
	
	
	/**
	 *  ajouter une nouvelle personne ;
	 */
	@Autowired
	public List<Person> savePerson(){
		return Person.save(Person person) {
		}
	}
	
	
	/**
	 * mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés) ;
	 */
}
