package com.mycompany.locationmanagementapi.service;

import com.mycompany.locationmanagementapi.entity.AddressEntity;
import com.mycompany.locationmanagementapi.exception.BusinessException;

public interface AddressService {
	public AddressEntity getAddress(Long id) throws BusinessException;
}
