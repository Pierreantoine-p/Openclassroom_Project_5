package com.openclassrooms.safetinet.repository;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.FireStations;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class FireStationRepository {
	
    private final DataWrapper dataWrapper;    
    
    /**
	 * GET ALL
	 *  Get all fireStations
	 *  @return
	 */
	public List<FireStations> getAll()  {	
			return dataWrapper.getFireStations();
	}
	
	/**
	 * Find By Adress
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public List<FireStations> findByAdress(String address)   {
			return dataWrapper.getStationByAdress(address);	
	}
	
	/**
	 * Find Station By Number
	 *  Get one station by number
	 *  @param String firstName
	 *  @return
	 */
	public List<FireStations> getStationByNumber(String stationNumber)  {
		List<FireStations> result = dataWrapper.getStationByNumber(stationNumber);
			return result;
	}

	/**
	 * POST
	 *  ajout d'un mapping caserne/adresse ;
	 *  @param Person person
	 */
	public Optional<FireStations> save(FireStations fireStation) {
		boolean isAdded = dataWrapper.getFireStations().add(fireStation);
		if (isAdded) {
			return Optional.of(fireStation);
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * PUT
	 * mettre à jour le numéro de la caserne de pompiers d'une adresse ;
	 * @param Person person
	 * @return
	 */	
	public boolean update(String address, FireStations fireStation)   {
		boolean result = false;
		result = dataWrapper.updateFireStation(address, fireStation);
		return result;
	}
	
	/**
	 * DELETE
	 * supprimer le mapping d'une caserne ou d'une adresse
	 * @param String firstname
	 * @return
	 */
	public boolean delete(String address )   {
		boolean result = false;
		result = dataWrapper.deleteFireStation(address);
		return result;		
	}
}
