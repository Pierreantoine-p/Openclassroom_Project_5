package com.openclassrooms.safetinet.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetinet.model.FireStationsModel;
import com.openclassrooms.safetinet.model.MedicalRecordsModel;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.FireStationsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
@RequestMapping("/firestation")
public class FireStationsController {
	private FireStationsService fireStationsService;
	/*
	@Autowired
	public FireStationsController(FireStationsService fireStationsService) {
		this.fireStationsService = fireStationsService;
	}
	

	@PostMapping
	public void save(@RequestBody FireStation fireStation) throws IOException{
		fireStationsService.save(fireStation);
	}
	
	@GetMapping("/{id}")
	public Persons findById (@PathVariable int id) throws IOException{
		return fireStationsService.findById(id);
	}
	
	@GetMapping
	public List<Persons> findAll()throws IOException{
		return fireStationsService.findAll();
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById (@PathVariable int id){
		fireStationsService.deleteById;
	}
	
	
	@PutMapping("/{id}")
	public void replacePersons (@RequestBody Persons newPersons, @PathVariable int id){
		newPersons.setId(id);
	}
	*/
}
