package com.petCart.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="supplier_detail")
public class SupplierDetail {

	@Column
	@GeneratedValue @Id
	private Integer id;
	
	@Basic
	private String email;
	
	@Basic
	@Column(name="address_line1",length=65000)
	private String addressLine1;

	
	@Basic
	@Column(name="address_line2",length=100)
	private String addressLine2;

	
	@Basic
	@Column(length=45)
	private String city;
	
	
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="image_id")
	private Files image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Files getImage() {
		return image;
	}

	public void setImage(Files image) {
		this.image = image;
	}

	
	
	
	
}
