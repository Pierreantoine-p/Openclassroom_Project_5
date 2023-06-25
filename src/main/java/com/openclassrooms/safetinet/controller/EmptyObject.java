package com.openclassrooms.safetinet.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openclassrooms.safetinet.model.FireStations;

public class EmptyObject {
	
	public static ResponseEntity<FireStations> getEmptyObject() throws IOException {
		try {
			FireStations emptyObject = new FireStations();
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(emptyObject);
	        System.out.println(ResponseEntity.ok().body(emptyObject));

			return ResponseEntity.ok().body(emptyObject);
		}catch(Exception e){
			// logger.error
	        System.out.println("Exception" + e);
		    throw new IOException();
		}
	}
/*
	@JsonSerialize
	public class EmptyJsonBody {
	}
	*/
}
