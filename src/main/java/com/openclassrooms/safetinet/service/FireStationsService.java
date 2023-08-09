package com.openclassrooms.safetinet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.repository.FireStationRepository;

@Service
public class FireStationsService {

	public FireStationRepository fireStationRepository;

    private static final Logger logger = LogManager.getLogger(FireStationsService.class);

	public FireStationsService(FireStationRepository fireStationRepository) {
		this.fireStationRepository = fireStationRepository;
	}



	//GET
	public List<FireStations> getAll()  {
		try {
			return fireStationRepository.getAll();

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
	}

	//GET ONE
	public List<FireStations> findByAdress(String address )  {
		try {
			return fireStationRepository.findByAdress(address);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
	}




	public List<FireStations>findStationByNumber(String stationNumber)  {
		try {
			List<FireStations> result = fireStationRepository.findByNumber(stationNumber);
			System.out.println("result" + result);
			return result;

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}
	}




	//POST
	public Optional<FireStations> save(FireStations fireStations)   {
		try {
			fireStationRepository.save(fireStations);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		return null;
	}

	//DELETE
	public Optional<FireStations>  delete(String address)   {
		try {
			List<FireStations> stations = fireStationRepository.getAll();
			for(FireStations station : stations) {
				if(station.getAddress().equals(address)) {
					 fireStationRepository.delete(station);
				}
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		return null;

	}

	//PATCH
	public Optional<FireStations> update(String address, FireStations fireStation)   {
		try {
			List<FireStations> stations = fireStationRepository.getAll();
			for(FireStations station : stations) {
				if(station.getAddress().equals(address)) {
					 fireStationRepository.update(fireStation);
				}
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		return null;
	}


}
