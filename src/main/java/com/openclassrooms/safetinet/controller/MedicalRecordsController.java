package com.openclassrooms.safetinet.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.service.MedicalRecordsService;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordsController {

	private MedicalRecordsService medicalRecordsService;

	private static final Logger logger = LogManager.getLogger(MedicalRecordsController.class);


	public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}



	@GetMapping
	public ResponseEntity <List<MedicalRecords>> getMedicalRecords()  {
		logger.info("getMedicalRecords");
		return new ResponseEntity<> (medicalRecordsService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{firstname}/{lastname}")
	public ResponseEntity<MedicalRecords> getMedicalRecord(@PathVariable String firstname, @PathVariable String lastname) {
		logger.info("getMedicalRecord, params: firstname={}, lastname={}", firstname, lastname );
		List< MedicalRecords> result =  medicalRecordsService.findByName(firstname, lastname);
		return result != null ? new ResponseEntity<MedicalRecords>(result.get(0), HttpStatus.OK) : new ResponseEntity<MedicalRecords>(HttpStatus.NO_CONTENT);
	}


	@PostMapping
	public ResponseEntity<MedicalRecords> save(@RequestBody MedicalRecords medicalRecords)  {
		logger.info("save, RequestBody: medicalRecord={} ", medicalRecords );
		medicalRecordsService.save(medicalRecords);
		return new ResponseEntity<>(medicalRecords,HttpStatus.OK);
	}


	@PutMapping("/{firstname}/{lastname}")
	public ResponseEntity<Void> update(@PathVariable String firstname,@PathVariable String lastname, @RequestBody MedicalRecords medicalRecords)  {
		logger.info("update, params: firstname={}, lastname={}, RequestBody: medicalRecord={} ", firstname, lastname, medicalRecords );
		List<MedicalRecords> existingMedicalRecords = medicalRecordsService.findByName(firstname,lastname);
		if(existingMedicalRecords!= null) {
			medicalRecordsService.update(firstname, lastname, medicalRecords);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{firstname}/{lastname}")
	public ResponseEntity<String> delete (@PathVariable String firstname, @PathVariable String lastname) {
		logger.info("delete, params: firstname={}, lastname={} ", firstname, lastname );
		List<MedicalRecords> existingMedicalRecords = medicalRecordsService.findByName(firstname,lastname);
		if(existingMedicalRecords!= null) {
			medicalRecordsService.delete(firstname,lastname);
			return ResponseEntity.ok("Suppression effectuer");
		}else{
			return ResponseEntity.ok("Not found");
		}
	}

}




