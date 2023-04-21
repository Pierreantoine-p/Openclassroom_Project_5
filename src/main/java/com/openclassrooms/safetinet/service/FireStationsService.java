package com.openclassrooms.safetinet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.repository.FireStationRepository;

@Service
public class FireStationsService {

	public FireStationRepository fireStationRepository;
		
	public FireStationsService(FireStationRepository fireStationRepository) {
		this.fireStationRepository = fireStationRepository;
	}
	
	public List<FireStations> getfireStations(){
		return fireStationRepository.getFireStations();
	}

}
