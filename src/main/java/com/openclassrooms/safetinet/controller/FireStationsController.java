package com.openclassrooms.safetinet.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.service.FireStationsService;

@RestController
@RequestMapping("/firestation")
public class FireStationsController {
	
	private FireStationsService fireStationsService;
	
    private static final Logger logger = LogManager.getLogger(FireStationsController.class);
	
    
	public FireStationsController(FireStationsService fireStationsService) {
		this.fireStationsService = fireStationsService;
	}
	
	  
	/*
	@GetMapping
	public ResponseEntity <Iterable<FireStations>> getAll() throws IOException{

		try {
			return new ResponseEntity<>(fireStationsService.getAll(), HttpStatus.OK);

		}catch(Exception e) {
			logger.error("Error : " + e);
		    throw new IOException();
		}
	}
	*/

    ObjectMapper objectMapper = new ObjectMapper();

	
	@GetMapping("/{address}")
	public ResponseEntity<String> getOne(@PathVariable String address )   {
		try {
			Optional<FireStations> station = fireStationsService.findByAdress(address);
			if(station.isPresent()) {
				FireStations fireStations = station.get();
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>("{}",HttpStatus.OK);
			}
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping
	public ResponseEntity<FireStations> save(@RequestBody FireStations fireStations)  {
		try {
			fireStationsService.save(fireStations);
			return new ResponseEntity<>( fireStations,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	@DeleteMapping("/{address}")
	public ResponseEntity<Void> delete (@PathVariable String address)   {
		try {
			Optional<FireStations> existingFireStations = fireStationsService.findByAdress(address);
			if(existingFireStations.isPresent()) {
				fireStationsService.delete(address);
			return new ResponseEntity <>(HttpStatus.NO_CONTENT);
			}else{
				return new ResponseEntity <>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@PutMapping("/{address}")
	public ResponseEntity<Void> update(@PathVariable String address, @RequestBody FireStations fireStation)  {
		try {
			Optional<FireStations> existingFireStations = fireStationsService.findByAdress(address);
			if(existingFireStations.isPresent()) {
				fireStationsService.update(address, fireStation);
	            return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity <>(HttpStatus.NOT_FOUND);
			}	
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
