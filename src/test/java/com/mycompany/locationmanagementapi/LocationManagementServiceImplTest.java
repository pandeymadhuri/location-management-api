package com.mycompany.locationmanagementapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.locationmanagementapi.entity.Location;
import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.repository.LocationRepository;
import com.mycompany.locationmanagementapi.service.LocationManagementServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class LocationManagementServiceImplTest {
	@Mock
	private LocationRepository locationRepository;
	
	@InjectMocks
	private LocationManagementServiceImpl locationManagementService = new LocationManagementServiceImpl();
	
	private List<Location> locList;
			
	@Before
	public void setUp(){

		 this.locList=new ArrayList<Location>();
		 
//		 Location location = new Location (1L,"atm","20123.56","61.567");
//	 	 this.locList.add(location);
//	 	 location = new Location (2L,"atm","20123.56","61.567");
//	 	 this.locList.add(location);
//	 	 location = new Location (3L,"atm","20123.56","61.567");
//	 	 this.locList.add(location);
	}
	
	@Test
	//@Ignore
	public void test_LocationDetail_When_Location_Found(){
		when(locationRepository.findById(1L)).thenReturn(Optional.of(this.locList.get(0)));
		Optional<Location> locOpt = locationManagementService.getLocationDetail(1L);
		assertEquals(1L, locOpt.get().getId());
		assertEquals("atm", locOpt.get().getType());
	}
	
	@Test
	//@Ignore
	public void test_LocationDetail_When_Location_NotFound(){
		
		when(locationRepository.findById(2L)).thenReturn(Optional.empty());
		Optional<Location> locOpt = locationManagementService.getLocationDetail(2L);
		
		assertEquals(Optional.empty(), locOpt);
	}
	
	@Test
	//@Ignore
	public void test_getAllLoctions_found(){
		
		when(locationRepository.findAll()).thenReturn(this.locList);
		List<Location> locationsListActual = locationManagementService.getAllLoctions();
		assertEquals(locList.size(), locationsListActual.size());
		assertEquals(locList.get(0).getId(), locationsListActual.get(0).getId());
	}
	
	@Test
	//@Ignore
	public void test_getAllLocations_NotFound(){
		
		when(locationRepository.findAll()).thenReturn(null);
		List<Location> locationsListActual = locationManagementService.getAllLoctions();
		assertEquals(null, locationsListActual);
	}
	
	@Test
	//@Ignore
	public void createLocationTest(){
		when(locationRepository.save(this.locList.get(0))).thenReturn(this.locList.get(0));
		//Location locationActual = locationManagementService.createLocation(this.locList.get(0));
		//assertEquals(this.locList.get(0), locationActual);
	}
	
	@Test
	//@Ignore
	public void test_updateLocation_locationFound() throws BusinessException{
		
		this.locList.get(0).setType("ATM");
		this.locList.get(0).setLat("112.11");
		this.locList.get(0).setLon("8878.90");
		
		when(locationRepository.findById(this.locList.get(0).getId())).thenReturn(Optional.of(this.locList.get(0)));
		when(locationRepository.save(this.locList.get(0))).thenReturn(this.locList.get(0));
		
		Location location = locationManagementService.updateLocation(this.locList.get(0));
		
		assertEquals("ATM", location.getType());
		assertEquals("112.11", location.getLat());
		assertEquals("8878.90", location.getLon());
	}
	
	@Test(expected = BusinessException.class)
	//@Ignore
	public void test_updateLocation_locationNotFound() throws BusinessException{
		
		this.locList.get(0).setId(989L);
		this.locList.get(0).setType("ATM");
		this.locList.get(0).setLat("112.11");
		this.locList.get(0).setLon("8878.90");
		
		when(locationRepository.findById(this.locList.get(0).getId())).thenReturn(Optional.empty());
		
		Location location = locationManagementService.updateLocation(this.locList.get(0));
		
	}
	@Test
	//@Ignore
	public void test_deleteLocation_when_Location_found() throws BusinessException{
		when(locationRepository.findById(1L)).thenReturn(Optional.of(locList.get(0)));
		Location loc=locationManagementService.deleteLocation(1L);
		assertEquals(1L, loc.getId());
	}
	
	@Test(expected = BusinessException.class)
	//@Ignore
	public void test_deleteLocation_when_Location_notFound() throws BusinessException{
		when(locationRepository.findById(1L)).thenReturn(Optional.empty());
		Location loc=locationManagementService.deleteLocation(1L);
	}
}
