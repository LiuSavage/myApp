package com.cbs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StockEntity implements Serializable {

	//ID
	private BigDecimal id;

	//倉庫コード
	private String storegeCode;

	//カテゴリー
	private String categoryCode;

	//備品code
	private String goodsCode;

	//在庫数
	private BigDecimal stockNumber;

	//在庫単位
	private String stUnit;

	//賞味／消費期限
	private LocalDate stExpiration;

	//LOT/シリアルNO
	private String serialNumber;

	//単価
	private BigDecimal stPrice;

	//金額
	private BigDecimal stAmount;

	//在庫ステータス
	private String stockStatus;

	//備考
	private String stComment;
	
	// 写真
	private String photo;

	//削除フラグ
	private BigDecimal delFlg;

	//登録日
	private LocalDateTime insertDate;

	//登録者
	private String insertUser;

	//更新日
	private LocalDateTime updateDate;

	//更新者
	private String updateUser;

	private static final long serialVersionUID = 1L;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigDecimal getStPrice() {
		return stPrice;
	}

	public void setStPrice(BigDecimal stPrice) {
		this.stPrice = stPrice;
	}

	public BigDecimal getStAmount() {
		return stAmount;
	}

	public void setStAmount(BigDecimal stAmount) {
		this.stAmount = stAmount;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getStComment() {
		return stComment;
	}

	public void setStComment(String stComment) {
		this.stComment = stComment;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public BigDecimal getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(BigDecimal stockNumber) {
		this.stockNumber = stockNumber;
	}

	public LocalDate getStExpiration() {
		return stExpiration;
	}

	public void setStExpiration(LocalDate stExpiration) {
		this.stExpiration = stExpiration;
	}

	public BigDecimal getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(BigDecimal delFlg) {
		this.delFlg = delFlg;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}