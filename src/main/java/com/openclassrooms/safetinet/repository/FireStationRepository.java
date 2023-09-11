package com.openclassrooms.safetinet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.FireStations;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class FireStationRepository {
	
    private final DataWrapper dataWrapper;    
    
    /**
	 * Get FireStations
	 * @return List of FireStations 
	 */
	public List<FireStations> getAll()  {	
			return dataWrapper.getFireStations();
	}
	
	/**
	 * Get FireStations by  address 
	 * @Param String : address
	 * @return List of FireStations
	 */
	public List<FireStations> findByAdress(String address)   {
			return dataWrapper.getStationByAdress(address);	
	}
	
	/**
	 * Get FireStations by  stationNumber 
	 * @Param String : stationNumber
	 * @return List of FireStations
	 */
	public List<FireStations> getStationByNumber(String stationNumber)  {
		List<FireStations> result = dataWrapper.getStationByNumber(stationNumber);
			return result;
	}

	/**
	 * Created fireStations 
	 * @RequestBody fireStations
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
	 * Update FireStations
	 * @Param String : address
	 * @RequestBody fireStation
	 * @return FireStations update
	 */
	public boolean update(String address, FireStations fireStation)   {
		boolean result = false;
		result = dataWrapper.updateFireStation(address, fireStation);
		return result;
	}
	
	/**
	 * Delete FireStations
	 * @Param String : address
	 */
	public boolean delete(String address )   {
		boolean result = false;
		result = dataWrapper.deleteFireStation(address);
		return result;		
	}
}
