package com.openclassrooms.safetinet.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.controller.MedicalRecordsController;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.FireStationRepository;

@Service
public class FireStationsService {

	public FireStationRepository fireStationRepository;
		
    private static final Logger logger = LogManager.getLogger(FireStationsService.class);

	public FireStationsService(FireStationRepository fireStationRepository) {
		this.fireStationRepository = fireStationRepository;
	}
	
	
	
	//GET
	public List<FireStations> getAll() throws IOException{
		try {
			return fireStationRepository.getAll();

		}catch(Exception e) {
			logger.error("Error : " + e);

	       throw new IOException();
		}
	}
	
	//GET ONE
	public Optional<FireStations> findByAdress(String address ) throws IOException{
		try {
			return fireStationRepository.findByAdress(address);
		}catch(Exception e) {
			logger.error("Error : " + e);

	       	throw new IOException();
		}
	}
	
	
	
	
	public List<FireStations>findStationByNumber(String stationNumber) throws IOException{
		try {
			return fireStationRepository.findByNumber(stationNumber);

		}catch(Exception e) {
			logger.error("Error : " + e);

	       	throw new IOException();
		}
	}
	
	
	
	
	//POST
	public FireStations save(FireStations fireStations) throws IOException {
		try {
			fireStationRepository.save(fireStations);
			return fireStations;
		}catch(Exception e) {
			logger.error("Error : " + e);

	       	throw new IOException();
		}
	}
	
	//DELETE	
	public boolean  delete(String address) throws IOException {
		try {
			List<FireStations> stations = fireStationRepository.getAll();
			for(FireStations station : stations) {
				if(station.getAddress().equals(address)) {
					return fireStationRepository.delete(station);
				}
			}
			return false;
			
		}catch(Exception e) {
			logger.error("Error : " + e);

	       	throw new IOException();
		}
		
	}
	
	//PATCH
	public boolean update(String address, FireStations fireStation) throws IOException {
		try {
			List<FireStations> stations = fireStationRepository.getAll();
			for(FireStations station : stations) {
				if(station.getAddress().equals(address)) {
					return fireStationRepository.update(fireStation);
				}
			}
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);

	       	throw new IOException();
		}
	}


}
