package com.openclassrooms.safetinet.repository;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.data.Data;

public class DataLoader {
	
    private final String filePath;
	private static final  ObjectMapper objectMapper = new ObjectMapper();
	
	public DataLoader(String filePath) {
        this.filePath = filePath;
    }
	
	
	  public void loadJsonData() throws IOException {
	        File file = new File("Data.json");
	        if (file.exists()) {
	            Data data = objectMapper.readValue(file, Data.class);
	        } else {
	            throw new IOException("Le fichier n'existe pas.");
	        }
	    }
}
