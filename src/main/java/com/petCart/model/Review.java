package com.petCart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.envers.Audited;

@NamedQueries
({
	@NamedQuery(name="Review.ratingbyProductId",query="select r.rating from Review r where product=:product"),
	@NamedQuery(name="Review.byUseraAndProduct",query="select r from Review r where r.customer=:customer and r.product=:product"),
	

})

@Entity
@Table(name="review")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	@JsonBackReference("review-product")
	@JoinColumn(name="pid")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Users customer;
	
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

	public Users getCustomer() {
		return customer;
	}

	public void setCustomer(Users customer) {
		this.customer = customer;
	}

		
}
