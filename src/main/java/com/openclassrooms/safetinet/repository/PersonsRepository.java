package com.openclassrooms.safetinet.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.model.Person;

@Repository
public class PersonsRepository {

	@Autowired
	public List<Person> getPersons() {
		return Data.getPersons();
	}
	
	
}
