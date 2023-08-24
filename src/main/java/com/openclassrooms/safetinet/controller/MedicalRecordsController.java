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


	/**
	 * get à list of MedicalRecords
	 * @return List all MedicalRecords
	 */
	@GetMapping
	public ResponseEntity <List<MedicalRecords>> getMedicalRecords()  {
		logger.info("getMedicalRecords");
		return new ResponseEntity<> (medicalRecordsService.getAll(), HttpStatus.OK);
	}
	
	/**
	 * get one MedicalRecords
	 * @Param firstName, lastName
	 * @return One MedicalRecord  
	 */
	@GetMapping("/{firstname}/{lastname}")
	public ResponseEntity<MedicalRecords> getMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
		logger.info("getMedicalRecord, params: firstname={}, lastname={}", firstName, lastName );
		List< MedicalRecords> result =  medicalRecordsService.findByName(firstName, lastName);
		return result != null ? new ResponseEntity<MedicalRecords>(result.get(0), HttpStatus.OK) : new ResponseEntity<MedicalRecords>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Created one MedicalRecords
	 * @RequestBody medicalRecords
	 * @return One MedicalRecord  
	 */
	@PostMapping
	public ResponseEntity<MedicalRecords> save(@RequestBody MedicalRecords medicalRecords)  {
		logger.info("save, RequestBody: medicalRecord={} ", medicalRecords );
		medicalRecordsService.save(medicalRecords);
		return new ResponseEntity<>(medicalRecords,HttpStatus.OK);
	}

	/**
	 * Update MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 * @RequestBody medicalRecords
	 * @return MedicalRecord update
	 */
	@PutMapping("/{firstname}/{lastname}")
	public ResponseEntity<Void> update(@PathVariable String firstName,@PathVariable String lastName, @RequestBody MedicalRecords medicalRecords)  {
		logger.info("update, params: firstname={}, lastname={}, RequestBody: medicalRecord={} ", firstName, lastName, medicalRecords );
		List<MedicalRecords> existingMedicalRecords = medicalRecordsService.findByName(firstName,lastName);
		if(existingMedicalRecords!= null) {
			medicalRecordsService.update(firstName, lastName, medicalRecords);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity <>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 */
	@DeleteMapping("/{firstname}/{lastname}")
	public ResponseEntity<String> delete (@PathVariable String firstName, @PathVariable String lastName) {
		logger.info("delete, params: firstname={}, lastname={} ", firstName, lastName );
		List<MedicalRecords> existingMedicalRecords = medicalRecordsService.findByName(firstName,lastName);
		if(existingMedicalRecords!= null) {
			medicalRecordsService.delete(firstName,lastName);
			return ResponseEntity.ok("Suppression effectuer");
		}else{
			return ResponseEntity.ok("Not found");
		}
	}

}




