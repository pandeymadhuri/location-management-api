package com.mycompany.locationmanagementapi.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mycompany.locationmanagementapi.entity.AddressEntity;
import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.exception.ErrorModel;
import com.mycompany.locationmanagementapi.repository.AddressRepository;

@Service
public class AddressServiceImpl implements  AddressService  {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public AddressEntity getAddress(Long id) throws BusinessException{
		// TODO Auto-generated method stub
		Optional<AddressEntity> addr =  addressRepository.findById(id);
		if (addr.isPresent()){
			return addr.get();
		}else{
		
			ErrorModel err = new ErrorModel(HttpStatus.NOT_FOUND.value(),"Address with address id "+addr.get().getId());
			throw new BusinessException(err);
		}
	}
	public List<AddressEntity> getAllAddresses(){
		return (List<AddressEntity>) addressRepository.findAll();
	}
	
	public AddressEntity save(AddressEntity addr) {
		return addressRepository.save(addr);
	}
}
