package com.petCart.model;

import java.io.Serializable;

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
@Table(name="rating")
public class Rating  implements Serializable{

	public Rating() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="pid")
	@JsonBackReference("rating-product")
	private Product product;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
	
	
}