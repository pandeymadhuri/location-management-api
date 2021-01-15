package com.mycompany.locationmanagementapi.dto;

import java.util.List;

public class LatLonApiRespWrapperDTO {

	private List<LatLonApiResponseDTO> data;

	public List<LatLonApiResponseDTO> getData() {
		return data;
	}

	public void setData(List<LatLonApiResponseDTO> data) {
		this.data = data;
	}
}
