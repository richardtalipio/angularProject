package com.ally.hhnCalamba.model;

import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="customer")
public class Customer {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id", nullable = false)
	private Integer customerId;
	
	@Column(name = "customer_name", nullable = false)
	private String customerName;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "latest_delivery_date")
	private String latestDeliveryDate;
	
	@OneToMany(mappedBy="customer")
    private Set<Order> orders;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public boolean containsPending() {
		Optional<Order> optional = orders.stream()
                .filter(x -> "pending".equals(x.getOrderStatus()))
                .findFirst();
		return optional.isPresent();
	}
	
	public boolean hasNoPending() {
		Optional<Order> optional = orders.stream()
                .filter(x -> "pending".equals(x.getOrderStatus()))
                .findFirst();
		return !optional.isPresent();
	}

	public String getLatestDeliveryDate() {
		return latestDeliveryDate;
	}

	public void setLatestDeliveryDate(String latestDeliveryDate) {
		this.latestDeliveryDate = latestDeliveryDate;
	}
	
	
	
	
}
