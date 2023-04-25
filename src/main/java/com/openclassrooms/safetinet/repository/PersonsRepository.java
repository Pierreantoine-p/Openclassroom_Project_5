package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import com.openclassrooms.safetinet.data.Data;
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
	public List<Person> getPersons() {
		return Data.getPersons();
	}
	
	/**
	 * POST
	 * add new person
	 *  @param Person person
	 */
	
	public void save(Person person){
		persons.add(person);
	}
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	
	public Optional<Person> findByName(String firstname,String lastname) {
		return persons.stream()
				.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
				.findFirst();
	}
	
	
	
	
	/**
	 * PUT
	 * mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés)
	 * @param Person person
	 * @return
	 */
	
	public Person update(Person person) {
		persons.stream()
		.filter(p -> p.getFirstName() == person.getFirstName() &&
				p.getLastName() == person.getLastName())
		.findFirst()
		.ifPresent(p ->{
			p.setAddress(person.getAddress());
			p.setCity(person.getCity());
			p.setZip(person.getZip());
			p.setPhone(person.getPhone());
			p.setEmail(person.getEmail());
		});
		return person;
	}
	
	/**
	 * DELETE
	 * supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 * @return
	 */
	
	public void delete(@PathVariable("firstname") String firstname, @PathVariable("lastname")String lastname) {
	    persons.removeIf(p -> p.getFirstName().equals(firstname)&& p.getLastName().equals(lastname));
	}
	
	 
	
}
