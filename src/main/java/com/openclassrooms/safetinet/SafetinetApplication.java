package com.openclassrooms.safetinet;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.openclassrooms.safetinet.controller.PersonsController;
import com.openclassrooms.safetinet.repository.DataLoader;

@SpringBootApplication
@ComponentScan({"com.openclassrooms.safetinet"})
public class SafetinetApplication {
	
    private static final Logger logger = LogManager.getLogger(PersonsController.class);

	
	public static void main(String[] args) {
		logger.info("L'application a démarré avec succès.");
		DataLoader dataload = new DataLoader("FileData.json");

		try {
			dataload.loadJsonData();
			logger.info("Fichier chargé avec succès.");
			//Data data = new Data();
			//System.out.println("data" + data);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("Problème lors du lancement de l'application" + e);
			e.printStackTrace();
		}
		
		
		SpringApplication.run(SafetinetApplication.class, args);
		run(args);
	}
	
		
	public static void run(String... args) {
    }
    

}

