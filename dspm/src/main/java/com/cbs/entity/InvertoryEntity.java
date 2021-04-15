package com.cbs.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InvertoryEntity {

	//ID
	private BigDecimal id;

	//倉庫コード
	private String storegeCode;

	//カテゴリー
	private String categoryCode;

	//備品コード
	private String goodsCode;

	//担当者コード
	private String userCode;

	//在庫単位
	private String stUnit;

	//賞味／消費期限
	private LocalDate stExpiration;

	//単価
	private BigDecimal stPrice;

	//棚卸開始日
	private LocalDate starInvertory;

	//棚卸終了日
	private LocalDate endInvertory;

	//棚卸数
	private BigDecimal invertNumber;

	//ステータス
	private String status;

	//備考
	private String comment;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
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

	public LocalDate getStarInvertory() {
		return starInvertory;
	}

	public void setStarInvertory(LocalDate starInvertory) {
		this.starInvertory = starInvertory;
	}

	public LocalDate getEndInvertory() {
		return endInvertory;
	}

	public void setEndInvertory(LocalDate endInvertory) {
		this.endInvertory = endInvertory;
	}

	public BigDecimal getInvertNumber() {
		return invertNumber;
	}

	public void setInvertNumber(BigDecimal invertNumber) {
		this.invertNumber = invertNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
}