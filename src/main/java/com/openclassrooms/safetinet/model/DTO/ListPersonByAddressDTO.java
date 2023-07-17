package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

public class ListPersonByAddressDTO {

	public List<ChildByAddressDTO> getChildByAddressDTO() {
		return ChildByAddressDTO;
	}

	public void setChildByAddressDTO(List<ChildByAddressDTO> childByAddressDTO) {
		ChildByAddressDTO = childByAddressDTO;
	}

	public List<ChildByAddressDTO> getAdultByAddressDTO() {
		return AdultByAddressDTO;
	}

	public void setAdultByAddressDTO(List<ChildByAddressDTO> adultByAddressDTO) {
		AdultByAddressDTO = adultByAddressDTO;
	}

	private List<ChildByAddressDTO> ChildByAddressDTO;
	
	private List<ChildByAddressDTO> AdultByAddressDTO;

}
