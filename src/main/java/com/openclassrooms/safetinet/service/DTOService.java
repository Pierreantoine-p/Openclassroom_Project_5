package com.openclassrooms.safetinet.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.model.DTO.ChildByAddressDTO;
import com.openclassrooms.safetinet.model.DTO.EmailBycity;
import com.openclassrooms.safetinet.model.DTO.HouseholdByStationDTO;
import com.openclassrooms.safetinet.model.DTO.ListPersonByAddressDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByAdressWithFireStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByAdressWithFireStationListDTO;
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


	//OK
	public PersonByStationWithCountDTO getPersonByStation(String stationNumber){
		PersonByStationWithCountDTO personByStationDTOWithCountList = new PersonByStationWithCountDTO();
		List<PersonByStationDTO> personByStationDTOList = new ArrayList();
		int countAdult = 0;
		int countChild = 0;

		try {
			List<FireStations> fireStationsList = fireStationsService.findStationByNumber(stationNumber);
			if(fireStationsList.isEmpty()) {
				return  personByStationDTOWithCountList;
			}
			for (FireStations fireStationList : fireStationsList){						
				String address = fireStationList.getAddress();

				List<Person> persons = personsService.getPersonByAddress(address);

				for (Person person : persons) {
					String firstname = person.getFirstName();
					String lastname = person.getLastName();

					Optional<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(firstname, lastname);					


					if(!medicalRecords.isEmpty()) {

						LocalDate now = LocalDate.now();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						LocalDate formatterbirthDate = LocalDate.parse(medicalRecords.get().getBirthdate(), formatter);
						Period period = Period.between(formatterbirthDate, now);

						int age = period.getYears();
						PersonByStationDTO personByStationDTO = new PersonByStationDTO();

						personByStationDTO.setFirstName(person.getFirstName());
						personByStationDTO.setLastName(person.getLastName());
						personByStationDTO.setAddress(person.getAddress());
						personByStationDTO.setPhone(person.getPhone());

						if(age <= 18) {
							countChild ++;
						}else {
							countAdult ++;
						}
						personByStationDTOList.add(personByStationDTO);
					}else {
						return new PersonByStationWithCountDTO();
					}	
				}										
			}
			personByStationDTOWithCountList.setPersonByStationDTO(personByStationDTOList);
			personByStationDTOWithCountList.setCountChild(countChild) ;
			personByStationDTOWithCountList.setCountAdult(countAdult) ;

			return personByStationDTOWithCountList;

		}catch(Exception e) {
			logger.error("Error : " + e);
			return new PersonByStationWithCountDTO();
		}
	}

	//OK
	public List<String> getPhoneByStation(String stationNumber){

		List<String> phoneList = new ArrayList();
		try {
			List<FireStations> fireStationsList = fireStationsService.findStationByNumber(stationNumber);

			if(fireStationsList.isEmpty()) {
				return Collections.emptyList();
			}else {
				for(FireStations fireStationList : fireStationsList) {
					String address = fireStationList.getAddress();

					List<Person> persons = personsService.getPersonByAddress(address);
					String phoneNumbers = persons.get(0).getPhone();
					phoneList.add(phoneNumbers);

				}
				return phoneList;
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}
	}

	//OK
	public PersonByAdressWithFireStationListDTO getFireByAddress(String address){
		PersonByAdressWithFireStationListDTO personByAdressWithFireStationListDTO = new PersonByAdressWithFireStationListDTO();
		List<PersonByAdressWithFireStationDTO> personByAdressWithFireStationDTOList = new ArrayList();

		try {
			List<Person> personsList = personsService.getPersonByAddress(address);
			for (Person person : personsList) {
				String firstname = person.getFirstName();
				String lastname = person.getLastName();

				Optional<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(firstname, lastname);					

				if(!medicalRecords.isEmpty()) {
					PersonByAdressWithFireStationDTO personByAdressWithFireStationDTO = new PersonByAdressWithFireStationDTO();

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

					LocalDate now = LocalDate.now();
					LocalDate formatterbirthDate = LocalDate.parse(medicalRecords.get().getBirthdate(), formatter);

					Period period = Period.between(formatterbirthDate, now);

					int personAge = period.getYears();
					String age = String.valueOf(personAge);


					List <FireStations> fireStationList = fireStationsService.findByAdress(address);

					personByAdressWithFireStationListDTO.setFireStation(fireStationList.get(0).getStation());


					personByAdressWithFireStationDTO.setLastName(person.getLastName());
					personByAdressWithFireStationDTO.setPhone(person.getPhone());
					personByAdressWithFireStationDTO.setAge(age);
					personByAdressWithFireStationDTO.setAllergies(medicalRecords.get().getAllergies());
					personByAdressWithFireStationDTO.setMedications(medicalRecords.get().getMedications());

					personByAdressWithFireStationDTOList.add(personByAdressWithFireStationDTO);
					personByAdressWithFireStationListDTO.setPersonByAdressWithFireStationDTO(personByAdressWithFireStationDTOList);


				}else {
					return new PersonByAdressWithFireStationListDTO();
				}
			}

			return personByAdressWithFireStationListDTO;

		}catch(Exception e) {
			logger.error("Error : " + e);
			return new PersonByAdressWithFireStationListDTO();
		}

	}



	public ListPersonByAddressDTO getChildByAddress(String address){
		ListPersonByAddressDTO ListPersonByAddressDTO = new ListPersonByAddressDTO();

		List<ChildByAddressDTO> childByStationDTOList = new ArrayList();

		List<Person> adressList = personsService.getPersonByAddress(address);

		List<ChildByAddressDTO> adultList = new ArrayList();
		List<ChildByAddressDTO> childList = new ArrayList();

		if(adressList.isEmpty()) {
			return  ListPersonByAddressDTO;
		}else {					

			for(Person person : adressList) {


				String firstname = person.getFirstName();
				String lastname = person.getLastName();

				Optional<MedicalRecords> datebirths = medicalRecordsService.getMedicalByName(firstname, lastname);	

				LocalDate now = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDate formatterbirthDate = LocalDate.parse(datebirths.get().getBirthdate(), formatter);
				Period period = Period.between(formatterbirthDate, now);

				int age = period.getYears();
				ChildByAddressDTO childByAddressDTO = new ChildByAddressDTO();

				childByAddressDTO.setFirstName(person.getFirstName());
				childByAddressDTO.setLastName(person.getLastName());
				childByAddressDTO.setAge(age);

				if(age <= 18) {
					childList.add(childByAddressDTO);
				}if(age >= 18) {
					adultList.add(childByAddressDTO);
				}
			}
		}

		if(!childList.isEmpty()) {
			ListPersonByAddressDTO.setAdultByAddressDTO(adultList);
			ListPersonByAddressDTO.setChildByAddressDTO(childList);

		}
		return ListPersonByAddressDTO;
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

	public List<PersonByFirstNameAndLastNameDTO> getPersondByStation(String firstname, String lastname){

		List<PersonByFirstNameAndLastNameDTO> personByFirstNameAndLastNameDTO = new ArrayList();
		try {	
			List<Person> allPerson = personsRepository.findByName(firstname, lastname);

			for (Person person : allPerson) {
				PersonByFirstNameAndLastNameDTO personByFirstNameAndLastNameListDTO = new PersonByFirstNameAndLastNameDTO();

				Optional<MedicalRecords> medical = medicalRecordsService.getMedicalByName(person.getFirstName(), person.getLastName());

				LocalDate now = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDate formatterbirthDate = LocalDate.parse(medical.get().getBirthdate(), formatter);
				Period period = Period.between(formatterbirthDate, now);

				int age = period.getYears();


				personByFirstNameAndLastNameListDTO.setLastName(person.getLastName());
				personByFirstNameAndLastNameListDTO.setAddress(person.getAddress());
				personByFirstNameAndLastNameListDTO.setEmail(person.getEmail());
				personByFirstNameAndLastNameListDTO.setAge(age);
				personByFirstNameAndLastNameListDTO.setMedications(medical.get().getMedications());
				personByFirstNameAndLastNameListDTO.setAllergies(medical.get().getAllergies());

				personByFirstNameAndLastNameDTO.add(personByFirstNameAndLastNameListDTO);

			}

		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}
		return personByFirstNameAndLastNameDTO;
	}

	public List<String>getEmailByCity(String city){
		List<String> emailList = new ArrayList();

		try {
			List<Person> personMails = personsService.findEmailByCity(city);	

			for(Person personMail : personMails ) {

				String emailPerson = personMail.getEmail();

				if(!emailList.contains(emailPerson)) {
					emailList.add(emailPerson) ;
				}	
			}

			return emailList;
		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}

	}


}
