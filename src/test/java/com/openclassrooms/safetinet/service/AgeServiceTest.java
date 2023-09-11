package com.openclassrooms.safetinet.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class AgeServiceTest {
	
	@Autowired
	private AgeService ageService;
	
	@Test
	@Order(1)
	public void testGetAge() {
		String birthDate = "08/25/1990";
		
		int result = ageService.getAge(birthDate);
		assertEquals(result, 33);
	}
}
