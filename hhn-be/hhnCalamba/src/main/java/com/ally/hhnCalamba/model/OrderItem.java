package com.ally.hhnCalamba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_item_id", nullable = false)
	private Integer orderItemId;

	@ManyToOne(fetch = FetchType.EAGER , optional = false)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.EAGER , optional = false)
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "is_freebie")
	private boolean isFreebie;

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isFreebie() {
		return isFreebie;
	}

	public void setFreebie(boolean isFreebie) {
		this.isFreebie = isFreebie;
	}
	
	

}
