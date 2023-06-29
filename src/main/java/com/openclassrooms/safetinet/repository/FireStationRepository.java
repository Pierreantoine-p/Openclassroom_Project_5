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


@Repository
public class FireStationRepository {

    private final List<FireStations> fireStations = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(FireStationRepository.class);

    
    /**
	 * GET ALL
	 *  Get all fireStations
	 *  @return
	 */
	public List<FireStations> getAll() throws IOException{
		try {
			return Data.getFireStations();

		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
	}
	

	/**
	 * POST
	 *  ajout d'un mapping caserne/adresse ;
	 *  @param Person person
	 */
	public FireStations save(FireStations fireStation) throws IOException{
		try {
			Data.getFireStations().add(fireStation);
			return fireStation;	
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
		
	}
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public Optional<FireStations> findByAdress(String address) throws IOException {
		try {
			return Data.getStationByAdress(address);
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
		
	}
	
	
	
	public List<FireStations> findByNumber(String stationNumber) throws IOException {
		try {
			return Data.getStationByNumber(stationNumber);

		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
	}
	
	
	/**
	 * DELETE
	 * supprimer le mapping d'une caserne ou d'une adresse
	 * @param String firstname
	 * @return
	 */
	public boolean delete(FireStations fireStation) throws IOException {
		try {
			Data.deleteFireStation(fireStation);
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
		
	}
	
	
	/**
	 * PUT
	 * mettre à jour le numéro de la caserne de pompiers d'une adresse ;
	 * @param Person person
	 * @return
	 */	
	public boolean update(FireStations fireStation)  throws IOException{
		try {
			Data.updateFireStation(fireStation);
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);
	       	throw new IOException();
		}
	}
	

	

	
	
}
