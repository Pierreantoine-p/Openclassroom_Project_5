package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

public class PersonByStationWithCountDTO {

	private List< PersonByStationDTO> personByStationDTO;
	private int countAdult;
	private int countChild;
	
	public int getCountAdult() {
		return countAdult;
	}
	public void setCountAdult(int countAdult) {
		this.countAdult = countAdult;
	}
	public int getCountChild() {
		return countChild;
	}
	public void setCountChild(int countChild) {
		this.countChild = countChild;
	}
	public List<PersonByStationDTO> getPersonByStationDTO() {
		return personByStationDTO;
	}
	public void setPersonByStationDTO(List<PersonByStationDTO> personByStationDTO) {
		this.personByStationDTO = personByStationDTO;
	}
	public static void add(List<PersonByStationDTO> personByStationDTOList) {
		// TODO Auto-generated method stub
		
	}
	
}
