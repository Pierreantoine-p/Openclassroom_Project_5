package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.FireStationRepository;

@Service
public class FireStationsService {

	public FireStationRepository fireStationRepository;
		
	public FireStationsService(FireStationRepository fireStationRepository) {
		this.fireStationRepository = fireStationRepository;
	}
	
	//GET
	public List<FireStations> getAll(){
		return fireStationRepository.getAll();
	}
	
	//GET ONE
	public Optional<FireStations> findByAdress(String address ){
		return fireStationRepository.findByAdress(address);
	}
	//POST
	public void save(FireStations fireStations) {
		fireStationRepository.save(fireStations);
	}
	
	//DELETE	
	public boolean  delete(String address) {
		List<FireStations> stations = fireStationRepository.getAll();
		for(FireStations station : stations) {
			if(station.getAddress().equals(address)) {
				return fireStationRepository.delete(station);
			}
		}
		return false;
		}
	
	
	//PATCH
	public boolean update(String address, FireStations fireStation) {
		List<FireStations> stations = fireStationRepository.getAll();
		for(FireStations station : stations) {
			if(station.getAddress().equals(address)) {
				return fireStationRepository.update(fireStation);
			}
		}
		return false;
	}


}
