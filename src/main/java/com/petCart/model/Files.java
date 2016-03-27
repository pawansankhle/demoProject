package com.petCart.model;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@NamedQueries({
@NamedQuery(name="findFileById",query="Select f From Files f where id=:id"),

})
@Entity
@Table(name="file_upload")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Files {

	
	
	@Column(name="fileid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Integer id;
	
	@Basic
	@Column(length=250)
	private String filename;
	
	
	@Basic
	@Column(length=250)
	private String file;

	@Temporal(TemporalType.DATE)
	@Column(name="created_time")
	private Date createdtime;
	
	@Temporal(TemporalType.DATE)
	@Column(name="modified_time")
	private Date modifiedtime;
	
	


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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
	
	
}
