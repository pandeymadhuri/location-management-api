package com.mycompany.locationmanagementapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.locationmanagementapi.entity.Location;
import com.mycompany.locationmanagementapi.repository.LocationRepository;

@RestController
@RequestMapping("/lma/v1")
public class LocationManagementController {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping("/locations")
	public ResponseEntity<List<Location>> getAllLoctions(){
		List<Location> locations = null;
		try {
			locations = locationRepository.findAll();
            if (locations != null && locations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
		
	}
	
	@GetMapping("/locations/{id}")
	public Location getLocationDetal(@PathVariable("id") Long id ){
		System.out.println("getLocationDetal");
		Optional<Location> locationOpt = locationRepository.findById(id);
		if(locationOpt.isPresent()){
			return locationOpt.get();
		}
		
		return null;
	}
	
	@PostMapping("/locations")
	public void createLocation(@RequestBody Location location){
		System.out.println("createLocation");
		locationRepository.save(location);
	}
	
	@PutMapping("/locations")
	public void updateLocation(@RequestBody Location location){
		System.out.println("updateLocation");
		Optional<Location> locationInDbOpt = locationRepository.findById(location.getId());
		if(locationInDbOpt.isPresent()){
			if(location.getLat() != null){
				locationInDbOpt.get().setLat(location.getLat());
			}
			if(location.getLon() != null){
				locationInDbOpt.get().setLon(location.getLon());
			}
			if(location.getType() != null){
				locationInDbOpt.get().setType(location.getType());
			}
		}
	}
	
	@DeleteMapping("/locations/{id}")
	public void deleteLocation(@PathVariable Long id){
		System.out.println("deleteLocation");
		locationRepository.deleteById(id);
	}
}
