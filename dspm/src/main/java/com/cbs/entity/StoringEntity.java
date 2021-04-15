package com.cbs.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StoringEntity {

	//id
	private BigDecimal id;

	//倉庫コード
	private String storegeCode;

	//カテゴリー
	private String categoryCode;

	//備品コード
	private String goodsCode;

	//担当者コード
	private String userCode;

	//入庫予定日
	private LocalDate stScheduled;

	//入庫日
	private LocalDate stReceipt;

	//賞味／消費期限
	private LocalDate stExpiration;

	//LOT/シリアルNO
	private String serialNumber;

	//単価
	private BigDecimal stPrice;

	//入庫数
	private BigDecimal stQuantity;

	//入庫単位
	private String stUnit;

	//金額
	private BigDecimal stAmount;

	//入庫理由
	private String stReason;

	//入庫ステータス
	private String stStatus;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public LocalDate getStScheduled() {
		return stScheduled;
	}

	public void setStScheduled(LocalDate stScheduled) {
		this.stScheduled = stScheduled;
	}

	public LocalDate getStReceipt() {
		return stReceipt;
	}

	public void setStReceipt(LocalDate stReceipt) {
		this.stReceipt = stReceipt;
	}

	public LocalDate getStExpiration() {
		return stExpiration;
	}

	public void setStExpiration(LocalDate stExpiration) {
		this.stExpiration = stExpiration;
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

	public BigDecimal getStQuantity() {
		return stQuantity;
	}

	public void setStQuantity(BigDecimal stQuantity) {
		this.stQuantity = stQuantity;
	}

	public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
	}

	public BigDecimal getStAmount() {
		return stAmount;
	}

	public void setStAmount(BigDecimal stAmount) {
		this.stAmount = stAmount;
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

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

}