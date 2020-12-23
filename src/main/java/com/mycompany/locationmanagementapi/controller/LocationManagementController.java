package com.mycompany.locationmanagementapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lma/v1")
public class LocationManagementController {
	
	@GetMapping("/locations")
	public void getAllLoctions(){
		System.out.println("getAllLocations");
	}
	
	@GetMapping("/locations/{id}")
	public void getLocationDetal(){
		System.out.println("getLocationDetal");
	}
	
	@PostMapping("/locations")
	public void createLocation(){
		System.out.println("createLocation");
	}
	
	@PutMapping("/locations")
	public void updateLocation(){
		System.out.println("updateLocation");
	}
	
	@DeleteMapping("/locations")
	public void deleteLocation(){
		System.out.println("deleteLocation");
	}
}
