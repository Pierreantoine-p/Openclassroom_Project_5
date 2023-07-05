package com.openclassrooms.safetinet.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.repository.PersonsRepository;

@Service
@Component
public class DTOService {
	
	private PersonsService personsService;
	
    public PersonsRepository personsRepository;

	
    private static final Logger logger = LogManager.getLogger(DTOService.class);

	public DTOService(PersonsRepository personsRepository) {
		this.personsRepository = personsRepository;
	}
	
	public DTOService(PersonsService personsService) {
		this.personsService = personsService;
	}
	
}
