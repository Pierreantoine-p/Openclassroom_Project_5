package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.service.FireStationsService;
import com.openclassrooms.safetinet.service.MedicalRecordsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
public class DTOController {

	private FireStationsService fireStationsService;
	private MedicalRecordsService medicalRecordsService;
	private PersonsService personsService;
	
	
    private static final Logger logger = LogManager.getLogger(DTOController.class);
    
    public DTOController(FireStationsService fireStationsService) {
		this.fireStationsService = fireStationsService;
    }
    /*
    public DTOController(PersonsService personsService) {
		this.personsService = personsService;
	}
    
    public DTOController(MedicalRecordsService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}

	public PersonsController(PersonsService personsService) {
		this.personsService = personsService;
	}
	
	public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}
	
	public FireStationsController(FireStationsService fireStationsService) {
		this.fireStationsService = fireStationsService;
	}*/
	
	
	@GetMapping("/firestation")
	public List<FireStations> getPersonByFireStation(@RequestParam(value = "stationNumber",required = false)String stationNumber ) throws IOException {
		try {
			List<FireStations> fireStationList = fireStationsService.findStationByNumber(stationNumber);
			if(fireStationList.isEmpty()) {
				return Collections.emptyList();
			}else {
				return fireStationList;
			}

		}catch (Exception e) {
			logger.error("Error : " + e);
		    throw new IOException();
		}
	}
	/*
	type adresse = fireStationsService.findStationByNumber(stationNumber);
	split take adresse 
	pui envoie adress
*/

/**
* 
* get id 
* id => adress
* adress => person firstname & lastname
* person => medical record birthday
* birthday timestamps => +18 & -18
* count total
* afficher le total
* 
* 
*/
}
