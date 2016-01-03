package com.petCart.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="file_upload")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Files {

	
	
	@Column(name="fileid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	@Basic
	@Column(length=250)
	private String filename;
	
	
	@Basic
	@Column(length=250)
	private String file;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


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
	
	
}
