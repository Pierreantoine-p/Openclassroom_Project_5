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

import com.openclassrooms.safetinet.model.MedicalRecordsModel;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.service.MedicalRecordsService;
import com.openclassrooms.safetinet.service.PersonsService;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordsController {

	/*
	private MedicalRecordsService medicalRecordsService;
	
	@Autowired
	public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
		this.medicalRecordsService = medicalRecordsService;
	}
	
	@PostMapping
	public void save(@RequestBody Persons persons) throws IOException{
		medicalRecordsService.save(persons);
	}
	/*
	@GetMapping("/{id}")
	public Persons findById (@PathVariable int id) throws IOException{
		return medicalRecordsService.findById(id);
	}
	
	@GetMapping
	public List<Persons> findAll()throws IOException{
		return medicalRecordsService.findAll();
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById (@PathVariable int id){
		medicalRecordsService.deleteById;
	}
	
	
	@PutMapping("/{id}")
	public void replacePersons (@RequestBody Persons newMedicalRecords, @PathVariable int id){
		newMedicalRecords.setId(id);
	
	}
	*/
	
}



	
			