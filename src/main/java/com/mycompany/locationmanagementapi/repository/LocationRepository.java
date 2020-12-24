package com.mycompany.locationmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.locationmanagementapi.entity.Location;

public interface LocationRepository extends JpaRepository<Location ,Long>{

}
