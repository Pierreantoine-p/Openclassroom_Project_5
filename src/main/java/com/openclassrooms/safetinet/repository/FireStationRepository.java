package com.openclassrooms.safetinet.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.Person;


@Repository
public class FireStationRepository {

    private final List<FireStations> fireStations = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(FireStationRepository.class);
    
	Data data = new Data();

    
    /**
	 * GET ALL
	 *  Get all fireStations
	 *  @return
	 */
	public List<FireStations> getAll()  {
		try {
			return data.getFireStations();

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<FireStations>();
		}
	}
	

	/**
	 * POST
	 *  ajout d'un mapping caserne/adresse ;
	 *  @param Person person
	 */
	public boolean save(FireStations fireStation) {
		try {
			return data.getFireStations().add(fireStation);
		}catch(Exception e) {
			logger.error("Error : " + e);
			return false;
		}
		
	}
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public Optional<FireStations> findByAdress(String address)   {
		try {
			return data.getStationByAdress(address);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		
	}
	
	
	
	public List<FireStations> findByNumber(String stationNumber)  {
		try {
			return data.getStationByNumber(stationNumber);

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<FireStations>();
		}
	}
	
	
	/**
	 * DELETE
	 * supprimer le mapping d'une caserne ou d'une adresse
	 * @param String firstname
	 * @return
	 */
	public boolean delete(FireStations fireStation)   {
		try {
			return data.deleteFireStation(fireStation);
		}catch(Exception e) {
			logger.error("Error : " + e);
			return false;
		}
		
	}
	
	
	/**
	 * PUT
	 * mettre à jour le numéro de la caserne de pompiers d'une adresse ;
	 * @param Person person
	 * @return
	 */	
	public Optional<FireStations> update(FireStations fireStation)   {
		try {
			return data.updateFireStation(fireStation);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
	}
	

	

	
	
}
