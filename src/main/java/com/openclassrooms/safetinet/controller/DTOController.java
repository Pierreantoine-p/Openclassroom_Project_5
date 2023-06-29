package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.model.PersonByStationDTO;
import com.openclassrooms.safetinet.service.FireStationsService;
import com.openclassrooms.safetinet.service.MedicalRecordsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
public class DTOController {

	private FireStationsService fireStationsService;
	private MedicalRecordsService medicalRecordsService;
	private PersonsService personsService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
    private static final Logger logger = LogManager.getLogger(DTOController.class);
    
    public DTOController(FireStationsService fireStationsService, PersonsService personsService, MedicalRecordsService medicalRecordsService) {
		this.fireStationsService = fireStationsService;
		this.personsService = personsService;
		this.medicalRecordsService = medicalRecordsService;


    }
    /*
    public DTOController(PersonsService personsService) {
		this.personsService = personsService;
	}
	*/
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
	
	/*
	@GetMapping("/firestation")
	public List<PersonByStationDTO> getPersonByFireStation(@RequestParam(value = "stationNumber",required = false)String stationNumber ) throws IOException {
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
	*/
    
	@GetMapping("/firestation")
	public List<PersonByStationDTO> getPersonByFireStation(@RequestParam(value = "stationNumber",required = false)String stationNumber) throws IOException {
		try {
			List<FireStations> fireStationList = fireStationsService.findStationByNumber(stationNumber);
			if(fireStationList.isEmpty()) {
				return Collections.emptyList();
			}else {
 
				String jsonStrings = objectMapper.writeValueAsString(fireStationList);

				JsonNode jsonNode = objectMapper.readTree( jsonStrings);

				for (JsonNode jsonString : jsonNode)
				{
					String address = jsonString.get("address").toString();
					
					List<Person> persons = personsService.getPersonByAddress(address);
					
					for (Person person : persons) {
						String firstname = person.getFirstName();
						String lastname = person.getLastName();

						medicalRecordsService.getMedicalByName(firstname, lastname);
					}
				}
				return null;
			}
		}catch (Exception e) {
			logger.error("Error : " + e);
		    throw new IOException();
		}
	}


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
