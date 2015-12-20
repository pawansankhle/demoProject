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
@Table(name="supplier")
public class Supplier {
	
	@Column
	@GeneratedValue @Id
	private Integer id;
	
	@Basic
	private Boolean enabled;
	
	@Basic
	private Boolean deleted;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="supplier_detail_id")
	private User detailId;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

   

	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
		
	
}
