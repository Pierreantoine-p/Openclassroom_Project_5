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
	
	public List<FireStations> getfireStations(){
		return fireStationRepository.getFireStations();
	}
	
	public void save(FireStations fireStations) {
		fireStationRepository.save(fireStations);
	}
	
	
	public void deletePerson(String firstname ) {
		fireStationRepository.delete(firstname);
	}
	
	
	public FireStations updatePerson(FireStations fireStations) {
		fireStationRepository.update(fireStations);
		return fireStations;
	}
	
	public Optional<FireStations> findPersonByName(String address) {
		return fireStationRepository.findByName(address);
	}

}
