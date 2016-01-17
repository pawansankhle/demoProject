package com.petCart.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;


@NamedQueries( {
@NamedQuery(name="findUserByName",query="Select u From Users u where username=:username"),
})
@XmlRootElement(name="Users")
@Entity
@Table(name = "users")
@Audited
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;

	
	public Users() {
	}
	
	public Users(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Users(String username, String password, 
		boolean enabled, Set<Roles> Roles) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = Roles;
	}
	
	
	@Basic
	@Column
	@GeneratedValue
	@Id
    private long id;
	
	@Basic 
	@Column(name="username")
	private String username;
	
	
	@JsonIgnore
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Basic 
	@Column(name="address_line1")
	private String addressLine1;
	
	@Basic 
	@Column(name="address_line2")
	private String addressLine2;
	
	
	@Basic 
	@Column(name="dilevery_address")
	private String deliveryAddress;
	
	@Basic 
	@Column(name="city")
	private String city;
	
	@Basic 
	@Column(name="postal_code")
	private String postalCode;
	
	@Basic 
	@Column(name="mobile")
	private String mobile;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="image_id")
	@NotAudited
	private Files image;
	
	@ManyToMany(targetEntity=com.petCart.model.Roles.class,cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="userrole", joinColumns=@JoinColumn(name="userid"), inverseJoinColumns=@JoinColumn(name="roleid"))
	@NotAudited
	private Set<Roles> roles = new HashSet<Roles>();
	

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Files getImage() {
		return image;
	}

	public void setImage(Files image) {
		this.image = image;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	

	

	
	

}
