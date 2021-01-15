package com.mycompany.locationmanagementapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.locationmanagementapi.entity.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long>{

}
