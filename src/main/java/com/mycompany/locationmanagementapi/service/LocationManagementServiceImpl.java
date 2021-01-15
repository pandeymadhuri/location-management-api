package com.mycompany.locationmanagementapi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mycompany.locationmanagementapi.dto.LatLonApiRespWrapperDTO;
import com.mycompany.locationmanagementapi.dto.LocationDTO;
import com.mycompany.locationmanagementapi.entity.AddressEntity;
import com.mycompany.locationmanagementapi.entity.Location;
import com.mycompany.locationmanagementapi.entity.ServiceEntity;
import com.mycompany.locationmanagementapi.entity.UserEntity;
import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.exception.ErrorModel;
import com.mycompany.locationmanagementapi.repository.AddressRepository;
import com.mycompany.locationmanagementapi.repository.LocationRepository;
import com.mycompany.locationmanagementapi.repository.ServiceRepository;
import com.mycompany.locationmanagementapi.repository.UserEntityRepository;

@Service
public class LocationManagementServiceImpl implements LocationManagementService{
	
	private static final Logger logger = LoggerFactory.getLogger(LocationManagementServiceImpl.class);

	@Value("${apiBaseUrl}")
	private String apiBaseUrl;
	
	@Value("${apiKey}")
	private String apiKey;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Override
	public List<Location> getAllLoctions() {
		return locationRepository.findAll();
	}

	@Override
	public Optional<Location> getLocationDetail(Long id) {
 		return locationRepository.findById(id);
	}

	@Override
	public Location createLocation(LocationDTO locationDTO) {
		
		
		List<Long> serviceIds = locationDTO.getServiceIds();
		Set<ServiceEntity> serviceEntities = new HashSet<>();
		
		for(Long sId:serviceIds){
			Optional<ServiceEntity> service = serviceRepository.findById(sId);
			if(service.isPresent()){
				serviceEntities.add(service.get());
			}
		}
		
		Location location = new Location();
		location.setType(locationDTO.getType());
		location.setService(serviceEntities);
		
		Optional<UserEntity> userEntityOpt = userEntityRepository.findById(locationDTO.getUserId());
		if(userEntityOpt.isPresent()){
			location.setUserEntity(userEntityOpt.get());
		}
		Long addressId = locationDTO.getAddressId();
		
		Optional<AddressEntity> addrEntityOpt = addressRepository.findById(addressId);
		if(addrEntityOpt.isPresent()){
			//call to Api to fetch address latitude longitude
			RestTemplate restTemplate = new RestTemplate();
			StringBuilder sb = new StringBuilder();
			sb.append(apiBaseUrl);
			sb.append("/v1/forward?access_key=");
			sb.append(apiKey);
			sb.append("&query=");
			
			sb.append(addrEntityOpt.get().getLocality());
			//sb.append('"');
			//sb.append(" ");
			//sb.append(addrEntityOpt.get().getState());
			
			//String uri = "http://api.positionstack.com/v1/forward?access_key=a9d8787055b9f16bfad814b6e4330f51&query="+addrEntityOpt.get().getLocality()+" "+addrEntityOpt.get().getState();
 			LatLonApiRespWrapperDTO latLonApiRespWrapperDTO = null;
			try{
				 latLonApiRespWrapperDTO = restTemplate.getForObject(sb.toString(), LatLonApiRespWrapperDTO.class);
				 if(latLonApiRespWrapperDTO !=null){
					 location.setLat(latLonApiRespWrapperDTO.getData().get(0).getLatitude().toString());
					 location.setLon(latLonApiRespWrapperDTO.getData().get(0).getLongitude().toString());
				 }
			}catch(Exception ex){
				logger.error("Error occured while fetching the Latitude nd Longitude Details with error "+ex.getMessage());
			}
			location.setAddressEntity(addrEntityOpt.get());
		}
		return locationRepository.save(location);
	}
	
	@Transactional
	@Override
	public Location updateLocation(Location location) throws BusinessException {
		Optional<Location> locationInDbOpt = locationRepository.findById(location.getId());
		Location location1 = null;
			if(locationInDbOpt.isPresent()){
				if(location.getLat() != null){
					locationInDbOpt.get().setLat(location.getLat());
				}
				if(location.getLon() != null){
					locationInDbOpt.get().setLon(location.getLon());
				}
				if(location.getType() != null){
					locationInDbOpt.get().setType(location.getType());
				}
				location1 = locationRepository.save(locationInDbOpt.get());
			}else {
				ErrorModel err = new ErrorModel(HttpStatus.NOT_FOUND.value(), "Element with id "+location.getId()+" not found");
				throw new BusinessException(err);
			}
			return location1;
	}
	
	@Transactional
	@Override
	public Location deleteLocation(Long id) throws BusinessException {
		Optional<Location> locationOpt = null;
		locationOpt = locationRepository.findById(id);
		if(locationOpt.isPresent()){
			locationRepository.deleteById(id);
		}else{
			ErrorModel err = new ErrorModel(HttpStatus.NOT_FOUND.value(), "Location with id "+id+" not found");
			throw new BusinessException(err);
		}
		return locationOpt.get();
	}
}
