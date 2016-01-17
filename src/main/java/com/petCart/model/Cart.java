package com.petCart.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.Fetch;





@NamedQueries( {
@NamedQuery(name="findCartByItemId",query="Select c From Cart c where itemId=:itemId"),
@NamedQuery(name="findCartById",query="Select c From Cart c where id=:id"),
@NamedQuery(name="findCartByName",query="Select c From Cart c where name=:name"),
})

@Entity
@Table(name="cart")
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Cart(){
		super();
	}

	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Integer id;
	
	@JsonIgnore
	@Column(name="name")  
    private String name;
     
    
	@JsonIgnore
	@Basic
	@Column(name="added_on")
	@Temporal(TemporalType.DATE)
	private Date addedOn;
	
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name="modified_on")
	private Date modifiedOn;
	
	
	@OneToMany(mappedBy="cartId",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<CartItem> items;
	
	@Column(name="total")
    private double total;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public List<CartItem> getItems() {
		return items;
	}


	public void setItems(List<CartItem> items) {
		this.items = items;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "Cart [id=" + id + ", name=" + name + ", addedOn=" + addedOn
				+ ", modifiedOn=" + modifiedOn + ", items=" + items
				+ ", total=" + total + "]";
	}
	
	
	

	
	
	
	
	
}
