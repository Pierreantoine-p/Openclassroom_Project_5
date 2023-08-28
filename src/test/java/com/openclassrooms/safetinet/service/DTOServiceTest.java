package com.openclassrooms.safetinet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import com.openclassrooms.safetinet.model.DTO.ListPersonByAddressDTO;

import com.openclassrooms.safetinet.model.DTO.PersonByAdressWithFireStationListDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationWithCountDTO;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DTOServiceTest {

	@Autowired
	private DTOService dTOService;

	@Test
	@Order(1)
	public void testGetPersonByStation() {
		PersonByStationWithCountDTO result = dTOService.getPersonByStation("3");

		assertEquals(result.getPersonByStationDTO().size(), 13);
		assertEquals(result.getCountAdult(), 10);
		assertEquals(result.getCountChild(), 3);
	}

	@Test
	@Order(2)
	public void testGetPhoneByStation() {
		List<String> phoneList = new ArrayList<String>();
		phoneList.add(new String("841-874-6512"));
		phoneList.add(new String("841-874-6512"));
		phoneList.add(new String("841-874-6544"));
		phoneList.add(new String("841-874-6874"));
		phoneList.add(new String("841-874-6544"));
		List<String> result = dTOService.getPhoneByStation("3");

		assertEquals(result.size(), 5);
		assertEquals(phoneList, result);
	}

	@Test
	@Order(3)
	public void testGetFireByAddress() {
		PersonByAdressWithFireStationListDTO result = dTOService.getFireByAddress("1509 Culver St");
		
		assertEquals(result. getPersonByAdressWithFireStationDTO().size(), 5);
	}


	@Test
	@Order(4)
	public void testGetChildByAddress() {
		ListPersonByAddressDTO result = dTOService.getChildByAddress("1509 Culver St");
		
		assertEquals(result.getChildByAddressDTO().size(), 2);
		assertEquals(result.getAdultByAddressDTO().size() , 3);
	}

	/*
	@Test
	@Order(5)
	public void testGetHouseholdByStation() {
		assertEquals();
	}
	 */

	@Test
	@Order(7)
	public void testGetEmailByCity() {
		List<String> result =  dTOService.getEmailByCity("Culver");
		
		assertEquals(result.size(), 15);
	}

}
