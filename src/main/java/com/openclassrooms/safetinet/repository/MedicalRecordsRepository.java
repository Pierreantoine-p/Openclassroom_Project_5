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
	 * Get medicalRecords
	 * @return List of MedicalRecords 
	 */
	public List<MedicalRecords> getAll() {
			return dataWrapper.getMedicalRecords();
 	}

	/**
	 * Get medicalRecords by  firstName and lastName
	 * @Param String : firstName
	 * @Param String : lastName
	 * @return List of MedicalRecord
	 */
	public List<MedicalRecords> findByName(String firstname, String lastname)  {
			return dataWrapper.getMedicalRecordByName(firstname, lastname);
 	}
	
	/**
	 * Get medicalRecords by  firstName and lastName
	 * @Param String : firstName
	 * @Param String : lastName
	 * @return List of MedicalRecord
	 */
	public List<MedicalRecords> getMedicalByName(String firtname, String lastname) {
		return dataWrapper.getMedicalByName(firtname, lastname);
}
	
	/**
	 * Created medicalRecords 
	 * @RequestBody medicalRecords
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
	 * Update MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 * @RequestBody medicalRecords
	 * @return MedicalRecord update
	 */
	public boolean update (String firstname, String lastname,MedicalRecords medicalRecord)  {
		boolean result = false;
		result = dataWrapper.updateMedicalRecords(firstname, lastname, medicalRecord);
		return result;
	}
	
	/**
	 * Delete MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 */
	public boolean delete(String firstname, String lastname)  {
		boolean result = false;
		result = dataWrapper.deleteMedicalRecords(firstname,lastname);
		return result;
	}
	
	
	
}
