package com.petCart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;



@Entity
@Table(name="cart")
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Cart(){
		super();
	}

	@Basic
	@Column(name="item_id")
	@GeneratedValue
	@Id
	private Integer itemId;
	
	@Basic
	@Column(name="cart_id")
	private String cartId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product productId;
	
	@Basic
	@Column(name="product_name")
	private String productName;
	
	@Basic
	@Column(name="price")
	private Double price;
	
	@Basic
	@Column(name="quantity")
	private String quantity;
	
	
	@Basic
	@Column(name="buy_now")
	private Boolean buyNow;
	
	@Basic
	@Column(name="added_on")
	@Temporal(TemporalType.DATE)
	private Date addedOn;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_on")
	private Date modifiedOn;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Boolean getBuyNow() {
		return buyNow;
	}

	public void setBuyNow(Boolean buyNow) {
		this.buyNow = buyNow;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	
	
	
}
