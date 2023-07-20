package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

public class AddressByStationDTO {
	
	private String Station;
	
	public String getStation() {
		return Station;
	}

	public void setStation(String station) {
		Station = station;
	}
	
	private List<AddressPersonByStationDTO> addressPersonByStationDTO;

	public List<AddressPersonByStationDTO> getAddressPersonByStationDTO() {
		return addressPersonByStationDTO;
	}

	public void setAddressPersonByStationDTO(List<AddressPersonByStationDTO> addressPersonByStationDTO) {
		this.addressPersonByStationDTO = addressPersonByStationDTO;
	}

}
		

