package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
@Component
public class PersonsService {

    
    public PersonsRepository personsRepository;

    public PersonsService(PersonsRepository personsRepository) {
		this.personsRepository = personsRepository;
	}
    
	public List<Person> getPersons() {
		return personsRepository.getPersons();
	}
	

	public void save(Person person) {
		personsRepository.save(person);
	}
	
	
	public void deletePerson(String firstname, String lastname ) {
	personsRepository.delete(firstname, lastname);
	}
	
	
	public Person updatePerson(Person person) {
		personsRepository.update(person);
		return person;
	}
	
	public Optional<Person> findPersonByName(String firstname, String lastname) {
		return personsRepository.findByName(firstname,lastname);
	}
	
	
	
	//TODO dto
	
}
