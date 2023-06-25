package com.openclassrooms.safetinet.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.data.DataMedicalRecord;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.service.MedicalRecordsService;

@Repository
public class MedicalRecordsRepository {
	
	
    private final List<MedicalRecords> medicalRecord = new ArrayList<>();
    
    private static final Logger logger = LogManager.getLogger(MedicalRecordsRepository.class);


	/**
	 * GET ALL
	 * @return
	 */
	public List<MedicalRecords> getAll()throws IOException{
		try {
			return Data.getMedicalRecords();

		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
 	}

	/**
	 * GET
	 * get on medicalRecord by firstName
	 * @param String firstName
	 * @return
	 */
	public Optional<MedicalRecords> findByName(String firstname, String lastname)throws IOException {
		try {
			return Data.getMedicalRecordByName(firstname, lastname);

		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
 	}
	
	
	/**
	 * POST
	 * ajouter un dossier médical ;
	 * @param medicalRecords
	 */
	public MedicalRecords save(MedicalRecords medicalRecord)throws IOException{
		try {
			Data.getMedicalRecords().add(medicalRecord);
			return medicalRecord;
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
		 
	}
	
	
	
	/**
	 * PUT
	 *  mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas)
	 *  @param MedicalRecords medicalRecords
	 */
	public boolean update (MedicalRecords medicalRecord) throws IOException{
		try {
			Data.updateMedicalRecords(medicalRecord);
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
	 
	}
	
	
	/**
	 * DELETE
	 * supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 */
	public boolean delete(MedicalRecords medicalRecord) throws IOException{
		try {
			Data.deleteMedicalRecords(medicalRecord);
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
	 
	}
	
	
}
