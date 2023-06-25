package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*
	@GetMapping
	public List<FireStations> getPersonByFireStation(@RequestParam("stationNumber") String stationNumber ) throws IOException{
		return fireStationsService.findStationByNumber(stationNumber);
	}*/
	
	/*
	@GetMapping
	public List<FireStations> getPersonByFireStation(@RequestParam(value = "stationNumber",required = false)String stationNumber ) throws IOException {
		try {
			if(stationNumber == null) {
				//logger.info
				return fireStationsService.getAll();
			}else {
				//logger.info
				List<FireStations> fireStationList = fireStationsService.findStationByNumber(stationNumber);
				if(fireStationList.isEmpty()) {
					return Collections.emptyList();
				}else {
					return fireStationList;
				}		
			}
		}catch (Exception e){
			logger.error("Error : " + e);
		    throw new IOException();
		}
	}
	*/
	
	
	
    ObjectMapper objectMapper = new ObjectMapper();

	
	@GetMapping("/{address}")
	public ResponseEntity<String> getOne(@PathVariable String address ) throws IOException {
        String emptyJson = "{}";
        
		try {
            String jsonString = objectMapper.writeValueAsString(emptyJson);
			Optional<FireStations> station = fireStationsService.findByAdress(address);
			if(station.isPresent()) {
				FireStations fireStations = station.get();
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
	   
				return new ResponseEntity<>("{}",HttpStatus.OK);

			}
			/*
			return station.map(s -> new ResponseEntity<>(s, HttpStatus.OK))
					.orElse(new ResponseEntity<>(jsonString, HttpStatus.OK));

					//orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
					//orElse(EmptyObject.getEmptyObject());


					//orElse(new ResponseEntity<>(new EmptyJsonBody(), HttpStatus.OK ));

					//orElse(array);
		
			//logger.info
			return fireStationsService.findByAdress(address)
					.map(station -> new ResponseEntity<>(station, HttpStatus.OK))
					//orElse(new ResponseEntity<>(EmptyObject.getEmptyObject(), HttpStatus.OK));
					.orElse(EmptyObject.getEmptyObject());
					*/
			/*	if(station.isPresent()) {
				return new ResponseEntity<>(station.get(), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND );
			}*/
		}catch (Exception e) {
			logger.error("Error : " + e);

		    throw new IOException();
		}
		
	}

	
	@PostMapping
	public FireStations save(@RequestBody FireStations fireStations) throws IOException{
		try {
			fireStationsService.save(fireStations);
			return fireStations;
		}catch(Exception e) {
			logger.error("Error : " + e);

	        throw new IOException();
		}
	
	}
	
	
	@DeleteMapping("/{address}")
	public ResponseEntity<Void> delete (@PathVariable String address) throws IOException {
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

		    throw new IOException();
		}
	}

	
	
	@PutMapping("/{address}")
	public ResponseEntity<Void> update(@PathVariable String address, @RequestBody FireStations fireStation) throws IOException {
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

		    throw new IOException();
		}
	}
	
}
