package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {

	public MedicalRecordsRepository medicalRecordsRepository;


	public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository) {
		this.medicalRecordsRepository = medicalRecordsRepository;
	}

	/**
	 * Get medicalRecords
	 * @return List of MedicalRecords 
	 */
	public List<MedicalRecords> getAll() {
		return medicalRecordsRepository.getAll();
	}

	/**
	 * Get medicalRecords by  firstName and lastName
	 * @Param String : firstName
	 * @Param String : lastName
	 * @return List of MedicalRecord
	 */
	public List<MedicalRecords> getMedicalByName(String firstName, String lastName)  {
		return medicalRecordsRepository.getMedicalByName(firstName, lastName);
	}
	
	/**
	 * Get medicalRecords by  firstName and lastName
	 * @Param String : firstName
	 * @Param String : lastName
	 * @return List of MedicalRecord
	 */
	public List<MedicalRecords> findByName(String firstName, String lastName)  {
		return medicalRecordsRepository.findByName(firstName, lastName);
	}
	
	/**
	 * Created medicalRecords 
	 * @RequestBody medicalRecords
	 */
	public Optional<MedicalRecords> save(MedicalRecords medicalRecord)  {
		return medicalRecordsRepository.save(medicalRecord);
	}

	/**
	 * Update MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 * @RequestBody medicalRecords
	 * @return MedicalRecord update
	 */
	public boolean update(String firstName, String lastName, MedicalRecords medicalRecord)  {
		boolean result = false;
		result = medicalRecordsRepository.update(firstName,lastName, medicalRecord);
		return result;
	}
	
	/**
	 * Delete MedicalRecords
	 * @Param String : lastName
	 * @Param String : firstName
	 */
	public boolean delete(String firstName, String lastName)  {
		boolean result = false;
		result = medicalRecordsRepository.delete(firstName, lastName);
		return result;
	}

}
