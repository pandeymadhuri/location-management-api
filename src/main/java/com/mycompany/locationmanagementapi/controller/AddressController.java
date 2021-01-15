package com.mycompany.locationmanagementapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.locationmanagementapi.entity.AddressEntity;
import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.service.AddressService;
import com.mycompany.locationmanagementapi.service.AddressServiceImpl;

@RestController
public class AddressController {
	@Autowired
	private AddressServiceImpl addressServiceImpl;
	
	@GetMapping("/addresses/{id}")
	public ResponseEntity<AddressEntity> getAddress(@PathVariable Long id) throws BusinessException{
		AddressEntity addressEntity = addressServiceImpl.getAddress(id);
		return new ResponseEntity<AddressEntity>(addressEntity, HttpStatus.OK);
	}
	 
	@GetMapping("/addresses")
	public ResponseEntity<List<AddressEntity>> getAllAddress(){
		List<AddressEntity> addrList = addressServiceImpl.getAllAddresses();
		return new ResponseEntity<List<AddressEntity>>(addrList,HttpStatus.OK);
	}
	
	@PostMapping("/addresses")
	public ResponseEntity<AddressEntity> saveAddress(@RequestBody AddressEntity addr){
		AddressEntity addrEntity = addressServiceImpl.save(addr);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/addresses")
	public void updateAddress(){}
	
	@DeleteMapping("addresses")
	public void deleteAddress(){}
}
