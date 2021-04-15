package com.cbs.form;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class IS000V01Info {

	// 地域コード
	private String areaID;

	// 地域コード
	private String areaName;

	// 備品数
	private BigDecimal goodsSum;

	// カテゴリー
	private String categoryCode;

	// カテゴリー名
	private String categoryName;

	// 備品名
	private String goodsName;

	// 備品code
	private String goodsCode;

	// 数式
	private BigDecimal amount;

	// 男性
	private BigDecimal man;

	// 女性
	private BigDecimal woman;

	// 老人
	private BigDecimal aged;

	// 外国籍
	private BigDecimal foreigners;

	// 障害者
	private BigDecimal disability;
	
	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public BigDecimal getGoodsSum() {
		return goodsSum;
	}

	public void setGoodsSum(BigDecimal goodsSum) {
		this.goodsSum = goodsSum;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getMan() {
		return man;
	}

	public void setMan(BigDecimal man) {
		this.man = man;
	}

	public BigDecimal getWoman() {
		return woman;
	}

	public void setWoman(BigDecimal woman) {
		this.woman = woman;
	}

	public BigDecimal getAged() {
		return aged;
	}

	public void setAged(BigDecimal aged) {
		this.aged = aged;
	}

	public BigDecimal getForeigners() {
		return foreigners;
	}

	public void setForeigners(BigDecimal foreigners) {
		this.foreigners = foreigners;
	}

	public BigDecimal getDisability() {
		return disability;
	}

	public void setDisability(BigDecimal disability) {
		this.disability = disability;
	}
}
