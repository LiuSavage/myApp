package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SI000V02DetailInfo {

	//ID
	private BigDecimal id;

	//倉庫名
	private String storegeName;

	//備品
	private String goodsName;

	//棚卸担当者
	private String sheUser;

	//棚卸開始日
	private LocalDate starInvertory;

	//棚卸終了日
	private LocalDate endInvertory;

	//在庫数
	private BigDecimal stockNumber;

	//棚卸数
	private BigDecimal invertNumber;

	//ステータス
	private String shedStatus;

	//棚卸結果
	private String shedResult;

	public String getStoregeName() {
		return storegeName;
	}

	public void setStoregeName(String storegeName) {
		this.storegeName = storegeName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSheUser() {
		return sheUser;
	}

	public void setSheUser(String sheUser) {
		this.sheUser = sheUser;
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

	public BigDecimal getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(BigDecimal stockNumber) {
		this.stockNumber = stockNumber;
	}

	public BigDecimal getInvertNumber() {
		return invertNumber;
	}

	public void setInvertNumber(BigDecimal invertNumber) {
		this.invertNumber = invertNumber;
	}

	public String getShedResult() {
		return shedResult;
	}

	public void setShedResult(String shedResult) {
		this.shedResult = shedResult;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getShedStatus() {
		return shedStatus;
	}

	public void setShedStatus(String shedStatus) {
		this.shedStatus = shedStatus;
	}

}