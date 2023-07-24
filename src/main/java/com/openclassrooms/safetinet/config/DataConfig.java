package com.openclassrooms.safetinet.config;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openclassrooms.safetinet.SafetinetApplication;
import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.repository.DataLoader;

@Configuration
public class DataConfig {
	
	@Bean
	public Data data() {
		DataLoader dataload = new DataLoader("FileData.json");
		return dataload.loadJsonData();
	}
	/*
    private static final Logger logger = LogManager.getLogger(DataConfig.class);
    public DataLoader(String filePath) {
        this.filePath = filePath;
    }

 * @Bean
	public DataPersons data() throws IOException {
		DataLoader dataLoader = new DataLoader();
		dataLoader.loadJson();
		DataPersons data = dataLoader.loadJson();
		return data;
		
	
	DataLoader dataload = new DataLoader("FileData.json");

	try {
		dataload.loadJsonData();
		logger.info("Fichier chargé avec succès.");


	} catch (IOException e) {
		// TODO Auto-generated catch block
		logger.info("Problème lors du lancement de l'application" + e);
		e.printStackTrace();
	}*/
	
}
