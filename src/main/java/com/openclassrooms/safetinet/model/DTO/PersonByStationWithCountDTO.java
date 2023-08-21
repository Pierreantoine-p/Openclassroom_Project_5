package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonByStationWithCountDTO {

	private List< PersonByStationDTO> personByStationDTO;
	private int countAdult;
	private int countChild;
	
	@JsonCreator
	public PersonByStationWithCountDTO(@JsonProperty("personByStationDTO")List< PersonByStationDTO> personByStationDTO,@JsonProperty("countAdult")Integer countAdult,@JsonProperty("countChild")Integer countChild) {
		this.personByStationDTO = personByStationDTO;
		this.countAdult = countAdult;
		this.countChild = countChild;
	}	
}
