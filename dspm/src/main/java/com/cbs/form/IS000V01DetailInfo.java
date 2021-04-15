package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IS000V01DetailInfo {

	//ID
	private BigDecimal id;

	//倉庫名
	private String storegeName;

	//カテゴリー名
	private String categoryName;

	//備品名
	private String goodsName;

	//在庫数
	private BigDecimal stockNumber;

	//在庫単位名
	private String stockUnitName;

	//単価
	private BigDecimal stPrice;

	//賞味／消費期限
	private LocalDate stExpiration;

	//在庫状況
	private String stockStatusSnap;

	//棚卸状況
	private String status;

	//倉庫コード
	private String storegeCode;

	//カテゴリーコード
	private String categoryCode;

	//備品コード
	private String goodsCode;

	//単位
	private String stUnit;

	//写真
	private String photo;

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

	public LocalDate getStExpiration() {
		return stExpiration;
	}

	public void setStExpiration(LocalDate stExpiration) {
		this.stExpiration = stExpiration;
	}

	public BigDecimal getStPrice() {
		return stPrice;
	}

	public void setStPrice(BigDecimal stPrice) {
		this.stPrice = stPrice;
	}

	public String getStockStatusSnap() {
		return stockStatusSnap;
	}

	public void setStockStatusSnap(String stockStatusSnap) {
		this.stockStatusSnap = stockStatusSnap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStoregeCode() {
		return storegeCode;
	}

	public void setStoregeCode(String storegeCode) {
		this.storegeCode = storegeCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}