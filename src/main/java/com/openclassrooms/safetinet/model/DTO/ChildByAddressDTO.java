package com.openclassrooms.safetinet.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChildByAddressDTO {
	
	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	

	@JsonCreator
	public ChildByAddressDTO(@JsonProperty("firstName")String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("age") Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;

	}

	

}
