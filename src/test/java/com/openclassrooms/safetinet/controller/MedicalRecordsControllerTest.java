package com.openclassrooms.safetinet.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class MedicalRecordsControllerTest {
	
	@Autowired
	private DataWrapper dataWrapper;
	
	@Autowired
	private MedicalRecordsController medicalRecordsController;
	
	@BeforeAll
	void before() {
		dataWrapper.setMedicalrecords(new ArrayList<>());
	}
	
	
	@Test
	@Order(1)
	public void testGetAll() {		
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = medicalRecordsController.getMedicalRecords();
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void testGetOne() {		
		List<MedicalRecords> medialRecords = new ArrayList<MedicalRecords>();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		medialRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		dataWrapper.setMedicalrecords(medialRecords);
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = medicalRecordsController.getMedicalRecord("John","Boyd");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void testSave() {	
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		MedicalRecords medicalRecords = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies);
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = medicalRecordsController.save(medicalRecords);
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
	@Test
	@Order(4)
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
		System.out.println("medicalRecordsToUpdate" + medicalRecordsToUpdate);
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = medicalRecordsController.update("John","Boyd", medicalRecordsToUpdate);
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
	@Test
	@Order(5)
	public void testDelete() {	
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = medicalRecordsController.delete("John","Boyd");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
}
