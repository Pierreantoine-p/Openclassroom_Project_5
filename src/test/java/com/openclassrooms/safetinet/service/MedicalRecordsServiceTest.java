package com.openclassrooms.safetinet.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.MedicalRecords;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class MedicalRecordsServiceTest {
	
	@Autowired
	private DataWrapper dataWrapper;
	@Autowired
	private MedicalRecordsService medicalRecordsService;
	
	@BeforeAll
	void before() {
		dataWrapper.setMedicalrecords(new ArrayList<>());
	}
	

	@Test
	@Order(1)
	public void testGetAll() {
		List<MedicalRecords> expectedMedicalRecords = new ArrayList<MedicalRecords>();
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		expectedMedicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		dataWrapper.setMedicalrecords(expectedMedicalRecords);
		List<MedicalRecords> actualMedicalRecords = medicalRecordsService.getAll();
		assertEquals(expectedMedicalRecords, actualMedicalRecords);
	}
	
	@Test
	@Order(2)
	public void testFindByName() {
		List<MedicalRecords> expectedMedicalRecords = new ArrayList<MedicalRecords>();
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		expectedMedicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		dataWrapper.setMedicalrecords(expectedMedicalRecords);
		List<MedicalRecords> actualMedicalRecords = medicalRecordsService.findByName("John", "Boyd");
		assertEquals(expectedMedicalRecords, actualMedicalRecords);
	}

	@Test
	@Order(3)
	public void testGetMedicalByName() {
		List<MedicalRecords> expectedMedicalRecords = new ArrayList<MedicalRecords>();
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		expectedMedicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		dataWrapper.setMedicalrecords(expectedMedicalRecords);
		List<MedicalRecords> actualMedicalRecords = medicalRecordsService.getMedicalByName("John", "Boyd");
		assertEquals(expectedMedicalRecords, actualMedicalRecords);
	}
	
	@Test
	@Order(4)
	public void testSave() {
		List<MedicalRecords> expectedMedicalRecords = new ArrayList<MedicalRecords>();
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		MedicalRecords medicalRecords = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies);
		expectedMedicalRecords.add(medicalRecords);
		dataWrapper.setMedicalrecords(expectedMedicalRecords);
		Optional<MedicalRecords> saveMedicalRecords = medicalRecordsService.save(medicalRecords);
		assertEquals(medicalRecords, saveMedicalRecords.get());
	}

	
	@Test
	@Order(5)
	public void testDelete() {
		List<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		medicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		dataWrapper.setMedicalrecords(medicalRecords);
		boolean result = medicalRecordsService.delete("John","Boyd");
		assertTrue(result);
	}
	
	@Test
	@Order(6)
	public void testUpdate() {
		List<MedicalRecords> medicalRecords = new ArrayList<>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		List<String> medicationsToUpdate = new ArrayList<>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		medicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		dataWrapper.setMedicalrecords(medicalRecords);
		medicationsToUpdate.add( "pharmacol:5000mg");
		MedicalRecords medicalRecordsToUpdate = new MedicalRecords("John","Boyd","03/06/1984",medicationsToUpdate,allergies);
		boolean result = medicalRecordsService.update("John","Boyd", medicalRecordsToUpdate);
		assertTrue(result);	
	}
	
}
