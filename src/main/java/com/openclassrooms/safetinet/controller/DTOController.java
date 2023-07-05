package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.model.ChildByAddressDTO;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
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
   
    
	@GetMapping("/firestation")
	//public List<PersonByStationDTO> getPersonByFireStation(@RequestParam(value = "stationNumber",required = false)String stationNumber) throws IOException {

	public List<PersonByStationDTO> getPersonByFireStation(@RequestParam(value = "stationNumber")String stationNumber)   {
		/**
		 * initialier 2 count 
		 * get l'age
		 * si age +18 count +1
		 * si age -18 count +1
		 * concatenation 
		 */
		try {
			List<FireStations> fireStationsList = fireStationsService.findStationByNumber(stationNumber);
			
			if(fireStationsList.isEmpty()) {
				return Collections.emptyList();
			}else {
				List<String> listAdress = new ArrayList();
				List<PersonByStationDTO> personByStationDTOList = new ArrayList();
				
				for (FireStations fireStationList : fireStationsList){						
					String address = fireStationList.getAddress();
						List<Person> persons = personsService.getPersonByAddress(address);

						for (Person person : persons) {
							String firstname = person.getFirstName();
							String lastname = person.getLastName();
							
							Optional<MedicalRecords> datebirth = medicalRecordsService.getMedicalByName(firstname, lastname);
							//String datebirthStrings = objectMapper.writeValueAsString(datebirth);
							if(datebirth.isPresent()) {
								
									PersonByStationDTO personByStationDTO = new PersonByStationDTO();

									personByStationDTO.setFirstName(person.getFirstName());
									personByStationDTO.setLastName(person.getLastName());
									personByStationDTO.setAddress(person.getAddress());
									personByStationDTO.setPhone(person.getPhone());
									personByStationDTO.setBirthdate(datebirth.get().getBirthdate());
									
									personByStationDTOList.add(personByStationDTO);
							
							}else {
								return Collections.emptyList();
							}	
						}										
				}
				return personByStationDTOList;
			}
		}catch (Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<PersonByStationDTO>();
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
							
							System.out.println("datebirth.getBirthdate()" + datebirth.getBirthdate());
					        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							
					        LocalDate now = LocalDate.now();
							LocalDate formatterbirthDate = LocalDate.parse(datebirth.getBirthdate(), formatter);
							
					        Period period = Period.between(formatterbirthDate, now);
					        
					        int age = period.getYears();
					        
					        
							System.out.println("Nom de zeus 0");
							
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
	/**
	 * Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
		La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
		membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide
	 */
	
	/**
	 * on chercher une adress sur person et on récupérer firstname et lastname 
	 * on fait une recherche par prénom sur medical record on récupere la liste des birthdate 
	 * 
	 * 
	 * 
	 * ajouter les gens de cette adresse
	 * 
	 * 
	 * 
	 * on crée une liste 
	 * on implante cette liste si birthdate de la personne est en dessous de 18 ans on l'ajoute 
	 */
	
}
