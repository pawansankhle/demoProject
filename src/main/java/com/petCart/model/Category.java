/*
 * category class to add category of various Dept
 * 
 */
package com.petCart.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;


@NamedQueries( {
@NamedQuery(name="findCategoryById",query="Select c From Category c where id=:id"),
@NamedQuery(name="findAllCategory",query="Select c From Category c")

})


@Entity
@Table(name="category")
public class Category implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;

	public Category() {
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
	
	
	@ManyToOne
	@JoinColumn(name="dept_id")
	@JsonBackReference("category-department")
	private Department department;
	
	@Basic
	@Column(name="created_time")
	@Temporal(TemporalType.DATE)
	private Date CreatedTime;
	
	@Basic
	@Column(name="modified_time")
	@Temporal(TemporalType.DATE)
	private Date ModifiedTime;
	
	
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
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

  }
