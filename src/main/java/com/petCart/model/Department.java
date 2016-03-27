package com.petCart.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;



@NamedQueries( {
@NamedQuery(name="findDepartmentById",query="Select d From Department d where id=:id"),
@NamedQuery(name="findAllDepartment",query="Select d From Department d"),

})


@Entity
@Table(name="department")
public class Department implements Serializable {


	
	private static final long serialVersionUID = 1L;
	
	@Basic
	@Id
    @GeneratedValue
	@Column
	private Integer id;
	
	@Basic
	@Column(name="name")
    private String name;

	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Category> categories;
	
	public Department() {
		super();
	}
	
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
