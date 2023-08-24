package com.openclassrooms.safetinet.model.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressPersonByStationDTO {

	private List<HouseholdByStationDTO> householdByStationDTO;

	private String address;


	@JsonCreator
	public AddressPersonByStationDTO (@JsonProperty("address")String address,@JsonProperty("householdByStationDTO")  List<HouseholdByStationDTO> householdByStationDTO) {
		this.address=address;
		this.householdByStationDTO = householdByStationDTO;
	}

}
