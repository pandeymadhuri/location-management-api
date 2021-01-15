package com.mycompany.locationmanagementapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.locationmanagementapi.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long>{

}
