package com.openclassrooms.safetinet.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetinet.data.DataWrapper;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class DataLoaderTest {

	@Test
	@Order(1)
	public void testLoadJsonData() {
		 String filePath = "FileData.json";
		 DataLoader dataLoader = new DataLoader(filePath);
        DataWrapper dataWrapper = dataLoader.loadJsonData();
		assertNotNull(dataWrapper);
		
	}
}
