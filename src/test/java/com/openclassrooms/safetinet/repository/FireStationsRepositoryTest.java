package com.openclassrooms.safetinet.repository;

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
import com.openclassrooms.safetinet.model.FireStations;
import com.openclassrooms.safetinet.service.FireStationsService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class FireStationsRepositoryTest {
	
	@Autowired
	private DataWrapper dataWrapper;
	@Autowired
	private FireStationRepository fireStationRepository;
	
	@BeforeAll
	void before() {
		dataWrapper.setFirestations(new ArrayList<>());
	}
		
	@Test
	@Order(1)
	public void testGetAll() {
		List<FireStations> expectedFireStations = new ArrayList<FireStations>();
		expectedFireStations.add(new FireStations("1509 Culver St","3"));
		
		dataWrapper.setFirestations(expectedFireStations);
		List<FireStations> actualPersons = fireStationRepository.getAll();
		
		assertEquals(expectedFireStations, actualPersons);

	}
	
	@Test
	@Order(2)
	public void testFindByAdress() {
		List<FireStations> expectedFireStations = new ArrayList<FireStations>();
		expectedFireStations.add(new FireStations("1509 Culver St","3"));
		
		dataWrapper.setFirestations(expectedFireStations);
		List<FireStations> actualPersons = fireStationRepository.findByAdress("1509 Culver St");
		
		assertEquals(expectedFireStations, actualPersons);

	}
	
	@Test
	@Order(3)
	public void testFindStationByNumber() {
		List<FireStations> expectedFireStations = new ArrayList<FireStations>();
		expectedFireStations.add(new FireStations("1509 Culver St","3"));
		
		dataWrapper.setFirestations(expectedFireStations);
		List<FireStations> actualPersons = fireStationRepository.getStationByNumber("3");
		
		assertEquals(expectedFireStations, actualPersons);

	}
	
	@Test
	@Order(4)
	public void testSave() {
		List<FireStations> expectedFireStations = new ArrayList<FireStations>();
		FireStations fireStations = new FireStations("1509 Culver St","3");
		expectedFireStations.add(fireStations);
		
		dataWrapper.setFirestations(expectedFireStations);
		Optional<FireStations> savedFireStations = fireStationRepository.save(fireStations);

		assertTrue(savedFireStations.isPresent());
		assertEquals(fireStations, savedFireStations.get());
	}
	
	@Test
	@Order(5)
	public void testUpdate() {
		List<FireStations> expectedFireStations = new ArrayList<>();
		expectedFireStations.add( new FireStations("1509 Culver St","3"));
		
		FireStations fireStationToUpdate = new FireStations("1509 Culver St","2") ;

		dataWrapper.setFirestations(expectedFireStations);
		boolean result = fireStationRepository.update("1509 Culver St", fireStationToUpdate);
	
		//System.out.println("updatedPerson" + updatedPerson.get());
		//System.out.println("personToUpdate" + personToUpdate);

		//assertTrue(updatedPerson.isPresent());
		assertTrue(result);	
	}
	
	@Test
	@Order(6)
	public void testDelete() {
		List<FireStations> expectedFireStations = new ArrayList<>();
		expectedFireStations.add( new FireStations("1509 Culver St","3"));

		dataWrapper.setFirestations(expectedFireStations);
		boolean result = fireStationRepository.delete("1509 Culver St");

		assertTrue(result);
	}
}
