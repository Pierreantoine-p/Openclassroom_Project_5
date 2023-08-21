package com.openclassrooms.safetinet.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneByFireStationDTO {

	private String phone;
	

/*
	public static void add(PhoneByFireStationDTO phoneByFireStationDTO) {
		// TODO Auto-generated method stub
		
	}

	public void setPhone(List<Person> phoneList) {
		// TODO Auto-generated method stub
		
	}
	*/
	@JsonCreator
	public PhoneByFireStationDTO(String phone) {
		this.phone = phone ;
	}
	
}
