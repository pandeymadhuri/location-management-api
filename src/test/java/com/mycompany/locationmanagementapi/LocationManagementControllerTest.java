package com.mycompany.locationmanagementapi;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mycompany.locationmanagementapi.controller.LocationManagementController;
import com.mycompany.locationmanagementapi.entity.Location;
import com.mycompany.locationmanagementapi.service.LocationManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
public class LocationManagementControllerTest {

	@InjectMocks
	private LocationManagementController  locationManagementController= 
	new LocationManagementController();
	
	@MockBean
	private LocationManagementService locationManagementService;

	ResponseEntity<List<Location>> loc;
	List<Location> locations;
	
	@Before
	public void setUp(){
	locations = new ArrayList<Location>();
	locations.add(new Location (1L,"atm","20123.56","61.567"));
	locations.add(new Location (2L,"atm","20123.56","61.567"));
	locations.add(new Location (3L,"atm","20123.56","61.567"));
	}
	
	@Test
	public void test_getAllLocations(){
		when(locationManagementService.getAllLoctions()).thenReturn(locations);
		loc = locationManagementController.getAllLoctions();
		//assertEquals
	}
}
