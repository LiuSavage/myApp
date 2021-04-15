package com.cbs.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OS000V02DetailInfo implements Serializable {

	// ID
	private BigDecimal id;

	// 倉庫名
	private String storegeName;

	// カテゴリー名
	private String categoryName;

	// 備品名
	private String goodsName;

	// 担当者名
	private String userName;

	// 出庫日
	private LocalDate stockingReceipt;

	// 出庫数
	private BigDecimal stockingQuantity;

	// 出庫単位名
	private String stockingUnitName;

	// 出庫理由名
	private String stockingReasonName;

	// 出庫ステータス
	private String stockingStatus;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getStockingReceipt() {
		return stockingReceipt;
	}

	public void setStockingReceipt(LocalDate stockingReceipt) {
		this.stockingReceipt = stockingReceipt;
	}

	public BigDecimal getStockingQuantity() {
		return stockingQuantity;
	}

	public void setStockingQuantity(BigDecimal stockingQuantity) {
		this.stockingQuantity = stockingQuantity;
	}

	public String getStockingUnitName() {
		return stockingUnitName;
	}

	public void setStockingUnitName(String stockingUnitName) {
		this.stockingUnitName = stockingUnitName;
	}

	public String getStockingReasonName() {
		return stockingReasonName;
	}

	public void setStockingReasonName(String stockingReasonName) {
		this.stockingReasonName = stockingReasonName;
	}

	public String getStockingStatus() {
		return stockingStatus;
	}

	public void setStockingStatus(String stockingStatus) {
		this.stockingStatus = stockingStatus;
	}

}