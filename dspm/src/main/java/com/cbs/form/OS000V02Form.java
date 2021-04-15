package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OS000V02Form extends Form {

	// 画面区分
	private String kbn;

	// ID
	private BigDecimal id;

	// 倉庫名
	private String storegeCode;

	// 倉庫名リスト
	private Map<String, String> storegeList;

	// 出庫ステータス
	private String stockingStatus;

	// ステータスリスト
	private Map<String, String> codeList;

	// カテゴリー
	private String categoryCode;

	// カテゴリーリスト
	private Map<String, String> categoryCodeList;

	// 備品
	private String goodsCode;

	// 備品リスト
	private Map<String, String> goodsList;

	// 出庫開始日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stockingDateFrom;

	// 出庫終了日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stockingDateEnd;

	// 担当者コード
	private String userCode;

	// 担当者リスト
	private Map<String, String> userCodeList;

	// 出庫リスト
	private List<OS000V02DetailInfo> stockingList;

	public String getKbn() {
		return kbn;
	}

	public void setKbn(String kbn) {
		this.kbn = kbn;
	}

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

	public String getStockingStatus() {
		return stockingStatus;
	}

	public void setStockingStatus(String stockingStatus) {
		this.stockingStatus = stockingStatus;
	}

	public Map<String, String> getCodeList() {
		return codeList;
	}

	public void setCodeList(Map<String, String> codeList) {
		this.codeList = codeList;
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

	public LocalDate getStockingDateFrom() {
		return stockingDateFrom;
	}

	public void setStockingDateFrom(LocalDate stockingDateFrom) {
		this.stockingDateFrom = stockingDateFrom;
	}

	public LocalDate getStockingDateEnd() {
		return stockingDateEnd;
	}

	public void setStockingDateEnd(LocalDate stockingDateEnd) {
		this.stockingDateEnd = stockingDateEnd;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Map<String, String> getUserCodeList() {
		return userCodeList;
	}

	public void setUserCodeList(Map<String, String> userCodeList) {
		this.userCodeList = userCodeList;
	}

	public List<OS000V02DetailInfo> getStockingList() {
		return stockingList;
	}

	public void setStockingList(List<OS000V02DetailInfo> stockingList) {
		this.stockingList = stockingList;
	}

}
