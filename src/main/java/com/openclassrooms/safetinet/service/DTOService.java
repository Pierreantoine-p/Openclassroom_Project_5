package com.openclassrooms.safetinet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.model.*;
import com.openclassrooms.safetinet.model.DTO.*;
import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
@Component
public class DTOService {

	private PersonsService personsService;

	public PersonsRepository personsRepository;

	private MedicalRecordsService medicalRecordsService;

	private FireStationsService fireStationsService;
	
	private AgeService ageService;

	ObjectMapper objectMapper = new ObjectMapper();


	public DTOService(PersonsRepository personsRepository, PersonsService personsService,FireStationsService fireStationsService, MedicalRecordsService medicalRecordsService, AgeService ageService) {
		this.personsRepository = personsRepository;
		this.personsService = personsService;
		this.fireStationsService = fireStationsService;
		this.medicalRecordsService = medicalRecordsService ;
		this.ageService = ageService;
	}


	/**
	 * Get a list of people covered by the corresponding fire station, the list includes the first name, name, address, telephone number and a counter of the number of adults and children
	 * @Param Integer : stationNumber
	 * @return List Person
	 */
	public PersonByStationWithCountDTO getPersonByStation(String stationNumber){
		PersonByStationWithCountDTO personByStationDTOWithCountList = new PersonByStationWithCountDTO();
		List<PersonByStationDTO> personByStationDTOList = new ArrayList<PersonByStationDTO>();
		int countAdult = 0;
		int countChild = 0;
		List<FireStations> fireStationsList = fireStationsService.getStationByNumber(stationNumber);

		if(fireStationsList.isEmpty()) {
			return  personByStationDTOWithCountList;
		}

		for (FireStations fireStationList : fireStationsList){						
			String address = fireStationList.getAddress();
			List<Person> persons = personsService.getPersonByAddress(address);

			for (Person person : persons) {
				String firstname = person.getFirstName();
				String lastname = person.getLastName();
				List<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(firstname, lastname);	

				if(!medicalRecords.isEmpty()) {
					int age = ageService.getAge(medicalRecords.get(0).getBirthdate());
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
	}
	
	/**
	 * Get a list of telephone numbers of residents served by the fire station
	 * @Param Integer : stationNumber
	 * @return List phone number
	 */
	public List<String> getPhoneByStation(String stationNumber){
		List<String> phoneList = new ArrayList<String>();
		List<FireStations> fireStationsList = fireStationsService.getStationByNumber(stationNumber);
		System.out.println("Nom de zeus " + fireStationsList);

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
	}
	
	/**
	 * Get a list of residents living at the given address and the number of the fire station serving it
	 * @Param String : address
	 * @return List Person
	 */
	public PersonByAdressWithFireStationListDTO getFireByAddress(String address){
		PersonByAdressWithFireStationListDTO personByAdressWithFireStationListDTO = new PersonByAdressWithFireStationListDTO();
		List<PersonByAdressWithFireStationDTO> personByAdressWithFireStationDTOList = new ArrayList<PersonByAdressWithFireStationDTO>();
		List<Person> personsList = personsService.getPersonByAddress(address);

		for (Person person : personsList) {
			String firstname = person.getFirstName();
			String lastname = person.getLastName();
			List<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(firstname, lastname);		

			if(!medicalRecords.isEmpty()) {
				PersonByAdressWithFireStationDTO personByAdressWithFireStationDTO = new PersonByAdressWithFireStationDTO();
				int age = ageService.getAge(medicalRecords.get(0).getBirthdate());
				List <FireStations> fireStationList = fireStationsService.findByAdress(address);
				personByAdressWithFireStationListDTO.setFireStation(fireStationList.get(0).getStation());
				personByAdressWithFireStationDTO.setLastName(person.getLastName());
				personByAdressWithFireStationDTO.setPhone(person.getPhone());
				personByAdressWithFireStationDTO.setAge(age);
				personByAdressWithFireStationDTO.setAllergies(medicalRecords.get(0).getAllergies());
				personByAdressWithFireStationDTO.setMedications(medicalRecords.get(0).getMedications());
				personByAdressWithFireStationDTOList.add(personByAdressWithFireStationDTO);
				personByAdressWithFireStationListDTO.setPersonByAdressWithFireStationDTO(personByAdressWithFireStationDTOList);
			}else {
				return new PersonByAdressWithFireStationListDTO();
			}
		}
		return personByAdressWithFireStationListDTO;
	}
	
	/**
	 * Get a list of children living at the address, this list must have first name, last name, age and a list of other household members, if there is no child, returns an empty string
	 * @Param String : city
	 * @return List String child
	 */
	public ListPersonByAddressDTO getChildByAddress(String address){
		ListPersonByAddressDTO ListPersonByAddressDTO = new ListPersonByAddressDTO();
		List<Person> adressList = personsService.getPersonByAddress(address);
		List<ChildByAddressDTO> adultList = new ArrayList<ChildByAddressDTO>();
		List<ChildByAddressDTO> childList = new ArrayList<ChildByAddressDTO>();

		if(adressList.isEmpty()) {
			return  ListPersonByAddressDTO;
		}else {					

			for(Person person : adressList) {
				String firstname = person.getFirstName();
				String lastname = person.getLastName();
				List<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(firstname, lastname);	
				int age = ageService.getAge(medicalRecords.get(0).getBirthdate());
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
	
	/**
	 * Get a list of households served by the barracks, there must be the nim, telephone number, age and medical history
	 * @Param Integer : stationNumber
	 * @return List Person
	 */
	public AddressByStationDTO getHouseholdByStation(String stationNumber){
		AddressByStationDTO addressByStationDTO = new AddressByStationDTO();
		List<AddressPersonByStationDTO> addressPersonByStationListDTO = new ArrayList<AddressPersonByStationDTO>();
		List<HouseholdByStationDTO> householdByStationDTOList = new ArrayList<HouseholdByStationDTO>();
		List<FireStations> fireStationsList = fireStationsService.getStationByNumber(stationNumber);
		addressByStationDTO.setStation(stationNumber);

		if(fireStationsList.isEmpty()) {
			return  null;
		}

		for (FireStations fireStationList : fireStationsList){
			AddressPersonByStationDTO listAddressPersonByStationDTO = new AddressPersonByStationDTO();
			listAddressPersonByStationDTO.setAddress(fireStationList.getAddress());
			List<Person> personList = personsService.getPersonByAddress(fireStationList.getAddress());

			for (Person person : personList) {
				List<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(person.getFirstName(), person.getLastName());	
				int age = ageService.getAge(medicalRecords.get(0).getBirthdate());
				HouseholdByStationDTO householdByStationDTO = new HouseholdByStationDTO();
				householdByStationDTO.setFirstname(person.getFirstName());
				householdByStationDTO.setLastname(person.getLastName());
				householdByStationDTO.setPhone(person.getPhone());
				householdByStationDTO.setAge(age);
				householdByStationDTO.setMedications(medicalRecords.get(0).getMedications());
				householdByStationDTO.setAllergies(medicalRecords.get(0).getAllergies());
				householdByStationDTOList.add(householdByStationDTO);

				listAddressPersonByStationDTO.setHouseholdByStationDTO(householdByStationDTOList);
			}

			addressPersonByStationListDTO.add(listAddressPersonByStationDTO);
			addressByStationDTO.setAddressPersonByStationDTO(addressPersonByStationListDTO);
			//String addressByStationD= objectMapper.writeValueAsString(addressByStationDTO);
		}
		return addressByStationDTO;
	}
	
	/**
	 * Get the name, address, age, email address and medical history if several people have the same name, they appear
	 * @Param String : lastName
	 * @Param String : firstName
	 * @return List Person
	 */
	public List<PersonByFirstNameAndLastNameDTO> getPersonByStation(String firstname, String lastname){
		List<PersonByFirstNameAndLastNameDTO> personByFirstNameAndLastNameDTO = new ArrayList<PersonByFirstNameAndLastNameDTO>();	
		List<Person> allPerson = personsRepository.getByName(firstname, lastname);

		for (Person person : allPerson) {
			PersonByFirstNameAndLastNameDTO personByFirstNameAndLastNameListDTO = new PersonByFirstNameAndLastNameDTO();
			List<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalByName(person.getFirstName(), person.getLastName());
			int age = ageService.getAge(medicalRecords.get(0).getBirthdate());
			personByFirstNameAndLastNameListDTO.setLastName(person.getLastName());
			personByFirstNameAndLastNameListDTO.setAddress(person.getAddress());
			personByFirstNameAndLastNameListDTO.setEmail(person.getEmail());
			personByFirstNameAndLastNameListDTO.setAge(age);
			personByFirstNameAndLastNameListDTO.setMedications(medicalRecords.get(0).getMedications());
			personByFirstNameAndLastNameListDTO.setAllergies(medicalRecords.get(0).getAllergies());
			personByFirstNameAndLastNameDTO.add(personByFirstNameAndLastNameListDTO);
		}

		return personByFirstNameAndLastNameDTO;
	}
	
	/**
	 * Get email addresses of all the inhabitants of the city
	 * @Param city
	 * @return List String email
	 */
	public List<String>getEmailByCity(String city){
		List<String> emailList = new ArrayList<String>();
		List<Person> personMails = personsService.findEmailByCity(city);	

		for(Person personMail : personMails ) {
			String emailPerson = personMail.getEmail();

			if(!emailList.contains(emailPerson)) {
				emailList.add(emailPerson) ;
			}	
		}
		return emailList;
	}

}
