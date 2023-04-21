package com.openclassrooms.safetinet.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.model.MedicalRecords;

@Repository
public class MedicalRecordsRepository {
	
	@Autowired
	public List<MedicalRecords> getMedicalRecords(){
		return Data.getMedicalRecords();
	}

	/**
	 *  ajouter un dossier médical ;
	 */
	
	
	
	/**
	 *  mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas) ;
	 */
	
	
	/**
	 * supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique).
	 */
	
	
}
