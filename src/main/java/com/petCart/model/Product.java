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
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@NamedQueries({
@NamedQuery(name="findProductById",query="Select p From Product p where id=:id"),
@NamedQuery(name="findAllProduct",query="Select p From Product p"),
@NamedQuery(name="viewProduct", query="select p from Product p where id=:id")
 })


@Entity
@Table(name="product")
@Audited
public class Product  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}
	
	@Basic
	@Column
	@GeneratedValue 
	@Id
    private Long id;
	
	@Basic
	@Column(name="name")
	private String Name;
	
    @Basic
    @Column(name="mrp_price")
	private Double MRPPrice;
	
    @Basic
    @Column(name="price")
	private Double Price;
	
    @Basic
    @Column(name="offer_price")
	private Double OfferPrice;
	
    
    @Basic
	@Column(name="quantity")
	private Integer Quantity;
	
    @Basic
	@Column(name="ps_show")
	private Boolean showItem;
	
	
    @Basic
	@Column(name="ps_desc",columnDefinition = "TEXT")
    private String description;
	
    
	@Temporal(TemporalType.DATE)
	@Column(name="created_time")
	private Date CreatedTime;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_time")
	private Date ModifiedTime;
	
	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dept_id")
	private Department department;
    
	@JsonIgnore
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cat_id")
	private Category category;

	@JsonIgnore
	@NotAudited
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonManagedReference("product-review")
	private List<Review> reviews;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@NotAudited
	private List<Files> images;
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="supplier_id")
	@NotAudited
	private Supplier supplier;
	
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	
	public Double getOfferPrice() {
		return OfferPrice;
	}

	public Double getMRPPrice() {
		return MRPPrice;
	}

	public void setMRPPrice(Double mRPPrice) {
		MRPPrice = mRPPrice;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public Boolean getShowItem() {
		return showItem;
	}

	public void setShowItem(Boolean showItem) {
		this.showItem = showItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Date getCreatedTime() {
		return CreatedTime;
	}

	public void setCreatedTime(Date createdTime) {
		CreatedTime = createdTime;
	}

	public Date getModifiedTime() {
		return ModifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		ModifiedTime = modifiedTime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setOfferPrice(Double offerPrice) {
		OfferPrice = offerPrice;
	}

	

	public List<Files> getImages() {
		return images;
	}

	public void setImages(List<Files> images) {
		this.images = images;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
	
}
