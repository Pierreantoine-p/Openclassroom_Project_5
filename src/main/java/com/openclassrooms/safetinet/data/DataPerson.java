package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.openclassrooms.safetinet.model.Person;

public class DataPerson {

	private static List<Person> persons = new ArrayList<Person>();

	/**
	 * GET ALL PERSONS
	 * @return
	 */
	public static List<Person> getAllPersons() {
		return persons;
	}
	
	/**
	 * POST PERSON
	 * @return
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	/**
	 * GET ONE PERSON
	 * @return
	 */
	public static Optional<Person> getPersonByName(String firstname,String lastname) {
		return persons.stream()
				.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
				.findFirst();
	}

	/**
	 * DELETE PERSON
	 * @param person
	 * @return
	 */
	public static boolean deletePerson(Person person) {
		return persons.remove(person);
	}
	
	/**
	 * UPDATE PERSON
	 * @param person
	 * @return
	 */
	 public static Optional<Person> updatePerson (Person person) {
		 return persons.stream()
		  .filter(p -> p.getFirstName().equals(person.getFirstName()) &&
		            p.getLastName().equals(person.getLastName()))
		  .findFirst()
		  .map(p ->{
			 	p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setZip(person.getZip());
				p.setPhone(person.getPhone());
				p.setEmail(person.getEmail()); 
				return p;
		 });
		  
	 }
}
