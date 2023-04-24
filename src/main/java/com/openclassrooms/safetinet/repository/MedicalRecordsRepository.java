package com.openclassrooms.safetinet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.model.MedicalRecords;

@Repository
public class MedicalRecordsRepository {
	
    private final List<MedicalRecords> medicalRecord = new ArrayList<>();

	/**
	 * GET ALL
	 * @return
	 */
	@Autowired
	public List<MedicalRecords> getMedicalRecords(){
		return Data.getMedicalRecords();
	}

	
	/**
	 * POST
	 * ajouter un dossier médical ;
	 * @param medicalRecords
	 */
	@Autowired
	public void save(MedicalRecords medicalRecords){
		medicalRecord.add(medicalRecords);
	}
	
	
	/**
	 * GET
	 * get on medicalRecord by firstName
	 * @param String firstName
	 * @return
	 */
	@Autowired
	public Optional<MedicalRecords> findByName(String firstName) {
		return medicalRecord.stream().filter(p -> p.getFirstName() == firstName).findFirst();
	}
	
	
	/**
	 * PUT
	 *  mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas)
	 *  @param MedicalRecords medicalRecords
	 */
	@Autowired
	public MedicalRecords update(MedicalRecords medicalRecords) {
		medicalRecord.stream()
		.filter(p -> p.getFirstName() == medicalRecords.getFirstName())
		.findFirst()
		.ifPresent(p ->{
			p.setBirthdate(medicalRecords.getBirthdate());
			p.setMedications(medicalRecords.getMedications());
			p.setAllergies(medicalRecords.getAllergies());
		});
		return medicalRecords;
	}
	
	
	
	/**
	 * DELETE
	 * supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
	 * @param String firstname
	 */
	@Autowired
	public void delete(@PathVariable("firstName") String firstname) {
		medicalRecord.remove(firstname);
	}
	
	
}
