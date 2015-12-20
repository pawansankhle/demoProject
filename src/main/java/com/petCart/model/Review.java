package com.petCart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.envers.Audited;


@Entity
@Table(name="review")
public class Review  implements Serializable{

	public Review() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="pid")
	@JsonBackReference("review-product")
	private Product product;
	
	@Column(name="cusotmer_name")
	private String customerName;
	
	@Column(name="cusotmer_email")
	private String customerEamil;
	
	@Column(name="product_id")
	private String ProductId;
	
	@Column(name="review", columnDefinition = "TEXT")
	private String review;
	
	@Column
	private Integer rating;
	
	@Column(name="created_on")
	private Date createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEamil() {
		return customerEamil;
	}

	public void setCustomerEamil(String customerEamil) {
		this.customerEamil = customerEamil;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	
	
	
	
	
	
	
}
