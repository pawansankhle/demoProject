package com.petCart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.envers.Audited;


@XmlRootElement(name="Orders")
@Entity
@Table(name = "orders")
@Audited
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	@GeneratedValue @Id
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private User customer;
	
	@Basic
	@Column(name="bill_number")
	private String billNumber;
	
	@Basic
	@Column(name="total_ammount")
	private Double totalAmount;
	
	@Basic
	@Column(name="ref_id")
	private Integer referenceId;
	
	@Basic
	@Column(name="created_on")
	private Date createdOn;
	
	@Basic
	@Column(name="updated_on")
	private Date updatedOn;
	
	@Basic
	@Column(name="shipped_on")
	private Date shippedOn;
	
	@Basic
	@Column(name="shipping_charge")
	private Double shippingCharge;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Basic
	@Column(name="admin_comment",columnDefinition = "TEXT")
    private String adminComment;
	
	@Basic
	@Column(name="supplier_comment",columnDefinition = "TEXT")
    private String supplierComment;
	
	@Basic
	@Column(name="customer_notified")
	private boolean customerNotified;
	
	@Basic
	@Column(name="delivery_date")
	private Date deliveryDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getShippedOn() {
		return shippedOn;
	}

	public void setShippedOn(Date shippedOn) {
		this.shippedOn = shippedOn;
	}

	public Double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(Double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}

	public String getSupplierComment() {
		return supplierComment;
	}

	public void setSupplierComment(String supplierComment) {
		this.supplierComment = supplierComment;
	}

	public boolean isCustomerNotified() {
		return customerNotified;
	}

	public void setCustomerNotified(boolean customerNotified) {
		this.customerNotified = customerNotified;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
	
	


}
