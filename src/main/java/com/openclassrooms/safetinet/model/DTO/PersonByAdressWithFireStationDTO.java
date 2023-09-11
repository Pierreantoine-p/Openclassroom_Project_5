package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonByAdressWithFireStationDTO {

	private String lastName;

	private String phone; 

	private int age;

	private List<String> medications;

	private List<String> allergies;

	@JsonCreator
	public PersonByAdressWithFireStationDTO(String lastName, String phone, int age, List<String> medications, List<String> allergies) {
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;

	}

}
