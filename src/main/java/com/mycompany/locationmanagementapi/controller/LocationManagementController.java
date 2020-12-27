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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
	public ResponseEntity<Location> getLocationDetal(@PathVariable("id") Long id ){
		Optional<Location> locationOpt = null;
		try{	
			locationOpt = locationRepository.findById(id);
			if(!locationOpt.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		}catch(Exception ex){
			return new ResponseEntity<Location>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Location>(locationOpt.get(),HttpStatus.OK);
	}
	
	@PostMapping("/locations")
	public ResponseEntity<Location> createLocation(@RequestBody Location location){
		Location loc = null;
		try{
			loc = locationRepository.save(location);
		}catch(Exception ex){
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(loc, HttpStatus.OK);
	}
	
	@PutMapping("/locations")
	public ResponseEntity<Location> updateLocation(@RequestBody Location location){
		Optional<Location> locationInDbOpt = locationRepository.findById(location.getId());
		Location location1 = null;
		try{
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
				location1 = locationRepository.save(locationInDbOpt.get());
				return new ResponseEntity<Location>(location1,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
		}catch(Exception ex){
			return new ResponseEntity<Location>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/locations/{id}")
	public ResponseEntity<Location> deleteLocation(@PathVariable Long id){
		Optional<Location> locationInDbOpt = null;
		try{
			locationInDbOpt = locationRepository.findById(id);
			if(locationInDbOpt.isPresent()){
				locationRepository.deleteById(id);
				return new ResponseEntity<>(locationInDbOpt.get(), HttpStatus.NO_CONTENT);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception ex){
			
			return new ResponseEntity<Location>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
