package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.safetinet.data.Data;
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
	@Autowired
	public List<FireStations> getFireStations(){
		return Data.getFireStations();
	}
	

	/**
	 * POST
	 *  ajout d'un mapping caserne/adresse ;
	 *  @param Person person
	 */
	@Autowired
	public void save(FireStations fireStation){
		fireStations.add(fireStation);
	}
	
	/**
	 * GET ONE
	 *  Get one person by firstname
	 *  @param String firstName
	 *  @return
	 */
	@Autowired
	public Optional<FireStations> findByName(String address) {
		return fireStations.stream().filter(p -> p.getAddress() == address).findFirst();
	}
	
	
	/**
	 * PUT
	 * mettre à jour le numéro de la caserne de pompiers d'une adresse ;
	 * @param Person person
	 * @return
	 */
	@Autowired
	public FireStations update(FireStations fireStation) {
		fireStations.stream()
		.filter(p -> p.getAddress() == fireStation.getAddress())
		.findFirst()
		.ifPresent(p ->{
			p.setStation(fireStation.getStation());
		});
		return fireStation;
	}
	
	
	
	/**
	 * DELETE
	 * supprimer le mapping d'une caserne ou d'une adresse
	 * @param String firstname
	 * @return
	 */
	@Autowired
	public void delete(@PathVariable("address") String address) {
		fireStations.remove(address);
	}
	
	
}
