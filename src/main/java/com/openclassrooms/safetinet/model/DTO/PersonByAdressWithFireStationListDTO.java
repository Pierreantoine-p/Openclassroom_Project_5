package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

public class PersonByAdressWithFireStationListDTO {

	private String fireStation;
	
	private List <PersonByAdressWithFireStationDTO> personByAdressWithFireStationDTO;

	public List <PersonByAdressWithFireStationDTO> getPersonByAdressWithFireStationDTO() {
		return personByAdressWithFireStationDTO;
	}

	public void setPersonByAdressWithFireStationDTO(List <PersonByAdressWithFireStationDTO> personByAdressWithFireStationDTO) {
		this.personByAdressWithFireStationDTO = personByAdressWithFireStationDTO;
	}

	public String getFireStation() {
		return fireStation;
	}

	public void setFireStation(String fireStation) {
		this.fireStation = fireStation;
	}
	
}
