package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.FireStationsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
@RequestMapping("/firestation")
public class FireStationsController {
	
	private FireStationsService fireStationsService;
	
	
	public FireStationsController(FireStationsService fireStationsService) {
		this.fireStationsService = fireStationsService;
	}
	
	
	@GetMapping
	public List<FireStations> findAll()throws IOException{
		return fireStationsService.getfireStations() ;
	}
	

	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody FireStations fireStations) throws IOException{
		fireStationsService.save(fireStations);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{firstName}")
	public ResponseEntity<Void> deletePersonByName (@PathVariable String firstName){
		Optional<FireStations> existingFireStations = fireStationsService.findPersonByName(firstName);
		if(existingFireStations.isPresent()) {
			fireStationsService.deletePerson(firstName);
		return new ResponseEntity <>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/{firstName}")
	public ResponseEntity<Void> updatePerson(@PathVariable String firstName, @RequestBody FireStations fireStations){
		Optional<FireStations> existingFireStations = fireStationsService.findPersonByName(firstName);
		if(existingFireStations.isPresent()) {
			fireStationsService.updatePerson(fireStations);
            return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{firstname}")
	public ResponseEntity<FireStations> getPersonByName(@PathVariable String address ) throws IOException{
		return fireStationsService.findPersonByName(address)
				.map(person -> new ResponseEntity<>(person, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
