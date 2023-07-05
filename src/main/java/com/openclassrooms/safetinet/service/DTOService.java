package com.openclassrooms.safetinet.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.model.DTO.EmailBycity;
import com.openclassrooms.safetinet.model.DTO.HouseholdByStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByAdressWithFireStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByFirstNameAndLastNameDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationWithCountDTO;
import com.openclassrooms.safetinet.model.DTO.PhoneByFireStationDTO;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
@Component
public class DTOService {
	
	private PersonsService personsService;
	
    public PersonsRepository personsRepository;

	private MedicalRecordsService medicalRecordsService;

	private FireStationsService fireStationsService;

    private static final Logger logger = LogManager.getLogger(DTOService.class);
    
    ObjectMapper objectMapper = new ObjectMapper();


	public DTOService(PersonsRepository personsRepository, PersonsService personsService,FireStationsService fireStationsService, MedicalRecordsService medicalRecordsService) {
		this.personsRepository = personsRepository;
		this.personsService = personsService;
		this.fireStationsService = fireStationsService;
		this.medicalRecordsService = medicalRecordsService ;
	}
	
	
	
	
	public List<PersonByStationWithCountDTO>getPersonByStation(String stationNumber){
		try {
		List<FireStations> fireStationsList = fireStationsService.findStationByNumber(stationNumber);
	
		if(fireStationsList.isEmpty()) {
			return Collections.emptyList();
		}else {
			
			List<PersonByStationWithCountDTO> personByStationDTOWithCountList = new ArrayList();
			List<PersonByStationDTO> personByStationDTOList = new ArrayList();
			
			for (FireStations fireStationList : fireStationsList){						
				String address = fireStationList.getAddress();
				
					List<Person> persons = personsService.getPersonByAddress(address);
	
					for (Person person : persons) {
						String firstname = person.getFirstName();
						String lastname = person.getLastName();
						
						Optional<MedicalRecords> datebirth = medicalRecordsService.getMedicalByName(firstname, lastname);					
						
						
						if(datebirth.isPresent()) {
							
					        LocalDate now = LocalDate.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							LocalDate formatterbirthDate = LocalDate.parse(datebirth.get().getBirthdate(), formatter);
							Period period = Period.between(formatterbirthDate, now);
						        
						    int age = period.getYears();
	
						    
						    PersonByStationDTO personByStationDTO = new PersonByStationDTO();
							PersonByStationWithCountDTO personByStationDTOWithCount = new PersonByStationWithCountDTO();
							
							
							personByStationDTO.setFirstName(person.getFirstName());
							personByStationDTO.setLastName(person.getLastName());
							personByStationDTO.setAddress(person.getAddress());
							personByStationDTO.setPhone(person.getPhone());

						

							
							if(age <= 18) {
								int countChild = +1;
								personByStationDTOWithCount.setCountChild(countChild) ;

								
							}else {
								int countAdult = +1;
								personByStationDTOWithCount.setCountAdult(countAdult) ;

								
	
							}
							personByStationDTOList.add(personByStationDTO);
							personByStationDTOWithCountList.add(personByStationDTOWithCount);
							System.out.println("personByStationDTOWithCountList" + personByStationDTOWithCountList);
							//PersonByStationWithCountDTO.add(personByStationDTOWithCount);
						}else {
							return Collections.emptyList();
						}	
					}										
			}
			return personByStationDTOWithCountList;
		}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
	}
	
	
	
	
	
	public List<PhoneByFireStationDTO>getPhoneByStation(String stationNumber){
		
		List<PhoneByFireStationDTO> phoneByFireStationDTOlist = new ArrayList();

		try {
			List<FireStations> fireStationsList = fireStationsService.findStationByNumber(stationNumber);
			if(fireStationsList.isEmpty()) {
				return Collections.emptyList();
			}else {
				for(FireStations fireStationList : fireStationsList) {
					String address = fireStationList.getAddress();
					
						List<Person> persons = personsService.getPersonByAddress(address);
						 for (Person person : persons) {
							 PhoneByFireStationDTO phoneByFireStationDTO = new PhoneByFireStationDTO();
								
								phoneByFireStationDTO.setPhone(person.getPhone());
								phoneByFireStationDTOlist.add(phoneByFireStationDTO);
						 }
				}
				return phoneByFireStationDTOlist;

			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
	
	}
	
	
	
	
	
	
	
	public List<PersonByAdressWithFireStationDTO>getFireByAddress(String address){
		
		List<PersonByAdressWithFireStationDTO> personByAdressWithFireStationDTOList = new ArrayList();

		try {
			List<Person> personsList = personsService.getPersonByAddress(address);
				for (Person person : personsList) {
					String firstname = person.getFirstName();
					String lastname = person.getLastName();
					
					Optional<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(firstname, lastname);					
					
					if(medicalRecords.isPresent()) {
						PersonByAdressWithFireStationDTO personByAdressWithFireStationDTO = new PersonByAdressWithFireStationDTO();
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						
				        LocalDate now = LocalDate.now();
						LocalDate formatterbirthDate = LocalDate.parse(medicalRecords.get().getBirthdate(), formatter);
						
				        Period period = Period.between(formatterbirthDate, now);
			        
				        int personAge = period.getYears();
				        String age = String.valueOf(personAge);
					
						System.out.println("age" + age);

					//for(MedicalRecords medicalRecord : medicalRecords) {
					
					List <FireStations> fireStationList = fireStationsService.findByAdress(address);
					
					for(FireStations fireStation : fireStationList) {
						
						//System.out.println("medicalRecordList" + medicalRecordList);
						
						//PersonByAdressWithFireStationDTO personByAdressWithFireStationDTO = new PersonByAdressWithFireStationDTO();

						
						personByAdressWithFireStationDTO.setLastName(person.getLastName());
						personByAdressWithFireStationDTO.setPhone(person.getPhone());
						personByAdressWithFireStationDTO.setAllergies(medicalRecords.get().getAllergies());
						personByAdressWithFireStationDTO.setMedications(medicalRecords.get().getMedications());
						personByAdressWithFireStationDTO.setAge(age);
						personByAdressWithFireStationDTO.setFireStation(fireStation.getStation());
						
						personByAdressWithFireStationDTOList.add(personByAdressWithFireStationDTO);
						System.out.println("AAAAAAAAAAA" + personByAdressWithFireStationDTOList);

					}
					
				}else {
					return Collections.emptyList();

				}
				}
				System.out.println("personByAdressWithFireStationDTOList" + personByAdressWithFireStationDTOList);

			return personByAdressWithFireStationDTOList;

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
		/**
		 * Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
			de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
			médicaux (médicaments, posologie et allergies) de chaque personne
		 */
	}
	
	
	
	
	
	
	
	
	
	public List<HouseholdByStationDTO>getHouseholdByStation(String stationNumber){
		try {
			return null;

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
		/**
		 * Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
			personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
			faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
		 */
	}
	
	public List<PersonByFirstNameAndLastNameDTO>getPersondByStation(String firstName, String lastName){
		try {			
			return null;
			
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
		/**
		 * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
			posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
			toutes apparaître.
		 */
	}
	
	public List<EmailBycity>getEmailByCity(String city){
		List<EmailBycity> emailByCity = new ArrayList();
		List<Person> email = new ArrayList();

		try {
			List<Person> personMails = personsService.findEmailByCity(city);
			
			for(Person personMail : personMails ) {
				if(!email.contains(personMail)) {
					// emailByCity.addAll(personMail.getEmail()) ;
					email.add(personMail) ;
					System.out.println("email" + email);
				}		
				EmailBycity emailByCityDTO = new EmailBycity();
				
				emailByCityDTO.setEmail(email.get() );
				emailByCity.add(emailByCityDTO);
			}
			
			
			return emailByCity;
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
		/**
		 * Cette url doit retourner les adresses mail de tous les habitants de la ville.
		 */
	}
	
	
}
