package com.mycompany.locationmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.locationmanagementapi.entity.Location;
import com.mycompany.locationmanagementapi.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity ,Long>{

}
