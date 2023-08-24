package com.openclassrooms.safetinet.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.DTO.PersonByStationDTO;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class DTOControllerTest {
	@Autowired
	private DataWrapper dataWrapper;
	@Autowired
	private DTOController dTOController;

	@BeforeAll
	void before() {
		dataWrapper.setFirestations(new ArrayList<>());
	}

	@Test
	@Order(1)
	public void testGetPersonByFireStation() {
		List<PersonByStationDTO> personByStationDTO = new ArrayList<>();
		personByStationDTO.add( new PersonByStationDTO("John","Boyd","1509 Culver St","841-874-6512"));
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = dTOController.getPersonByFireStation("3");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(2)
	public void testGetPhoneByStation() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = dTOController.getPhoneByStation("3");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(3)
	public void testGetFireByAddress() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = dTOController.getPhoneByStation("1509 Culver St");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(4)
	public void testGetHouseholdByStation() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = dTOController.getHouseholdByStation("3");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(5)
	public void testGetPersondByStation() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = dTOController.getPersondByStation("John","Boyd");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(6)
	public void testGetEmailByCity() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = dTOController.getEmailByCity("Culver");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}

	@Test
	@Order(6)
	public void testGetChildByAddress() {
		HttpStatus expectedResponse = HttpStatus.OK;
		ResponseEntity<?> actualResponse = dTOController.getChildByAddress("1509 Culver St");
		assertEquals(expectedResponse,actualResponse.getStatusCode());
	}
}
