package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonByFirstNameAndLastNameDTO {

	private String lastName;

	private String address;

	private Integer age;

	private String email;

	private List<String> medications;

	private List<String> allergies;

	@JsonCreator
	public PersonByFirstNameAndLastNameDTO(String lastName, String address, Integer age, String email, List<String> medications, List<String> allergies) {
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;

	}




}
