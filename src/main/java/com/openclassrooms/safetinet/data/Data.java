package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;

public class Data {
	
	 /*
	public static DataPerson dataPerson = new DataPerson();
	public static DataFireStation dataFirestation = new DataFireStation();
	public static DataMedicalRecord dataMedicalRecord = new DataMedicalRecord();
*/
	
	private static List<Person> persons = new ArrayList<Person>();
	private static List<MedicalRecords> medicalrecords = new ArrayList<MedicalRecords>();
	private static List<FireStations> firestations = new ArrayList<FireStations>();
	
	
	
	//PERSON
	
	
	
	/**
	 * GET ALL PERSONS
	 * @return
	 */
	public static List<Person> getAllPersons() {
		System.out.println("DATA" + persons);
		return persons;
	}
	
	/**
	 * POST PERSON
	 * @return
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	/**
	 * GET ONE PERSON
	 * @return
	 */
	public static Optional<Person> getPersonByName(String firstname,String lastname) {
		return persons.stream()
				.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
				.findFirst();
	}

	/**
	 * DELETE PERSON
	 * @param person
	 * @return
	 */
	public static boolean deletePerson(Person person) {
		return persons.remove(person);
	}
	
	/**
	 * UPDATE PERSON
	 * @param person
	 * @return
	 */
	 public static Optional<Person> updatePerson (Person person) {
		 return persons.stream()
		  .filter(p -> p.getFirstName().equals(person.getFirstName()) &&
		            p.getLastName().equals(person.getLastName()))
		  .findFirst()
		  .map(p ->{
			 	p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setZip(person.getZip());
				p.setPhone(person.getPhone());
				p.setEmail(person.getEmail()); 
				return p;
		 });
		  
	 }
	 
	 
	 
	 //FIRE STATION
	
		/**
		 * GET ALL FIRE STATION
		 * @return
		 */
		public static List<FireStations> getFireStations() {
			return firestations;
		}

		public void setFirestations(List<FireStations> firestations) {
			this.firestations = firestations;
		}
		
		/**
		 * GET ONE FIRE STATION
		 * @return
		 */
		public static Optional<FireStations> getStationByAdress(String address ) {
			return firestations.stream()
					.filter(p -> p.getAddress().equals(address))
					.findFirst();
		}
		
		/**
		 * DELETE FIRE STATION
		 * @param person
		 * @return
		 */
		public static boolean deleteFireStation(FireStations fireStation) {
			return firestations.remove(fireStation);
		}
		
		/**
		 * UPDATE FIRE STATION
		 * @param person
		 * @return
		 */
		 public static Optional<FireStations> updateFireStation (FireStations fireStation) {
			 return firestations.stream()
			  .filter(p -> p.getAddress().equals(fireStation.getAddress()))
			  .findFirst()
			  .map(p ->{
				 	p.setStation(fireStation.getStation());
					return p;
			 });
			  
		 }
		 
	
		 
		 //MEDICAL RECORD
		 
		 /**
			 * GET ALL MEDICAL RECORD
			 * @return
			 */
		 public static List<MedicalRecords> getMedicalRecords() {
				return medicalrecords;
			}


		 public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
				this.medicalrecords = medicalrecords;
			}
		 
			/**
			 * GET ONE MEDICAL RECORD
			 * @return
			 */
			public static Optional<MedicalRecords> getMedicalRecordByName(String firstname, String lastname ) {
				return medicalrecords.stream()
						.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
						.findFirst();
			}
			
			/**
			 * DELETE MEDICAL RECORD
			 * @param person
			 * @return
			 */
			public static boolean deleteMedicalRecords(MedicalRecords medicalrecord) {
				return medicalrecords.remove(medicalrecord);
			}
			
			/**
			 * UPDATE MEDICAL RECORD
			 * @param person
			 * @return
			 */
			 public static Optional<MedicalRecords> updateMedicalRecords (MedicalRecords medicalrecord) {
				 return medicalrecords.stream()
						 .filter(p -> p.getFirstName().equals(medicalrecord.getFirstName()) &&
						            p.getLastName().equals(medicalrecord.getLastName()))
						  .findFirst()
						  .map(p ->{
								p.setMedications(medicalrecord.getMedications());
								p.setAllergies(medicalrecord.getAllergies());
								return p;
						 });
				  
			 }			 
 
			 
}
