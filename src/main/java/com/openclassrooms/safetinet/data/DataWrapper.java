package com.openclassrooms.safetinet.data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;


public class DataWrapper {

	private static final Logger logger = LogManager.getLogger(DataWrapper.class);

	private List<MedicalRecords> medicalrecords;
	private List<Person> persons;
	private List<FireStations> firestations;

	/**
	 *Get a list of all people
	 * @return List of all persons
	 */
	public  List<Person> getAllPersons()  {
		List<Person> result =
				persons;
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 *Get a list of person by address
	 *@Param String : address
	 * @return List of persons
	 */
	public List<Person> getPersonByAddress(String address)   {
		List<Person> result =
				persons.stream()
				.filter(p -> p.getAddress().equals(address))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 *Get a list of email sort by city
	 *@Param String : city
	 * @return List String : email
	 */
	public List<Person>getEmailByCity(String city){
		List<Person> result =
				persons.stream()
				.filter(c ->c.getCity().equals(city))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * Created new person
	 * @RequestBody Person
	 */
	public void setPersons(List<Person> persons)  {
		logger.info("Person created: persons={}", persons );
		this.persons = persons;
	}

	/**
	 *Get a list of person by firstName and lastName
	 *@Param String : firstName
	 *@Param String : lastName
	 * @return List of person
	 */
	public  List<Person> getPersonByName(String firstname,String lastname)  {
		List<Person> result =
				persons.stream()
				.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * Delete a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 */
	public boolean deletePerson(String firstname, String lastname) {
		logger.info("Person deleted");
		return persons.removeIf(
				p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname));
	}

	/**
	 * Update a person
	 * @Param String : firstName, 
	 * @Param String : lastName
	 * @return Person update 
	 */
	public boolean updatePerson (String firstname, String lastname,Person person)  {
		Optional<Person> updatedPerson =
				persons.stream()
				.filter(p -> p.getFirstName().equals(firstname) &&
						p.getLastName().equals(lastname))
				.findFirst()
				.map(p ->{
					p.setAddress(person.getAddress());
					p.setCity(person.getCity());
					p.setZip(person.getZip());
					p.setPhone(person.getPhone());
					p.setEmail(person.getEmail()); 
					return p;
				});
		logger.info("Person update");
		return updatedPerson.isPresent();
	}

	/**
	 * Get FireStations
	 * @return List of FireStations 
	 */
	public List<FireStations> getFireStations()  {
		List<FireStations> result =
				firestations;
		return result;
	}

	/**
	 * Created fireStations 
	 * @RequestBody fireStations
	 */
	public void setFirestations(List<FireStations> firestations)  {
		logger.info("FireStation created");
		this.firestations = firestations;
	}

	/**
	 * Get FireStations by  stationNumber 
	 * @Param String : stationNumber
	 * @return List of FireStations
	 */
	public  List<FireStations> getStationByAdress(String address )  {
		List<FireStations> result = 
				firestations.stream()
				.filter(p -> p.getAddress().equals(address))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * Get FireStations by  stationNumber 
	 * @Param String : stationNumber
	 * @return List of FireStations
	 */
	public  List<FireStations> getStationByNumber(String stationNumber )  {
		List<FireStations> result = firestations.stream()
				.filter(station -> station.getStation().equals(stationNumber))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * Delete FireStations
	 * @Param String : address
	 */
	public boolean deleteFireStation(String address)  {
		logger.info("FireStation deleted");
		return firestations.removeIf(p -> p.getAddress().equals(address));
	}

	/**
	 * Update FireStations
	 * @Param String : address
	 * @RequestBody fireStation
	 * @return FireStations update
	 */
	public boolean updateFireStation (String address, FireStations fireStation)  {
		Optional<FireStations> updated =
				firestations.stream()
				.filter(p -> p.getAddress().equals(fireStation.getAddress()))
				.findFirst()
				.map(p ->{
					p.setStation(fireStation.getStation());
					return p;
				});
		logger.info("FireStation update");
		return updated.isPresent();
	}

	/**
	 * Get medicalRecords
	 * @return List of MedicalRecords 
	 */
	public List<MedicalRecords> getMedicalRecords()  {
		List<MedicalRecords> result = 
				medicalrecords;
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * Created medicalRecords 
	 * @RequestBody medicalRecords
	 */
	public void setMedicalrecords(List<MedicalRecords> medicalrecords){
		logger.info("MedicalRecord created");
		this.medicalrecords = medicalrecords;
	}

	/**
	 * Get medicalRecords by  firstName and lastName
	 * @Param String : firstName
	 * @Param String : lastName
	 * @return List of MedicalRecord
	 */
	public List<MedicalRecords> getMedicalRecordByName(String firstname, String lastname )  {
		List<MedicalRecords> result =
				medicalrecords.stream()
				.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * Get medicalRecords by  firstName and lastName
	 * @Param String : firstName
	 * @Param String : lastName
	 * @return List of MedicalRecord
	 */
	public List<MedicalRecords> getMedicalByName(String firstname, String lastname)  {

		List<MedicalRecords> result =
				medicalrecords.stream()
				.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * Delete MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 */
	public boolean deleteMedicalRecords(String firstname, String lastname)  {
		logger.info("MedicalRecord deleted");
		return medicalrecords.removeIf(
				p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname));
	}

	/**
	 * Update MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 * @RequestBody medicalRecords
	 * @return MedicalRecord update
	 */
	public boolean updateMedicalRecords (String firstname, String lastname, MedicalRecords medicalrecord)  {
		Optional<MedicalRecords> updated =
				medicalrecords.stream()
				.filter(p -> p.getFirstName().equals(firstname) &&
						p.getLastName().equals(lastname))
				.findFirst()
				.map(p ->{
					p.setMedications(medicalrecord.getMedications());
					p.setAllergies(medicalrecord.getAllergies());
					return p;
				});
		logger.info("MedicalRecord update");
		return updated.isPresent();

	}

}
