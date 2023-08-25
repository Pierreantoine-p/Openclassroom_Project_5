package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.repository.FireStationRepository;

@Service
public class FireStationsService {

	public FireStationRepository fireStationRepository;


	public FireStationsService(FireStationRepository fireStationRepository) {
		this.fireStationRepository = fireStationRepository;
	}
	
	/**
	 * Get FireStations
	 * @return List of FireStations 
	 */
	public List<FireStations> getAll()  {
		return fireStationRepository.getAll();
	}

	/**
	 * Get FireStations by  address 
	 * @Param String : address
	 * @return List of FireStations
	 */
	public List<FireStations> findByAdress(String address )  {
		return fireStationRepository.findByAdress(address);
	}

	/**
	 * Get FireStations by  stationNumber 
	 * @Param String : stationNumber
	 * @return List of FireStations
	 */
	public List<FireStations>getStationByNumber(String stationNumber)  {
		return fireStationRepository.getStationByNumber(stationNumber);
	}
	
	/**
	 * Created fireStations 
	 * @RequestBody fireStations
	 */
	public Optional<FireStations> save(FireStations fireStations)   {
		return fireStationRepository.save(fireStations);
	}
	
	/**
	 * Update FireStations
	 * @Param String : address
	 * @RequestBody fireStation
	 * @return FireStations update
	 */
	public boolean update(String address, FireStations fireStation)   {
		boolean result = false;
		result = fireStationRepository.update(address, fireStation);
		return result;
	}
	
	/**
	 * Delete FireStations
	 * @Param String : address
	 */
	public boolean  delete(String address)   {
		boolean result = false;
		result = fireStationRepository.delete(address);
		return result;
	}

}
