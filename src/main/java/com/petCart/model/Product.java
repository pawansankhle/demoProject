package com.petCart.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@NamedQueries( {
@NamedQuery(name="findProductById",query="Select p From Product p where id=:id"),
@NamedQuery(name="findAllProduct",query="Select p From Product p")

})

@XmlRootElement(name="Product")
@Entity
@Table(name="product")
@Audited
public class Product  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}
	
    private Integer id;
	
	private String Name;
	
    
	private Double MRPPrice;
	
    
	private Double Price;
	
    
	private Double OfferPrice;
	
    
	private Integer Quantity;
	
    
	private Boolean showItem;
	
	@Temporal(TemporalType.DATE)
	private Date CreatedTime;
	
	@Temporal(TemporalType.DATE)
	private Date ModifiedTime;
	
	
	
    private Department department;
    
    private Category category;

    @Basic
	@Column
	@GeneratedValue 
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic
	@Column(name="name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	

	@Basic
	@Column(name="price")
	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	@Basic
	@Column(name="offer_price")
	public Double getOfferPrice() {
		return OfferPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		OfferPrice = offerPrice;
	}

	@Basic
	@Column(name="quantity")
	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	
	@Basic
	@Column(name="show_item")
	public Boolean getShowItem() {
		return showItem;
	}

	public void setShowItem(Boolean showItem) {
		this.showItem = showItem;
	}

	
	@Basic
	@Column(name="created_time")
	public Date getCreatedTime() {
		return CreatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}

	@Basic
	@Column(name="modified_time")
	public Date getModifiedTime() {
		return ModifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		ModifiedTime = modifiedTime;
	}

	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dept_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Basic
	@Column(name="mrp_price")
	public Double getMRPPrice() {
		return MRPPrice;
	}

	public void setMRPPrice(Double mRPPrice) {
		MRPPrice = mRPPrice;
	}

	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cat_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
    
}
