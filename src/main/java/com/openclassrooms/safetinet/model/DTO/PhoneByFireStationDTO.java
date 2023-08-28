package com.openclassrooms.safetinet.model.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneByFireStationDTO {

	private String phone;

	@JsonCreator
	public PhoneByFireStationDTO(String phone) {
		this.phone = phone ;
	}
	
}
