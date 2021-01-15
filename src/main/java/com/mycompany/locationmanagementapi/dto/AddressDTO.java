package com.mycompany.locationmanagementapi.dto;

public class AddressDTO {

	private long plot_no;
	private String street;
	private String locality;
	private String state;
	
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
