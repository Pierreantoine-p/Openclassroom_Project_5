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
import com.openclassrooms.safetinet.model.DTO.EmailBycity;
import com.openclassrooms.safetinet.model.DTO.HouseholdByStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByAdressWithFireStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByFirstNameAndLastNameDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationWithCountDTO;
import com.openclassrooms.safetinet.model.DTO.PhoneByFireStationDTO;
import com.openclassrooms.safetinet.service.DTOService;
import com.openclassrooms.safetinet.service.FireStationsService;
import com.openclassrooms.safetinet.service.MedicalRecordsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
public class DTOController {

	private FireStationsService fireStationsService;
	private MedicalRecordsService medicalRecordsService;
	private PersonsService personsService;
	private DTOService dTOService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
    private static final Logger logger = LogManager.getLogger(DTOController.class);
    
    public DTOController(FireStationsService fireStationsService, PersonsService personsService, MedicalRecordsService medicalRecordsService, DTOService dTOService) {
		this.fireStationsService = fireStationsService;
		this.personsService = personsService;
		this.medicalRecordsService = medicalRecordsService;
		this.dTOService = dTOService;
    }
   
    
	@GetMapping("/firestation")
	public ResponseEntity<List<PersonByStationWithCountDTO>> getPersonByFireStation(@RequestParam(value = "stationNumber")String stationNumber)   {
		try {
			return new ResponseEntity<>(dTOService.getPersonByStation(stationNumber), HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/phoneAlert")
	public ResponseEntity<List<PhoneByFireStationDTO>> getPhoneByStation(@RequestParam(value = "stationNumber")String stationNumber){
		try {
			return new ResponseEntity<>(dTOService.getPhoneByStation(stationNumber),HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/fire")
	public ResponseEntity<List<PersonByAdressWithFireStationDTO>> getFireByAddress(@RequestParam(value = "address")String address){
		try {
			return new ResponseEntity<>(dTOService.getFireByAddress(address),HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/flood/stations")
	public ResponseEntity<List<HouseholdByStationDTO>> getHouseholdByStation(@RequestParam(value = "stationNumber")String stationNumber){
		try {
			return new ResponseEntity<>(dTOService.getHouseholdByStation(stationNumber),HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/personInfo")
	public ResponseEntity<List<PersonByFirstNameAndLastNameDTO>> getPersondByStation(@RequestParam(value = "firstName")String firstName, @RequestParam(value = "lastName")String lastName){
		try {
			return new ResponseEntity<>(dTOService.getPersondByStation(firstName,lastName),HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/communityEmail")
	public ResponseEntity<List<EmailBycity>> getEmailByCity(@RequestParam(value = "city")String city){
		try {
			return new ResponseEntity<>(dTOService.getEmailByCity(city),HttpStatus.OK);
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	/**
	 * Si !age -18  = "{}"
	 * si age +18 => recherche par nom et adresse et crée l'objet 
	 * 
	 */
/*	

	@GetMapping("/childAlert")
	public List<ChildByAddressDTO> getChildByAddress (@RequestParam(value = "address")String address)throws IOException{
		try {
			List<Person> personsList = personsService.getPersonByAddress(address);

			//System.out.println("personStrings" + personStrings);

				if(personsList.isEmpty()) {
					return Collections.emptyList();
				}else {
					//List<String> listPersons = new ArrayList();
					List<ChildByAddressDTO> childByStationDTOList = new ArrayList();
					
					
					for(Person person : personsList) {
						
						

						String firstname = person.getFirstName();
						String lastname = person.getLastName();
						
						Optional <MedicalRecords> datebirthList = medicalRecordsService.getMedicalByName(firstname, lastname);
						

						for(MedicalRecords datebirth : datebirthList) {
							
					        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							
					        LocalDate now = LocalDate.now();
							LocalDate formatterbirthDate = LocalDate.parse(datebirth.getBirthdate(), formatter);
							
					        Period period = Period.between(formatterbirthDate, now);
					        
					        int age = period.getYears();
					        
					        
							
							if(age <= 18) {
								ChildByAddressDTO childByAddressDTO = new ChildByAddressDTO();
								

						        String personAge = String.valueOf(age);

								childByAddressDTO.setFirstName(person.getFirstName());
								childByAddressDTO.setLastName(person.getLastName());
								childByAddressDTO.setAge(personAge);
								
								childByStationDTOList.add(childByAddressDTO);
							}
					        
							
							
						}
					}
					
					return childByStationDTOList;
				}
		}catch (Exception e) {
			logger.error("Error : " + e);
		    throw new IOException();
		}
	}
	*/
	/*
	 * Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
		La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
		membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide
	 */
	

	
}
