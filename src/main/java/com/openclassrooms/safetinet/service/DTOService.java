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
import com.openclassrooms.safetinet.model.*;
import com.openclassrooms.safetinet.model.DTO.*;
import com.openclassrooms.safetinet.service.*;
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

	public int getAge (String date) {

		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate formatterbirthDate = LocalDate.parse(date, formatter);
		Period period = Period.between(formatterbirthDate, now);

		int age = period.getYears();
		return age;
	}

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

						int age = getAge(medicalRecords.get().getBirthdate());

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

					int age = getAge(medicalRecords.get().getBirthdate());


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

				Optional<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(firstname, lastname);	


				int age = getAge(medicalRecords.get().getBirthdate());


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





	public AddressByStationDTO getHouseholdByStation(String stationNumber){
		AddressByStationDTO addressByStationDTO = new AddressByStationDTO();
		//AddressPersonByStationDTO addressPersonByStationDTO = new AddressPersonByStationDTO();

		List<AddressPersonByStationDTO> addressPersonByStationListDTO = new ArrayList();
		List<HouseholdByStationDTO> householdByStationDTOList = new ArrayList();

		try {
			List<FireStations> fireStationsList = fireStationsService.findStationByNumber(stationNumber);

			//ajouter le numero de station 
			addressByStationDTO.setStation(stationNumber);

			if(fireStationsList.isEmpty()) {
				return  null;
			}
			for (FireStations fireStationList : fireStationsList){


				AddressPersonByStationDTO listAddressPersonByStationDTO = new AddressPersonByStationDTO();
				String listAddressPersonByStationDT = objectMapper.writeValueAsString(listAddressPersonByStationDTO);
				System.out.println("listAddressPersonByStationDT" + listAddressPersonByStationDT);


				listAddressPersonByStationDTO.setAddress(fireStationList.getAddress());
				String listAddressPersonByStation = objectMapper.writeValueAsString(listAddressPersonByStationDTO);
				System.out.println("listAddressPersonByStationD" + listAddressPersonByStation);


				System.out.println("address" + fireStationList.getAddress());

				List<Person> personList = personsService.getPersonByAddress(fireStationList.getAddress());

				//String json = objectMapper.writeValueAsString(personList);
				//System.out.println("personList" + json);

				for (Person person : personList) {
					String personUnit = objectMapper.writeValueAsString(person);
					System.out.println("AAAAAAAAAAAAAAAAAA" + personUnit);

					Optional<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(person.getFirstName(), person.getLastName());	
					int age = getAge(medicalRecords.get().getBirthdate());


					HouseholdByStationDTO householdByStationDTO = new HouseholdByStationDTO();
					/**
					 * contruit la person
					 * add dans une liste
					 * ajoute dans le set person
					 * ajoute l'adresse
					 * add dans une liste
					 * ajouter le numero de station 
					 * ajouter dans l'objet final
					 * return
					 */

					// contruit la person

					householdByStationDTO.setFirstname(person.getFirstName());
					householdByStationDTO.setLastname(person.getLastName());
					householdByStationDTO.setPhone(person.getPhone());
					householdByStationDTO.setAge(age);
					householdByStationDTO.setMedications(medicalRecords.get().getMedications());
					householdByStationDTO.setAllergies(medicalRecords.get().getAllergies());

					String householdByStationDT = objectMapper.writeValueAsString(householdByStationDTO);
					System.out.println("householdByStationDT" + householdByStationDT);

					householdByStationDTOList.add(householdByStationDTO);

					//ajoute le set person
					listAddressPersonByStationDTO.setHouseholdByStationDTO(householdByStationDTOList);
					String listAddressPersonByStationD = objectMapper.writeValueAsString(listAddressPersonByStationDTO);
					System.out.println("listAddressPersonByStationD" + listAddressPersonByStationD);


				}



				//ajoute l'adress

				//add le tout dans une liste
				addressPersonByStationListDTO.add(listAddressPersonByStationDTO);
				String addressPersonByStationListD= objectMapper.writeValueAsString(addressPersonByStationListDTO);
				System.out.println("addressPersonByStationListD" + addressPersonByStationListD);

				// ajouter dans l'objet final
				addressByStationDTO.setAddressPersonByStationDTO(addressPersonByStationListDTO);
				String addressByStationD= objectMapper.writeValueAsString(addressByStationDTO);
				System.out.println("addressByStationD" + addressByStationD);



			}

			return addressByStationDTO;

		}catch(Exception e) {
			logger.error("Error : " + e);
			return new AddressByStationDTO();
		}


	}

	public List<PersonByFirstNameAndLastNameDTO> getPersondByStation(String firstname, String lastname){

		List<PersonByFirstNameAndLastNameDTO> personByFirstNameAndLastNameDTO = new ArrayList();
		try {	
			List<Person> allPerson = personsRepository.getByName(firstname, lastname);

			for (Person person : allPerson) {
				PersonByFirstNameAndLastNameDTO personByFirstNameAndLastNameListDTO = new PersonByFirstNameAndLastNameDTO();

				Optional<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(person.getFirstName(), person.getLastName());


				int age = getAge(medicalRecords.get().getBirthdate());


				personByFirstNameAndLastNameListDTO.setLastName(person.getLastName());
				personByFirstNameAndLastNameListDTO.setAddress(person.getAddress());
				personByFirstNameAndLastNameListDTO.setEmail(person.getEmail());
				personByFirstNameAndLastNameListDTO.setAge(age);
				personByFirstNameAndLastNameListDTO.setMedications(medicalRecords.get().getMedications());
				personByFirstNameAndLastNameListDTO.setAllergies(medicalRecords.get().getAllergies());

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
