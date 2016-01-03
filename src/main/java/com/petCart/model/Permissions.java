package com.petCart.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="permissions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permissions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(nullable=false, length=200)
	private String description;

	/** The permissionid. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long permissionid;

	/** The permissionname. */
	@Basic
	@Column(nullable=false, length=50)
	@Enumerated(EnumType.STRING)
	private userPermission permissionname;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(Long permissionid) {
		this.permissionid = permissionid;
	}

	public userPermission getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(userPermission userPermission) {
		this.permissionname = userPermission;
	}

	
}
