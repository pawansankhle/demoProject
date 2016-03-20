package com.petCart.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Basic
	@Column
	@GeneratedValue 
	@Id
	private Integer id;
	
	
	@Basic
	@Column(name="addressLine1")
	private String addressLine1;
	
	@Basic
	@Column(name="addressLine2")
	private String addressLine2;
	
	@Basic
	@Column(name="shippingAddress")
	private String shippingAddress;
	
	@Basic
	@Column(name="state")
	private String state;
	
	@Basic
	@Column(name="city")
	private String city;
	
	@Basic
	@Column(name="pincode")
	private Integer pincode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
	
	
	

}
