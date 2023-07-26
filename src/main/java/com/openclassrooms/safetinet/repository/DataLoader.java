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
	    System.out.println("path 1");

		DataWrapper data = null;
		InputStream inputStream = null; 

		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(this.filePath);
			//String json = objectMapper.writeValueAsString(inputStream);
		    System.out.println("inputStream = " + inputStream);

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
	    System.out.println("data = " + data);

		return data;
	} 
	
	/*
    private final String filePath;
	private static final  ObjectMapper objectMapper = new ObjectMapper();
	
	public DataLoader(String filePath) {
        this.filePath = filePath;
    }
	
	  public void loadJsonData() throws IOException {

		  InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileData.json");
		  //Logger.INFO
			try {
				if (inputStream != null) {
			
			        Data data = objectMapper.readValue(inputStream, Data.class);

			       
			    } else {
			        throw new IOException("Le fichier n'existe pas.");
			    }
			}
			catch(IOException e){
		        System.out.println(e);
	
			}finally {
				inputStream.close();
			}
	        
	    } 
	  */

}  
