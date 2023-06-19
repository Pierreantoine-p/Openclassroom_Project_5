package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.data.DataMedicalRecord;
import com.openclassrooms.safetinet.model.MedicalRecords;

@Repository
public class MedicalRecordsRepository {
	
	
    private final List<MedicalRecords> medicalRecord = new ArrayList<>();

	/**
	 * GET ALL
	 * @return
	 */
	public List<MedicalRecords> getAll(){
		return Data.getMedicalRecords();
	}

	/**
	 * GET
	 * get on medicalRecord by firstName
	 * @param String firstName
	 * @return
	 */
	public Optional<MedicalRecords> findByName(String firstname, String lastname) {
		return Data.getMedicalRecordByName(firstname, lastname);
	}
	
	
	/**
	 * POST
	 * ajouter un dossier médical ;
	 * @param medicalRecords
	 */
	public MedicalRecords save(MedicalRecords medicalRecord){
		Data.getMedicalRecords().add(medicalRecord);
		return medicalRecord;
	}
	
	
	
	/**
	 * PUT
	 *  mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas)
	 *  @param MedicalRecords medicalRecords
	 */
	public boolean update (MedicalRecords medicalRecord) {
		Data.updateMedicalRecords(medicalRecord);
		return false;
	}
	
	
	/**
	 * DELETE
	 * supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 */
	public boolean delete(MedicalRecords medicalRecord) {
		Data.deleteMedicalRecords(medicalRecord);
		return false;
	}
	
	
}
