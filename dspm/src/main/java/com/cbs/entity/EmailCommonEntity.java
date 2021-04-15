package com.cbs.entity;

import java.math.BigDecimal;

public class EmailCommonEntity {

	// 倉庫名
	private String storegeName;

	// 備品名
	private String goodsName;

	// 期限切れ
	private BigDecimal discardDays;

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

	public BigDecimal getDiscardDays() {
		return discardDays;
	}

	public void setDiscardDays(BigDecimal discardDays) {
		this.discardDays = discardDays;
	}

}