package com.openclassrooms.safetinet.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.model.Person;


@Repository
public class PersonsRepository {

	
	@Autowired
	public List<Person> getPersons() {
		return Data.getPersons();
	}
	
	
	/*
	@Autowired
	public void deleteByName(String firstname) {
	}
	/*
	@Autowired
	public void deleteByName(String firstname) {
		Person person = entityManager.find(Person.class, lastname);
		entityManager.remove(person);
	}
	*/
	
}
