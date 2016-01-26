package com.petCart.model;




import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@NamedQueries( {
@NamedQuery(name="findItemByItemId",query="Select i From CartItem i where itemId=:itemId and cartId=:cartId"),
@NamedQuery(name="findItemsByCartId",query="Select i From CartItem i where cartId=:cartId"),

})
@Entity
@Table(name="cart_item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartItem  implements Serializable{

	
	private static final long serialVersionUID = 1L;


	public CartItem() {
		super();
	}


	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
     
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "item_id")
    private Product itemId;
    
	
    @Column(name="quantity")
    private int quantity;
    
	@Basic
	@Column(name="buy_now")
	private Boolean buyNow;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonIgnore
	@JoinColumn(name="cart_id", nullable=false)
	private Cart cartId;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Boolean getBuyNow() {
		return buyNow;
	}


	public void setBuyNow(Boolean buyNow) {
		this.buyNow = buyNow;
	}


	public Cart getCartId() {
		return cartId;
	}


	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}


	public Product getItemId() {
		return itemId;
	}


	public void setItemId(Product itemId) {
		this.itemId = itemId;
	}


	
	
	
}
