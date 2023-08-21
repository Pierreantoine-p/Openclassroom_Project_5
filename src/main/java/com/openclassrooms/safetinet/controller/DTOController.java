package com.openclassrooms.safetinet.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.model.DTO.AddressByStationDTO;
import com.openclassrooms.safetinet.model.DTO.ListPersonByAddressDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByAdressWithFireStationListDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByFirstNameAndLastNameDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationWithCountDTO;
import com.openclassrooms.safetinet.service.DTOService;
import com.openclassrooms.safetinet.service.FireStationsService;
import com.openclassrooms.safetinet.service.MedicalRecordsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
public class DTOController {

	private DTOService dTOService;

	ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger logger = LogManager.getLogger(DTOController.class);

	public DTOController(FireStationsService fireStationsService, PersonsService personsService, MedicalRecordsService medicalRecordsService, DTOService dTOService) {
		this.dTOService = dTOService;
	}


	@GetMapping("/firestation")
	public ResponseEntity<PersonByStationWithCountDTO> getPersonByFireStation(@RequestParam(value = "stationNumber")String stationNumber)   {
		logger.info("getPersonByFireStation, params: stationNumber={}", stationNumber);
		return new ResponseEntity<>(dTOService.getPersonByStation(stationNumber), HttpStatus.OK);
	}

	@GetMapping("/phoneAlert")
	public ResponseEntity<List<String>> getPhoneByStation(@RequestParam(value = "stationNumber")String stationNumber){
		logger.info("getPhoneByStation, params: stationNumber={}", stationNumber);
		return new ResponseEntity<>(dTOService.getPhoneByStation(stationNumber),HttpStatus.OK);
	}

	@GetMapping("/fire")
	public ResponseEntity<PersonByAdressWithFireStationListDTO> getFireByAddress(@RequestParam(value = "address")String address){
		logger.info("getFireByAddress, params: address={}", address);
		return new ResponseEntity<>(dTOService.getFireByAddress(address),HttpStatus.OK);
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<AddressByStationDTO> getHouseholdByStation(@RequestParam(value = "stations")String stationNumber){
		logger.info("getHouseholdByStation, params: stationNumber={}", stationNumber);
		return new ResponseEntity<>(dTOService.getHouseholdByStation(stationNumber),HttpStatus.OK);
	}


	@GetMapping("/personInfo")
	public ResponseEntity<List<PersonByFirstNameAndLastNameDTO>> getPersondByStation(@RequestParam(value = "firstName") String firstname,@RequestParam(value = "lastName") String lastname){
		logger.info("getPersondByStation, params: firstname={},  lastname={}", firstname, lastname);
		return new ResponseEntity<>(dTOService.getPersonByStation(firstname,lastname),HttpStatus.OK);
	}

	@GetMapping("/communityEmail")
	public ResponseEntity<List<String>> getEmailByCity(@RequestParam(value = "city")String city){
		logger.info("getEmailByCity, params: city={}", city);
		return new ResponseEntity<>(dTOService.getEmailByCity(city),HttpStatus.OK);
	}



	@GetMapping("/childAlert")
	public ResponseEntity<ListPersonByAddressDTO> getChildByAddress (@RequestParam(value = "address")String address) {
		logger.info("getChildByAddress, params: address={}", address);
		return new ResponseEntity<>(dTOService.getChildByAddress(address),HttpStatus.OK);
	}

}
