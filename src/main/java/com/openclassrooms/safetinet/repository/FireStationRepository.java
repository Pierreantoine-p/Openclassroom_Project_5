package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.data.DataFireStation;
import com.openclassrooms.safetinet.data.DataPerson;
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.model.Person;

@Repository
public class FireStationRepository {

    private final List<FireStations> fireStations = new ArrayList<>();

    /**
	 * GET ALL
	 *  Get all fireStations
	 *  @return
	 */
	public List<FireStations> getAll(){
		return Data.getFireStations();
	}
	

	/**
	 * POST
	 *  ajout d'un mapping caserne/adresse ;
	 *  @param Person person
	 */
	public FireStations save(FireStations fireStation){
		Data.getFireStations().add(fireStation);
		return fireStation;
	}
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	public Optional<FireStations> findByAdress(String address) {
		return Data.getStationByAdress(address);
	}
	
	/**
	 * DELETE
	 * supprimer le mapping d'une caserne ou d'une adresse
	 * @param String firstname
	 * @return
	 */
	public boolean delete(FireStations fireStation) {
		Data.deleteFireStation(fireStation);
		return false;
	}
	
	
	/**
	 * PUT
	 * mettre à jour le numéro de la caserne de pompiers d'une adresse ;
	 * @param Person person
	 * @return
	 */	
	public boolean update(FireStations fireStation) {
		Data.updateFireStation(fireStation);
		return false;
	}
	

	

	
	
}
