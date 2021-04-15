package com.cbs.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StockingEntity {

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

	//出庫予定日
	private LocalDate stockingScheduled;

	//出庫日
	private LocalDate stockingReceipt;

	//賞味／消費期限
	private LocalDate stExpiration;

	//lot/シリアルno
	private String serialNumber;

	//単価
	private BigDecimal stPrice;

	//出庫数
	private BigDecimal stockingQuantity;

	//出庫単位
	private String stockingUnit;

	//金額
	private BigDecimal stAmount;

	//出庫理由
	private String stockingReason;

	//出庫ステータス
	private String stockingStatus;

	//備考
	private String stComment;

	//削除フラグ
	private BigDecimal delFlg;

	//入庫id
	private BigDecimal storingId;

	//登録日
	private LocalDateTime insertDate;

	//登録者
	private String insertUser;

	//更新日
	private LocalDateTime updateDate;

	//更新者
	private String updateUser;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public LocalDate getStockingScheduled() {
		return stockingScheduled;
	}

	public void setStockingScheduled(LocalDate stockingScheduled) {
		this.stockingScheduled = stockingScheduled;
	}

	public LocalDate getStockingReceipt() {
		return stockingReceipt;
	}

	public void setStockingReceipt(LocalDate stockingReceipt) {
		this.stockingReceipt = stockingReceipt;
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

	public BigDecimal getStockingQuantity() {
		return stockingQuantity;
	}

	public void setStockingQuantity(BigDecimal stockingQuantity) {
		this.stockingQuantity = stockingQuantity;
	}

	public String getStockingUnit() {
		return stockingUnit;
	}

	public void setStockingUnit(String stockingUnit) {
		this.stockingUnit = stockingUnit;
	}

	public BigDecimal getStAmount() {
		return stAmount;
	}

	public void setStAmount(BigDecimal stAmount) {
		this.stAmount = stAmount;
	}

	public String getStockingReason() {
		return stockingReason;
	}

	public void setStockingReason(String stockingReason) {
		this.stockingReason = stockingReason;
	}

	public String getStockingStatus() {
		return stockingStatus;
	}

	public void setStockingStatus(String stockingStatus) {
		this.stockingStatus = stockingStatus;
	}

	public String getStComment() {
		return stComment;
	}

	public void setStComment(String stComment) {
		this.stComment = stComment;
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

	public BigDecimal getStoringId() {
		return storingId;
	}

	public void setStoringId(BigDecimal storingId) {
		this.storingId = storingId;
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
}