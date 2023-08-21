package com.openclassrooms.safetinet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.controller.PersonsController;
import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.model.DTO.PersonByAdressWithFireStationListDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationDTO;
import com.openclassrooms.safetinet.model.DTO.PersonByStationWithCountDTO;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class DTOServiceTest {

/*
	@Autowired
	private DataWrapper dataWrapper;
	*/
	@Autowired
	private DTOService dTOService;
	
	/*
	@BeforeAll
	void before() {
		dataWrapper.setPersons(new ArrayList<>());
	}
	
*/
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
/*
		resultPersonByAdressWithFireStationListDTO(fireStation=3, personByAdressWithFireStationDTO=[PersonByAdressWithFireStationDTO(lastName=Boyd, phone=841-874-6512, age=39, medications=[aznol:350mg, hydrapermazol:100mg], allergies=[nillacilan]), PersonByAdressWithFireStationDTO(lastName=Boyd, phone=841-874-6513, age=34, medications=[pharmacol:5000mg, terazine:10mg, noznazol:250mg], allergies=[]), PersonByAdressWithFireStationDTO(lastName=Boyd, phone=841-874-6512, age=11, medications=[], allergies=[peanut]), PersonByAdressWithFireStationDTO(lastName=Boyd, phone=841-874-6512, age=5, medications=[], allergies=[]), PersonByAdressWithFireStationDTO(lastName=Boyd, phone=841-874-6544, age=37, medications=[tetracyclaz:650mg], allergies=[xilliathal])])

*/

		PersonByAdressWithFireStationListDTO result = dTOService.getFireByAddress("1509 Culver St");
System.out.println("result" + result);
		//assertEquals(result);
		//assertEquals(phoneList, result);


	}
	
	/*
	@Test
	@Order(4)
	public void testGetChildByAddress() {
		assertEquals();
	}
	
	@Test
	@Order(5)
	public void testGetHouseholdByStation() {
		assertEquals();
	}
	

	
	@Test
	@Order(7)
	public void testGetEmailByCity() {
		List<String> result = 
		assertEquals();
	}
	*/
	/*
	@Test
	@Order()
	public void test() {
		assertEquals();
	}
	*/

}
