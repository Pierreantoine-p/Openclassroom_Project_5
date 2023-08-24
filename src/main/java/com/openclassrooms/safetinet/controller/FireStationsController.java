package com.openclassrooms.safetinet.controller;

import java.util.List;

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
	
	/**
	 * Get one FireStations by address
	 * @Param String : address
	 * @return One FireStations
	 */
	@GetMapping("/{address}")
	public ResponseEntity<String> getAddress(@PathVariable String address )   {
		logger.info("getOne, params: address={}", address);
		List<FireStations> station = fireStationsService.findByAdress(address);
		if(!station.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>("{}",HttpStatus.OK);
		}
	}

	/**
	 * Created FireStations 
	 * @RequestBody fireStations
	 * @return New FireStations
	 */
	@PostMapping
	public ResponseEntity<FireStations> save(@RequestBody FireStations fireStations)  {
		logger.info("save, RequestBody: fireStations={}", fireStations);
		fireStationsService.save(fireStations);
		return new ResponseEntity<>( fireStations,HttpStatus.OK);
	}
	
	/**
	 * Update FireStations
	 * @RequestBody fireStation
	 * @Param String : address
	 * @return FireStations update
	 */
	@PutMapping("/{address}")
	public ResponseEntity<Void> update(@PathVariable String address, @RequestBody FireStations fireStation)  {
		logger.info("update, params: address={}, RequestBody: fireStation={} ", address, fireStation );
		List<FireStations> existingFireStations = fireStationsService.findByAdress(address);
		if(!existingFireStations.isEmpty()) {
			fireStationsService.update(address, fireStation);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}	
	}
	
	/**
	 * Delete FireStations
	 * @Param address
	 */
	@DeleteMapping("/{address}")
	public ResponseEntity<String> delete (@PathVariable String address)   {
		logger.info("delete, params: address={}", address);
		List<FireStations> existingFireStations = fireStationsService.findByAdress(address);
		if(!existingFireStations.isEmpty()) {
			fireStationsService.delete(address);
			return ResponseEntity.ok("Suppression effectuer");
		}else{
			return ResponseEntity.ok("Not found");
		}
	}

}
