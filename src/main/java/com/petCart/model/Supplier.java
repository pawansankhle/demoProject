package com.petCart.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@NamedQueries( {
     @NamedQuery(name="findAllSuppliers",query="Select s From Supplier s where s.deleted=false"),
     @NamedQuery(name="findSupplierByDetailId",query="Select s From Supplier s where s.deleted=false and detail=:detail"),
})

@Entity
@Table(name="supplier")
public class Supplier {
	
	@Column
	@GeneratedValue @Id
	private long id;
	
	
	@Basic
	private Boolean deleted;
	
	@Basic
    private String shopname;
	
	
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="detail_id")
	private Users detail;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

   public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	public String getShopname() {
		return shopname;
	}


	public void setShopname(String shopname) {
		this.shopname = shopname;
	}


	public Users getDetail() {
		return detail;
	}


	public void setDetail(Users detail) {
		this.detail = detail;
	}


}
