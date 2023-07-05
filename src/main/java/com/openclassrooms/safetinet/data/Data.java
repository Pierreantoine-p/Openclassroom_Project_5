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
	
	
	
    private static final Logger logger = LogManager.getLogger(Data.class);

	
	private static List<Person> persons = new ArrayList<Person>();
	private static List<MedicalRecords> medicalrecords = new ArrayList<MedicalRecords>();
	private static List<FireStations> firestations = new ArrayList<FireStations>();
	
	
	
	//PERSON
	
	
	
	/**
	 * GET ALL PERSONS
	 * @return
	 */
	public  List<Person> getAllPersons()  {
		try {
			logger.info("machin");
			return persons;
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<Person>();

		}
 	}
	
	
	
	
	
	
	public  List<Person> getPersonByAddress(String address)   {
		try {
			return persons.stream()
					.filter(p -> p.getAddress().equals(address))
					.collect(Collectors.toList());
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<Person>();
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * POST PERSON
	 * @return
	 */
	public void setPersons(List<Person> persons)  {
		Data.persons = persons;
	}
	
	/**
	 * GET ONE PERSON
	 * @return
	 */
	public  Optional<Person> getPersonByName(String firstname,String lastname)  {
		
		try {
			return persons.stream()
					.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
					.findFirst();
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		 
	}

	/**
	 * DELETE PERSON
	 * @param person
	 * @return
	 */
	public  boolean deletePerson(Person person) {
		try {
			logger.info("machin");
			return persons.remove(person);

		}catch(Exception e) {
			logger.error("Error : " + e);
			return false;		
		}
 	}
	
	/**
	 * UPDATE PERSON
	 * @param person
	 * @return
	 */
	 public  Optional<Person> updatePerson (Person person)  {
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
		        return Optional.empty();
			}
		 
		  
	 }
	 
	 
	 
	 //FIRE STATION
	
		/**
		 * GET ALL FIRE STATION
		 * @return
		 */
		public List<FireStations> getFireStations()  {
			try {
				logger.info("machin");
				return firestations;

			}catch(Exception e) {
				logger.error("Error : " + e);
		        return new ArrayList<FireStations>();
			}
 		}

		public void setFirestations(List<FireStations> firestations)  {
			Data.firestations = firestations;
		}
		
		/**
		 * GET ONE FIRE STATION
		 * @return
		 */
		public  List<FireStations> getStationByAdress(String address )  {
			try {
				logger.info("machin");
				return firestations.stream()
						.filter(p -> p.getAddress().equals(address))
						.collect(Collectors.toList());
			}catch(Exception e) {
				logger.error("Error : " + e);
		        return new ArrayList<FireStations>();
			}
			 
		}
		
		public  List<FireStations> getStationByNumber(String stationNumber )  {
			try {
				logger.info("machin");
				return firestations.stream()
						.filter(station -> station.getStation().equals(stationNumber))
						.collect(Collectors.toList());
			}catch(Exception e) {
				logger.error("Error : " + e);
		        return new ArrayList<FireStations>();

			}
			 
		}
		
		
		/**
		 * DELETE FIRE STATION
		 * @param person
		 * @return
		 */
		public boolean deleteFireStation(FireStations fireStation)  {
			try {
				logger.info("machin");
				return firestations.remove(fireStation);

			}catch(Exception e) {
				logger.error("Error : " + e);
				return false;
			}
 		}
		
		/**
		 * UPDATE FIRE STATION
		 * @param person
		 * @return
		 */
		 public  Optional<FireStations> updateFireStation (FireStations fireStation)  {
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
			        return Optional.empty();
				}
		 
			  
		 }
		 
	
		 
		 //MEDICAL RECORD
		 
		 /**
			 * GET ALL MEDICAL RECORD
			 * @return
			 */
		 public List<MedicalRecords> getMedicalRecords()  {
			 try {
					logger.info("machin");
					return medicalrecords;

				}catch(Exception e) {
					logger.error("Error : " + e);
			        return new ArrayList<MedicalRecords>();
				}
 			}


		 public void setMedicalrecords(List<MedicalRecords> medicalrecords){
				Data.medicalrecords = medicalrecords;
			}
		 
			/**
			 * GET ONE MEDICAL RECORD
			 * @return
			 */
			public Optional<MedicalRecords> getMedicalRecordByName(String firstname, String lastname )  {
				try {
					logger.info("machin");
					return medicalrecords.stream()
							.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
							.findFirst();
				}catch(Exception e) {
					logger.error("Error : " + e);
			        return Optional.empty();
				}
				 
			}
			
			/**
			 * DELETE MEDICAL RECORD
			 * @param person
			 * @return
			 */
			public boolean deleteMedicalRecords(MedicalRecords medicalrecord)  {
				try {
					logger.info("machin");
					return medicalrecords.remove(medicalrecord);

				}catch(Exception e) {
					logger.error("Error : " + e);
					return false;
				}
 			}
			
			/**
			 * UPDATE MEDICAL RECORD
			 * @param person
			 * @return
			 */
			 public Optional<MedicalRecords> updateMedicalRecords (MedicalRecords medicalrecord)  {
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
				        return Optional.empty();
					}
				 
			 }			 
 
			 public Optional<MedicalRecords> getMedicalByName(String firstname, String lastname)  {
				 try {
					 return medicalrecords.stream()
							 .filter(m -> m.getFirstName().equals(firstname) && m.getLastName().equals(lastname))
								.findFirst();
				 }catch(Exception e) {
						logger.error("Error : " + e);
				        return Optional.empty();
					}
			 }
			 
}
