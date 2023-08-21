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

	public List<FireStations> getAll()  {
		return fireStationRepository.getAll();
	}

	public List<FireStations> findByAdress(String address )  {
		return fireStationRepository.findByAdress(address);
	}

	public List<FireStations>getStationByNumber(String stationNumber)  {
		return fireStationRepository.getStationByNumber(stationNumber);
	}

	public Optional<FireStations> save(FireStations fireStations)   {
		return fireStationRepository.save(fireStations);
	}

	public boolean  delete(String address)   {
		boolean result = false;
		result = fireStationRepository.delete(address);
		return result;
	}

	public boolean update(String address, FireStations fireStation)   {
		boolean result = false;
		result = fireStationRepository.update(address, fireStation);
		return result;
	}
}
