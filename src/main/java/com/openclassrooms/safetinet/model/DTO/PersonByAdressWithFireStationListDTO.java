package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonByAdressWithFireStationListDTO {

	private String fireStation;

	private List <PersonByAdressWithFireStationDTO> personByAdressWithFireStationDTO;
	@JsonCreator
	public PersonByAdressWithFireStationListDTO(String fireStation, List<PersonByAdressWithFireStationDTO> personByAdressWithFireStationDTO) {
		this.fireStation = fireStation;
		this.personByAdressWithFireStationDTO = personByAdressWithFireStationDTO;
	}

}
