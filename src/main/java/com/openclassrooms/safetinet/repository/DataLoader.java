package com.openclassrooms.safetinet.repository;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetinet.data.*;

public class DataLoader {
	
	private static final  ObjectMapper objectMapper = new ObjectMapper();
	private final String filePath;
	
	public DataLoader(String filePath) {
		this.filePath = filePath;
	}

	public DataWrapper loadJsonData() {

		DataWrapper data = null;
		InputStream inputStream = null; 

		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(this.filePath);
			data = objectMapper.readValue(inputStream, DataWrapper.class);
		} catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(inputStream != null) inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	} 
	
	

}  
