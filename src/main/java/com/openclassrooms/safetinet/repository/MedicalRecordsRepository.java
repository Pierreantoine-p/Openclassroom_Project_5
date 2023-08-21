package com.openclassrooms.safetinet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.MedicalRecords;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MedicalRecordsRepository {
	
    private final DataWrapper dataWrapper;



	/**
	 * GET ALL
	 * @return
	 */
	public List<MedicalRecords> getAll() {
			return dataWrapper.getMedicalRecords();
 	}

	/**
	 * GET
	 * get on medicalRecord by firstName
	 * @param String firstName
	 * @return
	 */
	public List<MedicalRecords> findByName(String firstname, String lastname)  {
			return dataWrapper.getMedicalRecordByName(firstname, lastname);
 	}
	
	
	/**
	 * POST
	 * ajouter un dossier médical ;
	 * @param medicalRecords
	 */
	public Optional<MedicalRecords> save(MedicalRecords medicalRecord) {
		boolean isAdded = dataWrapper.getMedicalRecords().add(medicalRecord);
		if (isAdded) {
			return Optional.of(medicalRecord);
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * PUT
	 *  mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas)
	 *  @param MedicalRecords medicalRecords
	 */
	public boolean update (String firstname, String lastname,MedicalRecords medicalRecord)  {
		boolean result = false;
		result = dataWrapper.updateMedicalRecords(firstname, lastname, medicalRecord);
		return result;
	}
	
	/**
	 * DELETE
	 * supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 */
	public boolean delete(String firstname, String lastname)  {
		boolean result = false;
		result = dataWrapper.deleteMedicalRecords(firstname,lastname);
		return result;
	}
	
	public List<MedicalRecords> getMedicalByName(String firtname, String lastname) {
			return dataWrapper.getMedicalByName(firtname, lastname);
	}
	
}
