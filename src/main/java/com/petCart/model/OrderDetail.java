package com.petCart.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.envers.Audited;

@Entity
@Table(name="order_detail")
@Audited
public class OrderDetail implements Serializable {

	
	public OrderDetail() {
		super();
	}
     private static final long serialVersionUID = 1L;



	@Basic
	@Column
	@GeneratedValue 
	@Id
    private Integer id;
	
	
	
	/*private Cart itemId;   many to many relationship*/ 
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="order_id")
	private Orders order;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="product_id")
	private Product productId;
	
	
	@Column
	private String attributes;
	
	@Basic
	@Column(name="quantity")
	private Integer quantity;
	
	@Basic
	@Column(name="unit_cost")
	private Double unitCost;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders orders) {
		this.order = orders;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
		
}
