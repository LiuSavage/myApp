package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PS000V02Form extends Form {

	// 画面区分
	private String kbn;

	// ID
	private BigDecimal id;

	// 倉庫名
	private String storegeCode;

	// 倉庫名リスト
	private Map<String, String> storegeList;

	// ステータス
	private String stStatus;

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

	// 入庫日FROM
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate storingDateFrom;

	// 入庫日TO
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate storingDateEnd;

	// 担当者コード
	private String userCode;

	// 担当者リスト
	private Map<String, String> userCodeList;

	// 入庫リスト
	private List<PS000V02DetailInfo> storingList;

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

	public String getStStatus() {
		return stStatus;
	}

	public void setStStatus(String stStatus) {
		this.stStatus = stStatus;
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

	public LocalDate getStoringDateFrom() {
		return storingDateFrom;
	}

	public void setStoringDateFrom(LocalDate storingDateFrom) {
		this.storingDateFrom = storingDateFrom;
	}

	public LocalDate getStoringDateEnd() {
		return storingDateEnd;
	}

	public void setStoringDateEnd(LocalDate storingDateEnd) {
		this.storingDateEnd = storingDateEnd;
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

	public List<PS000V02DetailInfo> getStoringList() {
		return storingList;
	}

	public void setStoringList(List<PS000V02DetailInfo> storingList) {
		this.storingList = storingList;
	}

}
