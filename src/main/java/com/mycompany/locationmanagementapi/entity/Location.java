package com.mycompany.locationmanagementapi.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)		
	private long id;
	private String type;
	private String lat;
	private String lon;

	@ManyToMany
	@JoinTable(name = "location_service", joinColumns = @JoinColumn(name = "location_iid") , 
	inverseJoinColumns = @JoinColumn(name = "service_iid"))
	private Set<ServiceEntity> service;
	
	@ManyToOne
	private UserEntity userEntity;
	
	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@OneToOne
	private AddressEntity addressEntity;
	
	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

	public Set<ServiceEntity> getService() {
		return service;
	}

	public void setService(Set<ServiceEntity> service) {
		this.service = service;
	}

	public Location() {
		super();
	}

	/*public Location(long id, String type, String lat, String lon) {
		super();
		this.id = id;
		this.type = type;
		this.lat = lat;
		this.lon = lon;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

}
