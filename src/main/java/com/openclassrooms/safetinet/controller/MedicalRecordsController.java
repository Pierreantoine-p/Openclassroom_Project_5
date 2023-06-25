package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.MedicalRecordsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordsController {

	private MedicalRecordsService medicalRecordsService;

    private static final Logger logger = LogManager.getLogger(MedicalRecordsController.class);

	
	public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}
	
	
	
	@GetMapping
	public List<MedicalRecords> getAll() throws IOException{
		try {
			return medicalRecordsService.getAll();
		}catch(Exception e) {
			logger.error("Error : " + e);

	       	throw new IOException();
		}
	}
	
	@GetMapping("/{firstname}/{lastname}")
	public ResponseEntity<MedicalRecords> getOne(@PathVariable String firstname, @PathVariable String lastname) throws IOException{
		try {
			return medicalRecordsService.findByName(firstname, lastname)
					.map(medicalRecord -> new ResponseEntity<>(medicalRecord, HttpStatus.OK))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}catch(Exception e) {
			logger.error("Error : " + e);

	       	throw new IOException();
		}
	}
	
	@PostMapping
	public MedicalRecords save(@RequestBody MedicalRecords medicalRecords) throws IOException{
		try {
			medicalRecordsService.save(medicalRecords);
			return medicalRecords;
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}

	}
	
	
	@PutMapping("/{firstname}/{lastname}")
	public ResponseEntity<Void> update(@PathVariable String firstname,@PathVariable String lastname, @RequestBody MedicalRecords medicalRecord) throws IOException{
		try {
			Optional<MedicalRecords> existingMedicalRecords = medicalRecordsService.findByName(firstname,lastname);
			if(existingMedicalRecords.isPresent()) {
				medicalRecordsService.update(firstname, lastname, medicalRecord);
	            return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity <>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
		
	}
	
	@DeleteMapping("/{firstname}/{lastname}")
	public ResponseEntity<String> delete (@PathVariable String firstname, @PathVariable String lastname)throws IOException{
		try {
			Optional<MedicalRecords> existingMedicalRecords = medicalRecordsService.findByName(firstname,lastname);
			if(existingMedicalRecords.isPresent()) {
				medicalRecordsService.delete(firstname,lastname);
				return ResponseEntity.ok("Suppression effectuer");
			}else{
				return ResponseEntity.ok("Not found");
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
		
	}
	
}



	
			