package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailBycity {

	private List<String> email;

	
	@JsonCreator
	public EmailBycity(@JsonProperty("email")List<String> email) {
		this.email = email;
	}
}
