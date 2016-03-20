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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}
	
	@Basic
	@Column
	@GeneratedValue 
	@Id
    private Integer id;
	
	@Basic
	@Column(name="name")
	private String name;
	
	@Basic
    @Column(name="price")
	private Double price;
	
    @Basic
    @Column(name="discount")
	private Double discount;
	
    
    @Basic
	@Column(name="quantity")
	private Integer quantity;
	
    @Basic
	@Column(name="ps_show")
	private Boolean showitem;
	
	
    @Basic
	@Column(name="ps_desc",columnDefinition = "TEXT")
    private String description;
	
    
	@Temporal(TemporalType.DATE)
	@Column(name="created_time")
	private Date createdtime;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_time")
	private Date modifiedtime;
	
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

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getShowitem() {
		return showitem;
	}

	public void setShowitem(Boolean showitem) {
		this.showitem = showitem;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Date getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	
	
}
