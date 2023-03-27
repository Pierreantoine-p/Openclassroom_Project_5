package com.openclassrooms.safetinet;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.openclassrooms.safetinet.data.Data;
import com.openclassrooms.safetinet.repository.DataLoader;

@SpringBootApplication
public class SafetinetApplication {

	public static void main(String[] args) {
		DataLoader dataload = new DataLoader("Data.json");
		try {
			dataload.loadJsonData();
			System.out.println(Data.getPersons());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpringApplication.run(SafetinetApplication.class, args);
	}
	
	public static void run(String... args) {
        System.out.println("HelloWorld !");
    }

}
