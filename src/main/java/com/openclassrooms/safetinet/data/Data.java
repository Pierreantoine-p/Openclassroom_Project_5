package com.openclassrooms.safetinet.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;

public class Data {
	
	 /*
	public static DataPerson dataPerson = new DataPerson();
	public static DataFireStation dataFirestation = new DataFireStation();
	public static DataMedicalRecord dataMedicalRecord = new DataMedicalRecord();
*/
	
    private static final Logger logger = LogManager.getLogger(Data.class);

	
	private static List<Person> persons = new ArrayList<Person>();
	private static List<MedicalRecords> medicalrecords = new ArrayList<MedicalRecords>();
	private static List<FireStations> firestations = new ArrayList<FireStations>();
	
	
	
	//PERSON
	
	
	
	/**
	 * GET ALL PERSONS
	 * @return
	 */
	public static List<Person> getAllPersons()throws IOException {
		try {
			logger.info("machin");
			return persons;
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
 	}
	

	
	
	/**
	 * POST PERSON
	 * @return
	 */
	public void setPersons(List<Person> persons)throws IOException {
		
		Data.persons = persons;
	}
	
	/**
	 * GET ONE PERSON
	 * @return
	 */
	public static Optional<Person> getPersonByName(String firstname,String lastname)throws IOException {
		
		try {
			return persons.stream()
					.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
					.findFirst();
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
		 
	}

	/**
	 * DELETE PERSON
	 * @param person
	 * @return
	 */
	public static boolean deletePerson(Person person)throws IOException {
		try {
			logger.info("machin");
			return persons.remove(person);

		}catch(Exception e) {
			logger.error("Error : " + e);
		   	throw new IOException();
		}
 	}
	
	/**
	 * UPDATE PERSON
	 * @param person
	 * @return
	 */
	 public static Optional<Person> updatePerson (Person person)throws IOException {
		 try {
				logger.info("machin");
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
			}catch(Exception e) {
				logger.error("Error : " + e);
			   	throw new IOException();
			}
		 
		  
	 }
	 
	 
	 
	 //FIRE STATION
	
		/**
		 * GET ALL FIRE STATION
		 * @return
		 */
		public static List<FireStations> getFireStations()throws IOException {
			try {
				logger.info("machin");
				return firestations;

			}catch(Exception e) {
				logger.error("Error : " + e);
			   	throw new IOException();
			}
 		}

		public void setFirestations(List<FireStations> firestations)throws IOException {
			
			Data.firestations = firestations;
		}
		
		/**
		 * GET ONE FIRE STATION
		 * @return
		 */
		public static Optional<FireStations> getStationByAdress(String address )throws IOException {
			try {
				logger.info("machin");
				return firestations.stream()
						.filter(p -> p.getAddress().equals(address))
						.findFirst();
			}catch(Exception e) {
				logger.error("Error : " + e);
			   	throw new IOException();
			}
			 
		}
		
		public static List<FireStations> getStationByNumber(String stationNumber )throws IOException {
			try {
				logger.info("machin");
				return firestations.stream()
						.filter(station -> station.getStation().equals(stationNumber))
						.collect(Collectors.toList());
			}catch(Exception e) {
				logger.error("Error : " + e);
			   	throw new IOException();
			}
			 
		}
		
		
		/**
		 * DELETE FIRE STATION
		 * @param person
		 * @return
		 */
		public static boolean deleteFireStation(FireStations fireStation)throws IOException {
			try {
				logger.info("machin");
				return firestations.remove(fireStation);

			}catch(Exception e) {
				logger.error("Error : " + e);
			   	throw new IOException();
			}
 		}
		
		/**
		 * UPDATE FIRE STATION
		 * @param person
		 * @return
		 */
		 public static Optional<FireStations> updateFireStation (FireStations fireStation)throws IOException {
			 try {
					logger.info("machin");
					 return firestations.stream()
							  .filter(p -> p.getAddress().equals(fireStation.getAddress()))
							  .findFirst()
							  .map(p ->{
								 	p.setStation(fireStation.getStation());
									return p;
							 });
				}catch(Exception e) {
					logger.error("Error : " + e);
				   	throw new IOException();
				}
		 
			  
		 }
		 
	
		 
		 //MEDICAL RECORD
		 
		 /**
			 * GET ALL MEDICAL RECORD
			 * @return
			 */
		 public static List<MedicalRecords> getMedicalRecords()throws IOException {
			 try {
					logger.info("machin");
					return medicalrecords;

				}catch(Exception e) {
					logger.error("Error : " + e);
				   	throw new IOException();
				}
 			}


		 public void setMedicalrecords(List<MedicalRecords> medicalrecords){
				Data.medicalrecords = medicalrecords;
			}
		 
			/**
			 * GET ONE MEDICAL RECORD
			 * @return
			 */
			public static Optional<MedicalRecords> getMedicalRecordByName(String firstname, String lastname )throws IOException {
				try {
					logger.info("machin");
					return medicalrecords.stream()
							.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
							.findFirst();
				}catch(Exception e) {
					logger.error("Error : " + e);
				   	throw new IOException();
				}
				 
			}
			
			/**
			 * DELETE MEDICAL RECORD
			 * @param person
			 * @return
			 */
			public static boolean deleteMedicalRecords(MedicalRecords medicalrecord)throws IOException {
				try {
					logger.info("machin");
					return medicalrecords.remove(medicalrecord);

				}catch(Exception e) {
					logger.error("Error : " + e);
				   	throw new IOException();
				}
 			}
			
			/**
			 * UPDATE MEDICAL RECORD
			 * @param person
			 * @return
			 */
			 public static Optional<MedicalRecords> updateMedicalRecords (MedicalRecords medicalrecord)throws IOException {
				 try {
						logger.info("machin");
						 return medicalrecords.stream()
								 .filter(p -> p.getFirstName().equals(medicalrecord.getFirstName()) &&
								            p.getLastName().equals(medicalrecord.getLastName()))
								  .findFirst()
								  .map(p ->{
										p.setMedications(medicalrecord.getMedications());
										p.setAllergies(medicalrecord.getAllergies());
										return p;
								 });
					}catch(Exception e) {
						logger.error("Error : " + e);
					   	throw new IOException();
					}
				 
				  
			 }			 
 
			 
}
