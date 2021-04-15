package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PS000V02DetailInfo {

	// ID
	private BigDecimal id;

	// 倉庫名
	private String storegeName;

	// カテゴリー名
	private String categoryName;

	// 備品名
	private String goodsName;

	// 入庫者
	private String userName;

	// 入庫日
	private LocalDate stReceipt;

	// 入庫数
	private BigDecimal stQuantity;

	// 入庫単位名
	private String stUnitName;

	// 入庫理由
	private String stReason;

	// 入庫ステータス
	private String stStatus;

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

	public LocalDate getStReceipt() {
		return stReceipt;
	}

	public void setStReceipt(LocalDate stReceipt) {
		this.stReceipt = stReceipt;
	}

	public BigDecimal getStQuantity() {
		return stQuantity;
	}

	public void setStQuantity(BigDecimal stQuantity) {
		this.stQuantity = stQuantity;
	}

	public String getStUnitName() {
		return stUnitName;
	}

	public void setStUnitName(String stUnitName) {
		this.stUnitName = stUnitName;
	}

	public String getStReason() {
		return stReason;
	}

	public void setStReason(String stReason) {
		this.stReason = stReason;
	}

	public String getStStatus() {
		return stStatus;
	}

	public void setStStatus(String stStatus) {
		this.stStatus = stStatus;
	}

}