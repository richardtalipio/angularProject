package com.ally.hhnCalamba.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id", nullable = false)
	private Integer itemId;
	
	@Column(name = "item_name", nullable = false)
	private String itemName;
	
	@Column(name = "variant")
	private String variant;
	
	@Column(name = "item_category")
	private String itemCategory;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "stocks_left")
	private Integer stocksLeft;
	
	@Column(name = "dealers_discount")
	private Integer dealersDiscount;
	
	@Column(name = "date_created")
	private Date dateCreated;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItem = new HashSet<>();


	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getStocksLeft() {
		return stocksLeft;
	}

	public void setStocksLeft(Integer stocksLeft) {
		this.stocksLeft = stocksLeft;
	}

	public Integer getDealersDiscount() {
		return dealersDiscount;
	}

	public void setDealersDiscount(Integer dealersDiscount) {
		this.dealersDiscount = dealersDiscount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	
	
	
}
