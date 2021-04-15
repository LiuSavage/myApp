package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SL000V01DetailInfo {

	// ID
	private BigDecimal id;

	// 倉庫名
	private String storegeName;

	// カテゴリー名
	private String categoryName;

	// 備品名
	private String goodsName;

	// 賞味期限
	private LocalDate stExpiration;

	// 在庫数
	private BigDecimal stockNumber;

	// 在庫単位名
	private String stockUnitName;

	// 期限切れ
	private BigDecimal discardDays;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getStoregeName() {
		return storegeName;
	}

	public void setStoregeName(String storegeName) {
		this.storegeName = storegeName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public LocalDate getStExpiration() {
		return stExpiration;
	}

	public void setStExpiration(LocalDate stExpiration) {
		this.stExpiration = stExpiration;
	}

	public BigDecimal getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(BigDecimal stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getStockUnitName() {
		return stockUnitName;
	}

	public void setStockUnitName(String stockUnitName) {
		this.stockUnitName = stockUnitName;
	}

	public BigDecimal getDiscardDays() {
		return discardDays;
	}

	public void setDiscardDays(BigDecimal discardDays) {
		this.discardDays = discardDays;
	}

}