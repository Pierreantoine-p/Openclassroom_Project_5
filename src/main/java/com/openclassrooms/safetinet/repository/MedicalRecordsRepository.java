package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.MedicalRecords;

@Repository
public class MedicalRecordsRepository {
	
	
    private final List<MedicalRecords> medicalRecord = new ArrayList<>();
    DataWrapper data = new DataWrapper();
    private static final Logger logger = LogManager.getLogger(MedicalRecordsRepository.class);


	/**
	 * GET ALL
	 * @return
	 */
	public List<MedicalRecords> getAll() {
		try {
			return data.getMedicalRecords();

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<MedicalRecords>();
		}
 	}

	/**
	 * GET
	 * get on medicalRecord by firstName
	 * @param String firstName
	 * @return
	 */
	public Optional<MedicalRecords> findByName(String firstname, String lastname)  {
		try {
			return data.getMedicalRecordByName(firstname, lastname);

		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
 	}
	
	
	/**
	 * POST
	 * ajouter un dossier médical ;
	 * @param medicalRecords
	 */
	public boolean save(MedicalRecords medicalRecord) {
		try {
			return data.getMedicalRecords().add(medicalRecord);
		}catch(Exception e) {
			logger.error("Error : " + e);
			return false;
		}
		 
	}
	
	
	
	/**
	 * PUT
	 *  mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas)
	 *  @param MedicalRecords medicalRecords
	 */
	public Optional<MedicalRecords> update (MedicalRecords medicalRecord)  {
		try {
			return data.updateMedicalRecords(medicalRecord);
			
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
	 
	}
	
	
	/**
	 * DELETE
	 * supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 */
	public boolean delete(MedicalRecords medicalRecord)  {
		try {
			
			data.deleteMedicalRecords(medicalRecord);
			return true;
		}catch(Exception e) {
			logger.error("Error : " + e);
			return false;
		}
	}
	
	public Optional<MedicalRecords> getMedicalByName(String firtname, String lastname) {
		try {
			return data.getMedicalByName(firtname, lastname);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
	}
	
	
}
