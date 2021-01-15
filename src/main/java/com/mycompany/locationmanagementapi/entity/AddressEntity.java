package com.mycompany.locationmanagementapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long plot_no;
	private String street;
	private String locality;
	private String state;
	
	public AddressEntity(){}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPlot_no() {
		return plot_no;
	}
	public void setPlot_no(long plot_no) {
		this.plot_no = plot_no;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
