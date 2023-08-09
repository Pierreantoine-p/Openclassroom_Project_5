package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
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
	        return new ArrayList<>();
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * POST PERSON
	 * @return
	 */
	public void setPersons(List<Person> persons)  {
		this.persons = persons;
	}
	
	/**
	 * GET ONE PERSON
	 * @return
	 */
	public  List<Person> getPersonByName(String firstname,String lastname)  {
		
		try {
			return persons.stream()
					.filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
					.collect(Collectors.toList());
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
		 
	}

	/**
	 * DELETE PERSON
	 * @param person
	 * @return
	 */
	public boolean deletePerson(String firstname, String lastname) {
		try {
			return persons.removeIf(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname));
					
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
	 public boolean updatePerson (String firstname, String lastname,Person person)  {
		 try {
				 
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
			        return updatedPerson.isPresent();
			}catch(Exception e) {
				logger.error("Error : " + e);
				return false;		
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

		public void setFirestations(ArrayList<FireStations> firestations)  {
			this.firestations = firestations;
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
				List<FireStations> result = firestations.stream()
						.filter(station -> station.getStation().equals(stationNumber))
						.collect(Collectors.toList());
				System.out.println("data result" + result);
				
				logger.info("machin");
				return result;
				
				
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
					logger.info("datawrapper");
					return medicalrecords;

				}catch(Exception e) {
					logger.error("Error : " + e);
			        return new ArrayList<MedicalRecords>();
				}
 			}


		 public void setMedicalrecords(ArrayList<MedicalRecords> medicalrecords){
				this.medicalrecords = medicalrecords;
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
							 .filter(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname))
								.findFirst();
				 }catch(Exception e) {
						logger.error("Error : " + e);
				        return Optional.empty();
					}
			 }
			 
			 public List<Person>getEmailByCity(String city){
				 try {
					 return persons.stream()
							 .filter(c ->c.getCity().equals(city))
								.collect(Collectors.toList());
				 }catch(Exception e) {
						logger.error("Error : " + e);
				        return new ArrayList<>();
					}
			 }
			 
}
