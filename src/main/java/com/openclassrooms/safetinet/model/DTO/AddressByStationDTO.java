package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressByStationDTO {

	private String station;
	private List<AddressPersonByStationDTO> addressPersonByStationDTO;
	
	@JsonCreator
	public AddressByStationDTO(@JsonProperty("station")String station, @JsonProperty("addressPersonByStationDTO")List<AddressPersonByStationDTO> addressPersonByStationDTO) {
		this.station = station;	
		this.addressPersonByStationDTO = addressPersonByStationDTO;

	}


}


