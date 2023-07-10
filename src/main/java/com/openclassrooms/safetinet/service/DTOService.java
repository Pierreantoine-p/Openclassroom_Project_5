package com.openclassrooms.safetinet.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
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
import com.openclassrooms.safetinet.model.DTO.ChildByAddressDTO;
import com.openclassrooms.safetinet.model.DTO.EmailBycity;
import com.openclassrooms.safetinet.model.DTO.HouseholdByStationDTO;
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


	// A REVOIR
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

		//List<PhoneByFireStationDTO> phoneByFireStationDTOlist = new ArrayList();
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
/*
					PhoneByFireStationDTO phoneByFireStationDTO = new PhoneByFireStationDTO();
phoneList
					phoneByFireStationDTO.setPhone(phoneNumbers);
					phoneByFireStationDTOlist.add(phoneByFireStationDTO);
					System.out.println("phoneByFireStationDTOlist"  + phoneByFireStationDTOlist);
					*/	
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
		/**
		 * Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
			de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
			médicaux (médicaments, posologie et allergies) de chaque personne
		 */
	}


	// A REVOIR
	public List<ChildByAddressDTO> getChildByAddress(String address){
		try {

			List<ChildByAddressDTO> childByStationDTOList = new ArrayList();

			List<Person> personsList = personsService.getPersonByAddress(address);
			String json = objectMapper.writeValueAsString(personsList);
			System.out.println("personsList :" + json);

			if(personsList.isEmpty()) {
				return Collections.emptyList();
			}else {					

				for(Person person : personsList) {

					String personJson = objectMapper.writeValueAsString(person);
					System.out.println("person :" + personJson);

					String firstname = person.getFirstName();
					String lastname = person.getLastName();
					System.out.println("firstname : " + firstname);
					System.out.println("lastname : " + lastname);


					Optional<MedicalRecords> datebirths = medicalRecordsService.getMedicalByName(firstname, lastname);	

					String jsonDate = objectMapper.writeValueAsString(datebirths);
					System.out.println("datebirth : " + jsonDate);

					System.out.println("date : " + datebirths.get().getBirthdate());
					MedicalRecords datebirth = datebirths.get();

					LocalDate now = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDate formatterbirthDate = LocalDate.parse(datebirth.getBirthdate(), formatter);
					Period period = Period.between(formatterbirthDate, now);

					int age = period.getYears();
					System.out.println("age : " + age);

					if(age <= 18) {
						List<Person> listAllPerson = personsService.getPersonByAddress(person.getAddress());
						for(Person allPerson : listAllPerson) {
							ChildByAddressDTO childByAddressDTO = new ChildByAddressDTO();

							childByAddressDTO.setFirstName(allPerson.getFirstName());
							childByAddressDTO.setLastName(allPerson.getLastName());

							childByStationDTOList.add(childByAddressDTO);
							System.out.println("childByStationDTOList :" + childByStationDTOList);
						}

					}else {
						System.out.println("Not 18" );

					}


					/*
						if(!datebirth.isEmpty()) {
							LocalDate now = LocalDate.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							LocalDate formatterbirthDate = LocalDate.parse(datebirth.get(0).getBirthdate(), formatter);
							Period period = Period.between(formatterbirthDate, now);

							int age = period.getYears();
							System.out.println("age : " + age);

							if(age <= 18) {

								ChildByAddressDTO childByAddressDTO = new ChildByAddressDTO();

								List<Person> listAllPerson = personsService.getPersonByAddress(address);
									for(Person allPerson : listAllPerson) {


										LocalDate now = LocalDate.now();
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
										LocalDate formatterbirthDate = LocalDate.parse(datebirth.get(0).getBirthdate(), formatter);
										Period period = Period.between(formatterbirthDate, now);

										int age = period.getYears();


										String personAge = String.valueOf(age);

										childByAddressDTO.setFirstName(allPerson.getFirstName());
										childByAddressDTO.setLastName(allPerson.getLastName());
										childByAddressDTO.setAge(personAge);

										childByStationDTOList.add(childByAddressDTO);
										System.out.println("childByStationDTOList :" + childByStationDTOList);
									}

							}return Collections.emptyList();

						}return Collections.emptyList();

					 */
					/*
						//Optional <MedicalRecords> datebirth = medicalRecordsService.getMedicalByName(firstname, lastname);
						if(datebirth.isPresent()) {

							//String json = objectMapper.writeValueAsString(datebirth);
							System.out.println("datebirth" + datebirth);
							System.out.println("date" + datebirth.get().getBirthdate());


							LocalDate now = LocalDate.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							LocalDate formatterbirthDate = LocalDate.parse(datebirth.get().getBirthdate(), formatter);
							Period period = Period.between(formatterbirthDate, now);

							int age = period.getYears();

							if(age <= 18) {
								ChildByAddressDTO childByAddressDTO = new ChildByAddressDTO();


						        String personAge = String.valueOf(age);

								childByAddressDTO.setFirstName(person.getFirstName());
								childByAddressDTO.setLastName(person.getLastName());
								childByAddressDTO.setAge(personAge);

								childByStationDTOList.add(childByAddressDTO);
								System.out.println("childByStationDTOList :" + childByStationDTOList);

							}else {
								return Collections.emptyList();

							}
						}else {
							return Collections.emptyList();

						}
					 */
				}

				return childByStationDTOList;
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}
	}





	// A REVOIR
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

	// A REVOIR
	public List<PersonByFirstNameAndLastNameDTO>getPersondByStation(String firstName, String lastName){
		try {	
			List<Person> allPerson = personsService.findByName(firstName, lastName);
			for (Person person : allPerson) {

			}
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

	//OK
	public List<String>getEmailByCity(String city){
		//List<EmailBycity> emailByCity = new ArrayList();
		List<String> emailList = new ArrayList();

		try {
			List<Person> personMails = personsService.findEmailByCity(city);	

			for(Person personMail : personMails ) {

				String emailPerson = personMail.getEmail();

				if(!emailList.contains(emailPerson)) {
					emailList.add(emailPerson) ;
				}	
			}

			//emailByCityDTO.setEmail(emailList);


			return emailList;
		}catch(Exception e) {
			logger.error("Error : " + e);
			return new ArrayList<>();
		}

	}


}
