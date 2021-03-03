package com.mycompany.locationmanagementapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.mycompany.locationmanagementapi.dto.LocationDTO;
import com.mycompany.locationmanagementapi.entity.Location;
import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.service.LocationManagementService;

@RestController
@RequestMapping("/lma/v1")
public class LocationManagementController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationManagementController.class);
	
	@Autowired
	private LocationManagementService locationManagementService;
	
	@GetMapping("/locations")
	public ResponseEntity<List<Location>> getAllLoctions(){
		//LOGGER.info("getAllLoctions function made a change");
		System.out.println("my change");
		System.out.println("adding new printline in getAllLocations in git");
		System.out.println("adding new printline in getAllLocations in git");
		System.out.println("adding new printline in getAllLocations in git");
		System.out.println("adding new printline in getAllLocations in git");
		List<Location> locations = null;
		locations = locationManagementService.getAllLoctions();
		if(locations != null & locations.isEmpty()){
			return new ResponseEntity<List<Location>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Location>>(locations , HttpStatus.OK);
		}
		
   	}
	
	@GetMapping("/locations/{id}")
	public ResponseEntity<Location> getLocationDetal(@PathVariable("id") Long id ){
		Optional<Location> locationOpt = null;
		//new changes
		//old change gone
		//new code added
		int b = 3;
		System.out.println(b);
			locationOpt = locationManagementService.getLocationDetail(id);
			if(!locationOpt.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else{
				return new ResponseEntity<Location>(locationOpt.get(),HttpStatus.OK);
			}
	}
	
	@PostMapping("/locations")
	public @ResponseBody ResponseEntity<Location> createLocation(@Valid @RequestBody LocationDTO locationDTO ){
		Location loc = null;  
		loc = locationManagementService.createLocation(locationDTO);
		return new ResponseEntity<Location>(loc, HttpStatus.OK);
	}
	
	@PutMapping("/locations")
	public ResponseEntity<Location> updateLocation(@Valid @RequestBody Location location)throws BusinessException{
		Location updatedLocation = locationManagementService.updateLocation(location);
		return new ResponseEntity<Location>(updatedLocation,HttpStatus.OK);			
	}
	
	@DeleteMapping("/locations/{id}")
	public ResponseEntity<Location> deleteLocation(@PathVariable Long id)throws BusinessException{
		Location location = locationManagementService.deleteLocation(id);
		return new ResponseEntity<Location>(location,HttpStatus.NO_CONTENT);
	}
}
