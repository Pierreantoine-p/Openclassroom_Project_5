package com.openclassrooms.safetinet.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.FireStations;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class FireStationControllerTest {
	
	@Autowired
	private DataWrapper dataWrapper;
	@Autowired
	private FireStationsController fireStationsController;
	
	@Test
	@Order(1)
	public void testGetOne() {		
		List<FireStations> expectedFireStations = new ArrayList<FireStations>();
		expectedFireStations.add(new FireStations("1509 Culver St","3"));
		dataWrapper.setFirestations(expectedFireStations);
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = fireStationsController.getAddress("1509 Culver St");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void testSave() {		
		FireStations fireStations = new FireStations("1509 Culver St","3");
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = fireStationsController.save(fireStations);
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
	@Test
	@Order(3)
	public void testUpdate() {	
		List<FireStations> fireStations = new ArrayList<>();
		fireStations.add(new FireStations("1509 Culver St","3"));
		FireStations fireStationToUpdate = new FireStations("1509 Culver St", "2");
		dataWrapper.setFirestations(fireStations);
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = fireStationsController.update("1509 Culver St", fireStationToUpdate);
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
	
	@Test
	@Order(4)
	public void testDelete() {		
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = fireStationsController.delete("1509 Culver St");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
}
