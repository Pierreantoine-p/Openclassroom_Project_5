package com.openclassrooms.safetinet.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.MedicalRecordsRepository;

@SpringBootTest
public class MedicalRecordsServiceTest {

	private MedicalRecordsRepository medicalRecordsRepository;
	private MedicalRecordsService medicalRecordsService;
	
	@Before
	public void setUp() {
		medicalRecordsRepository = mock(MedicalRecordsRepository.class);
		medicalRecordsService = new MedicalRecordsService(medicalRecordsRepository);
	}
	
	@Test
	public void testGetAll() {
		List<MedicalRecords> expectedMedicalRecords = new ArrayList<MedicalRecords>();
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");

		expectedMedicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		
		doReturn(expectedMedicalRecords).when(medicalRecordsRepository).getAll();
		
		List<MedicalRecords> actualMedicalRecords = medicalRecordsService.getAll();
		
		assertEquals(expectedMedicalRecords, actualMedicalRecords);
	}
	
	@Test
	public void testGetAll_Exception() {

		when(medicalRecordsRepository.getAll()).thenThrow(new RuntimeException("Database connection error"));
		List<MedicalRecords> actualPersons = medicalRecordsService.getAll();

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}
	
	@Test
	public void testFindByName() {
		List<MedicalRecords> expectedMedicalRecords = new ArrayList<MedicalRecords>();
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		expectedMedicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));
		
		doReturn(expectedMedicalRecords).when(medicalRecordsRepository).findByName("John","Boyd");
		Optional<MedicalRecords> actualMedicalRecords = medicalRecordsService.findByName("John", "Boyd");
		
		assertEquals(expectedMedicalRecords, actualMedicalRecords);

	}
	
	@Test
	public void testGetPersonByAddress_Exception() {

		when(medicalRecordsRepository.findByName("John","Boyd")).thenReturn(Optional.empty());
		Optional<MedicalRecords> actualPersons = medicalRecordsService.findByName("John", "Boyd");

		//assertNotNull(actualPersons);
		assertTrue(actualPersons.isEmpty());

	}
	
	@Test
	public void testSave() {
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		
		MedicalRecords medicalRecords = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies);
		
		doNothing().when(medicalRecordsRepository).save(medicalRecords);
		
		Optional<MedicalRecords> saveMedicalRecords = medicalRecordsService.save(medicalRecords);
		assertEquals(medicalRecords, saveMedicalRecords.get());
	}
	
	@Test
	public void testSave_Exception() {
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		
		MedicalRecords medicalRecords = new MedicalRecords("John","Boyd","03/06/1984",medications,allergies);
		
		doThrow(new RuntimeException("Database connection error")).when(medicalRecordsRepository).save(medicalRecords);
		Optional<MedicalRecords> savedPerson = medicalRecordsService.save(medicalRecords);
		assertNotNull(savedPerson);
		assertFalse(savedPerson.isPresent());
	}
	
	@Test
	public void testDelete() {
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		List<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		
		medicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));

		when(medicalRecordsRepository.getAll()).thenReturn(medicalRecords);
		Optional<Person> deletedPerson = medicalRecordsService.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(medicalRecordsRepository, times(1)).delete(any(), null);
	}
	
	@Test
	public void testDelete_NotFound() {
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		List<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		
		medicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));

		when(medicalRecordsRepository.getAll()).thenReturn(medicalRecords);
		Optional<Person> deletedPerson = medicalRecordsService.delete("John","Boyd");

		assertFalse(deletedPerson.isPresent());
		verify(medicalRecordsRepository, never()).delete(any(), null);

	}
	
	@Test
	public void testUpdate() {
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		List<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		
		MedicalRecords medicalRecordsToUpdate = new MedicalRecords("John","Doe","03/06/1984",medications,allergies);
		medicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));

		when(medicalRecordsRepository.getAll()).thenReturn(medicalRecords);
		when(medicalRecordsRepository.update(medicalRecordsToUpdate)).thenReturn(Optional.of(medicalRecordsToUpdate));
		Optional<MedicalRecords> updateMedicalRecords = medicalRecordsService.update("John","Boyd", medicalRecordsToUpdate);
		
		assertTrue(updateMedicalRecords.isPresent());
		assertEquals(medicalRecordsToUpdate, updateMedicalRecords.get());
		
	}
	
	@Test
	public void testUpdate_Exception() {
		List<String>medications = new ArrayList<String>();
		List<String>allergies = new ArrayList<String>();
		
		medications.add("aznol:350mg");
		medications.add("hydrapermazol:100mg");
		allergies.add( "nillacilan");
		
		MedicalRecords medicalRecordsToUpdate = new MedicalRecords("John","Doe","03/06/1984",medications,allergies);
		List<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();
		medicalRecords.add(new MedicalRecords("John","Boyd","03/06/1984",medications,allergies));

		when(medicalRecordsRepository.getAll()).thenReturn(medicalRecords);
		Optional<MedicalRecords> updateMedicalRecords = medicalRecordsService.update("John", "Boyd", medicalRecordsToUpdate);

		assertNotNull(medicalRecordsToUpdate);
		assertFalse(updateMedicalRecords.isPresent());
	}
	
	@Test
	public void testGetMedicalByName() {
		
	}
	
}
