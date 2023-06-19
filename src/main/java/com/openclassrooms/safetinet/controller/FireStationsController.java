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
	public ResponseEntity <Iterable<FireStations>> getAll()throws IOException{
		return new ResponseEntity<>(fireStationsService.getAll(), HttpStatus.OK);
	}
	

	@GetMapping("/{address}")
	public ResponseEntity<FireStations> getOne(@PathVariable String address ) throws IOException{
		return fireStationsService.findByAdress(address)
				.map(station -> new ResponseEntity<>(station, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public FireStations save(@RequestBody FireStations fireStations) throws IOException{
		fireStationsService.save(fireStations);
		return fireStations;
	}
	
	
	@DeleteMapping("/{address}")
	public ResponseEntity<Void> delete (@PathVariable String address){
		Optional<FireStations> existingFireStations = fireStationsService.findByAdress(address);
		if(existingFireStations.isPresent()) {
			fireStationsService.delete(address);
		return new ResponseEntity <>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/{address}")
	public ResponseEntity<Void> update(@PathVariable String address, @RequestBody FireStations fireStation){
		Optional<FireStations> existingFireStations = fireStationsService.findByAdress(address);
		if(existingFireStations.isPresent()) {
			fireStationsService.update(address, fireStation);
            return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}


}
