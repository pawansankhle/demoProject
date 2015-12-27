package com.petCart.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="file_upload")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Files {

	
	@Basic
	@Column
	@GeneratedValue 
	@Id
    private long id;
	
	@Basic
	@Column(length=250)
	private String filename;
	
	
	@Basic
	@Column(length=250, nullable=false)
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
