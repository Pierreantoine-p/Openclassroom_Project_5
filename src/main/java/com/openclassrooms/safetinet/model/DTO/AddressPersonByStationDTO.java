package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

public class AddressPersonByStationDTO {

	private List<HouseholdByStationDTO> householdByStationDTO;

	private String Address;

	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}


	public List<HouseholdByStationDTO> getHouseholdByStationDTO() {
		return householdByStationDTO;
	}

	public void setHouseholdByStationDTO(List<HouseholdByStationDTO> householdByStationDTO) {
		this.householdByStationDTO = householdByStationDTO;
	}

}
