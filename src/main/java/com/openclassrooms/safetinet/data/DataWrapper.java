package com.openclassrooms.safetinet.data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import lombok.Data;

@Data
public class DataWrapper {



	private static final Logger logger = LogManager.getLogger(DataWrapper.class);

	private List<MedicalRecords> medicalrecords;
	private List<Person> persons;
	private List<FireStations> firestations;




	/**
	 * GET ALL PERSONS
	 * @return
	 */
	public  List<Person> getAllPersons()  {
		List<Person> result =
				persons;
		logger.info("result: result={}", result );
		return result;
	}

	public List<Person> getPersonByAddress(String address)   {
		List<Person> result =
				persons.stream()
				.filter(p -> p.getAddress().equals(address))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	/**
	 * POST PERSON
	 * @return
	 */
	public void setPersons(List<Person> persons)  {
		logger.info("Person created: persons={}", persons );
		this.persons = persons;
	}

	/**
	 * GET ONE PERSON
	 * @return
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
	 * DELETE PERSON
	 * @param person
	 * @return
	 */
	public boolean deletePerson(String firstname, String lastname) {
		logger.info("Person deleted");
		return persons.removeIf(
				p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname));
	}

	/**
	 * UPDATE PERSON
	 * @param person
	 * @return
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



	//FIRE STATION

	/**
	 * GET ALL FIRE STATION
	 * @return
	 */
	public List<FireStations> getFireStations()  {
		List<FireStations> result =
				firestations;
		return result;
	}

	public void setFirestations(List<FireStations> firestations)  {
		logger.info("FireStation created");
		this.firestations = firestations;
	}

	/**
	 * GET ONE FIRE STATION
	 * @return
	 */
	public  List<FireStations> getStationByAdress(String address )  {
		List<FireStations> result = 
				firestations.stream()
				.filter(p -> p.getAddress().equals(address))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	public  List<FireStations> getStationByNumber(String stationNumber )  {
		List<FireStations> result = firestations.stream()
				.filter(station -> station.getStation().equals(stationNumber))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}


	/**
	 * DELETE FIRE STATION
	 * @param person
	 * @return
	 */
	public boolean deleteFireStation(String address)  {
		logger.info("FireStation deleted");
		return firestations.removeIf(p -> p.getAddress().equals(address));
	}



	/**
	 * UPDATE FIRE STATION
	 * @param person
	 * @return
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




	//MEDICAL RECORD

	/**
	 * GET ALL MEDICAL RECORD
	 * @return
	 */
	public List<MedicalRecords> getMedicalRecords()  {
		List<MedicalRecords> result = 
				medicalrecords;
		logger.info("result: result={}", result );
		return result;
	}


	public void setMedicalrecords(List<MedicalRecords> medicalrecords){
		logger.info("MedicalRecord created");
		this.medicalrecords = medicalrecords;
	}

	/**
	 * GET ONE MEDICAL RECORD
	 * @return
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
	 * DELETE MEDICAL RECORD
	 * @param person
	 * @return
	 */
	public boolean deleteMedicalRecords(String firstname, String lastname)  {
		logger.info("MedicalRecord deleted");
		return medicalrecords.removeIf(
				p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname));
	}



	/**
	 * UPDATE MEDICAL RECORD
	 * @param person
	 * @return
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


	public List<MedicalRecords> getMedicalByName(String firstname, String lastname)  {

		List<MedicalRecords> result =
				medicalrecords.stream()
				.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;
	}

	public List<Person>getEmailByCity(String city){
		List<Person> result =
				persons.stream()
				.filter(c ->c.getCity().equals(city))
				.collect(Collectors.toList());
		logger.info("result: result={}", result );
		return result;

	}



}
