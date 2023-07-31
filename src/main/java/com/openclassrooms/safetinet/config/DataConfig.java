package com.openclassrooms.safetinet.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.repository.DataLoader;


@Configuration
public class DataConfig {

	@Bean
	public DataWrapper dataWrapper() {
		DataLoader dataload = new DataLoader("FileData.json");
		return dataload.loadJsonData();
	}
}

/*
@Configuration
public class DataConfig {

	 private final DataLoader dataLoader;

	    @Autowired
	    public DataConfig(DataLoader dataLoader) {
	        this.dataLoader = dataLoader;
	    }
	    
	@Bean
	public DataWrapper data() {
		//DataLoader dataload = new DataLoader("FileData.json");
		

		return dataLoader.loadJsonData();
	}
	/*
	 * private static final Logger logger = LogManager.getLogger(DataConfig.class);
	 * public DataLoader(String filePath) { this.filePath = filePath; }
	 * 
	 * @Bean public DataPersons data() throws IOException { DataLoader dataLoader =
	 * new DataLoader(); dataLoader.loadJson(); DataPersons data =
	 * dataLoader.loadJson(); return data;
	 * 
	 * 
	 * DataLoader dataload = new DataLoader("FileData.json");
	 * 
	 * try { dataload.loadJsonData(); logger.info("Fichier chargé avec succès.");
	 * 
	 * 
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * logger.info("Problème lors du lancement de l'application" + e);
	 * e.printStackTrace(); }


}
*/