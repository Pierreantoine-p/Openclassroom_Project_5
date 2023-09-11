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

	/**
	 * Get a list of people covered by the corresponding fire station, the list includes the first name, name, address, telephone number and a counter of the number of adults and children
	 * @Param Integer : stationNumber
	 * @return List Person
	 */
	@GetMapping("/firestation")
	public ResponseEntity<PersonByStationWithCountDTO> getPersonByFireStation(@RequestParam(value = "stationNumber")String stationNumber)   {
		logger.info("getPersonByFireStation, params: stationNumber={}", stationNumber);
		return new ResponseEntity<>(dTOService.getPersonByStation(stationNumber), HttpStatus.OK);
	}

	/**
	 * Get a list of telephone numbers of residents served by the fire station
	 * @Param Integer : stationNumber
	 * @return List phone number
	 */
	@GetMapping("/phoneAlert")
	public ResponseEntity<List<String>> getPhoneByStation(@RequestParam(value = "stationNumber")String stationNumber){
		logger.info("getPhoneByStation, params: stationNumber={}", stationNumber);
		return new ResponseEntity<>(dTOService.getPhoneByStation(stationNumber),HttpStatus.OK);
	}

	/**
	 * Get a list of residents living at the given address and the number of the fire station serving it
	 * @Param String : address
	 * @return List Person
	 */
	@GetMapping("/fire")
	public ResponseEntity<PersonByAdressWithFireStationListDTO> getFireByAddress(@RequestParam(value = "address")String address){
		logger.info("getFireByAddress, params: address={}", address);
		return new ResponseEntity<>(dTOService.getFireByAddress(address),HttpStatus.OK);
	}

	/**
	 * Get a list of households served by the barracks, there must be the nim, telephone number, age and medical history
	 * @Param Integer : stationNumber
	 * @return List Person
	 */
	@GetMapping("/flood/stations")
	public ResponseEntity<AddressByStationDTO> getHouseholdByStation(@RequestParam(value = "stations")String stationNumber){
		logger.info("getHouseholdByStation, params: stationNumber={}", stationNumber);
		return new ResponseEntity<>(dTOService.getHouseholdByStation(stationNumber),HttpStatus.OK);
	}

	/**
	 * Get the name, address, age, email address and medical history if several people have the same name, they appear
	 * @Param String : lastName
	 * @Param String : firstName
	 * @return List Person
	 */
	@GetMapping("/personInfo")
	public ResponseEntity<List<PersonByFirstNameAndLastNameDTO>> getPersondByStation(@RequestParam(value = "firstName") String firstName,@RequestParam(value = "lastName") String lastName){
		logger.info("getPersondByStation, params: firstname={},  lastname={}", firstName, lastName);
		return new ResponseEntity<>(dTOService.getPersonByStation(firstName,lastName),HttpStatus.OK);
	}

	/**
	 * Get email addresses of all the inhabitants of the city
	 * @Param city
	 * @return List String email
	 */
	@GetMapping("/communityEmail")
	public ResponseEntity<List<String>> getEmailByCity(@RequestParam(value = "city")String city){
		logger.info("getEmailByCity, params: city={}", city);
		return new ResponseEntity<>(dTOService.getEmailByCity(city),HttpStatus.OK);
	}

	/**
	 * Get a list of children living at the address, this list must have first name, last name, age and a list of other household members, if there is no child, returns an empty string
	 * @Param String : city
	 * @return List String child
	 */
	@GetMapping("/childAlert")
	public ResponseEntity<ListPersonByAddressDTO> getChildByAddress (@RequestParam(value = "address")String address) {
		logger.info("getChildByAddress, params: address={}", address);
		return new ResponseEntity<>(dTOService.getChildByAddress(address),HttpStatus.OK);
	}

}
