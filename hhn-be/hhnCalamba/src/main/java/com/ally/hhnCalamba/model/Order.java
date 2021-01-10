package com.ally.hhnCalamba.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.sun.istack.NotNull;

@Entity
@Table(name="ordertb")
public class Order {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", nullable = false)
	private Integer orderId;
	
	@Column(name = "price_less")
	private float priceLess;
	
	@Column(name = "discount")
	private float discount;
	
	@Column(name = "grand_total")
	private float grandTotal;
	
	@Column(name = "profit")
	private float profit;
	
	@Column(name = "delivery_date")
	private Date deliveryDate;
	
	@Column(name = "is_delivered")
	private boolean isDelivered;
	
	@Column(name = "is_paid")
	private boolean isPaid;
	
	@Column(name = "delivery_method")
	private String deliveryMethod;
	
	@Column(name = "date_ordered")
	private Date dateOrdered;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;
	
	@ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

	public Integer getOrdersId() {
		return orderId;
	}

	public void setOrdersId(Integer ordersId) {
		this.orderId = ordersId;
	}

	public float getPriceLess() {
		return priceLess;
	}

	public void setPriceLess(float priceLess) {
		this.priceLess = priceLess;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String isDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public Date getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	
	
	
}
