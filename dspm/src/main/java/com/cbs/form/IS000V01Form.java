package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class IS000V01Form extends Form {

	//ID
	private BigDecimal id;

	// 倉庫名
	private String storegeCode;

	// 倉庫名リスト
	private Map<String, String> storegeList;

	// 在庫状況
	private String stockStatus;

	// 在庫状況リスト
	private Map<String, String> stockStatusList;

	// カテゴリー
	private String categoryCode;

	// カテゴリーリスト
	private Map<String, String> categoryCodeList;

	// 備品
	private String goodsCode;

	// 備品リスト
	private Map<String, String> goodsList;

	// 賞味期限開始日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationFrom;

	// 賞味期限終了日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationEnd;

	//在庫リスト
	private List<IS000V01DetailInfo> stockList;

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

	public Map<String, String> getStoregeList() {
		return storegeList;
	}

	public void setStoregeList(Map<String, String> storegeList) {
		this.storegeList = storegeList;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public Map<String, String> getStockStatusList() {
		return stockStatusList;
	}

	public void setStockStatusList(Map<String, String> stockStatusList) {
		this.stockStatusList = stockStatusList;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Map<String, String> getCategoryCodeList() {
		return categoryCodeList;
	}

	public void setCategoryCodeList(Map<String, String> categoryCodeList) {
		this.categoryCodeList = categoryCodeList;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Map<String, String> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(Map<String, String> goodsList) {
		this.goodsList = goodsList;
	}

	public LocalDate getExpirationFrom() {
		return expirationFrom;
	}

	public void setExpirationFrom(LocalDate expirationFrom) {
		this.expirationFrom = expirationFrom;
	}

	public LocalDate getExpirationEnd() {
		return expirationEnd;
	}

	public void setExpirationEnd(LocalDate expirationEnd) {
		this.expirationEnd = expirationEnd;
	}

	public List<IS000V01DetailInfo> getStockList() {
		return stockList;
	}

	public void setStockList(List<IS000V01DetailInfo> stockList) {
		this.stockList = stockList;
	}
}
