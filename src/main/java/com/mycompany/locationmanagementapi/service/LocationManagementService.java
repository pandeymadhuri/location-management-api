package com.mycompany.locationmanagementapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.mycompany.locationmanagementapi.dto.LocationDTO;
import com.mycompany.locationmanagementapi.entity.Location;
import com.mycompany.locationmanagementapi.exception.BusinessException;

public interface LocationManagementService {

	public List<Location> getAllLoctions();
	public Optional<Location> getLocationDetail(Long id);
	public Location createLocation(LocationDTO locationDTO);
	public Location updateLocation(Location location)throws BusinessException;
	public Location deleteLocation(Long id)throws BusinessException;
}
