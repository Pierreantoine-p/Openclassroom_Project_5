package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListPersonByAddressDTO {

	private List<ChildByAddressDTO> childByAddressDTO;

	private List<ChildByAddressDTO> adultByAddressDTO;

	@JsonCreator
	public ListPersonByAddressDTO(@JsonProperty("childByAddressDTO")List<ChildByAddressDTO> childByAddressDTO, @JsonProperty("adultByAddressDTO")List<ChildByAddressDTO> adultByAddressDTO) {
		this.childByAddressDTO = childByAddressDTO;
		this .adultByAddressDTO = adultByAddressDTO;
	}
}
