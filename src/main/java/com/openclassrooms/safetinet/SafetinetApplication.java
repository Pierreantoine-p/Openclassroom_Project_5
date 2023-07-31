package com.openclassrooms.safetinet;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.openclassrooms.safetinet"})
public class SafetinetApplication {
	
  
//TODO logger entrer et parametre
	
	public static void main(String[] args) {
		/*
		logger.info("L'application a démarré avec succès.");
		DataLoader dataload = new DataLoader("FileData.json");

		try {
			dataload.loadJsonData();
			logger.info("Fichier chargé avec succès.");


		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("Problème lors du lancement de l'application" + e);
			e.printStackTrace();
		}
		*/
		
		
		SpringApplication.run(SafetinetApplication.class, args);
	
	}
	
		/*
	public static void run(String... args) {
    }
    */

}
