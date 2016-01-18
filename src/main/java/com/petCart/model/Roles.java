package com.petCart.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;


@NamedQueries( {
@NamedQuery(name="findRoleByName",query="Select r From Roles r where rollName=:rollName"),

})
@Entity
@Table(name = "roles")
public class Roles implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleid;
 
	@Column(name="rolename")
	@Enumerated(EnumType.STRING)
	private userRoles rollName;
	 
	@Basic
	@Column(nullable=false, length=200)
	private String description;
	
	@JsonIgnore
	@ManyToMany(targetEntity=com.petCart.model.Permissions.class, fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="rolepermission", joinColumns=@JoinColumn(name="roleid"), inverseJoinColumns=@JoinColumn(name="permissionid"))
	private Set<Permissions> permissions = new HashSet<Permissions>();


	public Long getRoleid() {
		return roleid;
	}


	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}


	public userRoles getRollName() {
		return rollName;
	}


	public void setRollName(userRoles rollName) {
		this.rollName = rollName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Permissions> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permissions> permissions) {
		this.permissions = permissions;
	}


	@Override
	public String toString() {
		return "Roles [roleid=" + roleid + ", rollName=" + rollName
				+ ", description=" + description + ", permissions="
				+ permissions + "]";
	}


	
	
	
}
