package com.hr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "location_master")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int location_id;
	private String cities;
	public Location() {
		super();
		
	}
	public Location(int location_id, String cities) {
		super();
		this.location_id = location_id;
		this.cities = cities;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getCities() {
		return cities;
	}
	public void setCities(String cities) {
		this.cities = cities;
	}
	@Override
	public String toString() {
		return "Location [location_id=" + location_id + ", cities=" + cities + "]";
	}
	
}
