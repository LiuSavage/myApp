package com.cbs.form;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class HP000V01Info {
	
	//id
	private Integer id;
	
	//更新日
	private String updateDate;
	
	//更新者ID
	private String updateUser;
	
	//更新者名
	private String userName;
	
	//初期化URL
	private String targetUrl;
	
	//地域コード
	private String areaID;
	
	//地域名
	private String areaName;
	
	//市区町村
	private String cityName;
		
	//備品数
	private String goodsSum;
	
	// カテゴリー
	private String categoryCode;
	
	//カテゴリー名
	private String categoryName;
	
	//備品名
	private String goodsName;
	
	//備品code
	private String goodsCode;
	
	//数式
	private BigDecimal amount;
	
	//男性
	private BigDecimal man;
	
	//女性
	private BigDecimal woman;
	
	//老人
	private BigDecimal aged;
	
	//外国籍
	private BigDecimal foreigners;
	
	//障害者
	private BigDecimal disability;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGoodsSum() {
		return goodsSum;
	}

	public void setGoodsSum(String goodsSum) {
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
