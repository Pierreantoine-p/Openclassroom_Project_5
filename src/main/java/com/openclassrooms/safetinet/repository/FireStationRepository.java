package com.openclassrooms.safetinet.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.model.FireStations;

@Repository
public class FireStationRepository {

	@Autowired
	public List<FireStations> getFireStations(){
		return Data.getFireStations();
	}
	
	/**
	 *  ajout d'un mapping caserne/adresse ;
	 */
	
	
	
	/**
	 * mettre à jour le numéro de la caserne de pompiers d'une adresse ;
	 */
	
	
	/**
	 * supprimer le mapping d'une caserne ou d'une adresse.
	 */
	
	
}
