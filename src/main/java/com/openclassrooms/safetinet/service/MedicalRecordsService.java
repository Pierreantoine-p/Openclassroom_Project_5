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

	public List<MedicalRecords> getAll() {
		return medicalRecordsRepository.getAll();
	}

	public List<MedicalRecords> findByName(String firstName, String lastName)  {
		return medicalRecordsRepository.findByName(firstName, lastName);
	}

	public Optional<MedicalRecords> save(MedicalRecords medicalRecord)  {
		return medicalRecordsRepository.save(medicalRecord);
	}

	public boolean delete(String firstName, String lastName)  {
		boolean result = false;
		result = medicalRecordsRepository.delete(firstName, lastName);
		return result;
	}

	public boolean update(String firstName, String lastName, MedicalRecords medicalRecord)  {
		boolean result = false;
		result = medicalRecordsRepository.update(firstName,lastName, medicalRecord);
		return result;
	}

	public List<MedicalRecords> getMedicalByName(String firstName, String lastName)  {
		return medicalRecordsRepository.getMedicalByName(firstName, lastName);
	}

}
