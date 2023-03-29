package com.openclassrooms.safetinet.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.data.*;

public class DataLoader {
	
    private final String filePath;
	private static final  ObjectMapper objectMapper = new ObjectMapper();
	
	public DataLoader(String filePath) {
        this.filePath = filePath;
    }
	
	
	  public void loadJsonData() throws IOException {
		  InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileData.json");
	        if (inputStream != null) {
	            Data data = objectMapper.readValue(inputStream, Data.class);
	        } else {
	            throw new IOException("Le fichier n'existe pas.");
	        }
	    }
}
