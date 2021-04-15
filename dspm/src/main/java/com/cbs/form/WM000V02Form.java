package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WM000V02Form extends Form {
	
	//No
	private BigDecimal id;

	//移動元倉庫名
	private String stockingStoregeName;
	
	//移動先倉庫名
	private String storingStoregeName;
	
	//カテゴリー名
	private String categoryName;
	
	//備品名
	private String goodsName;
	
	//移動担当者名
	private String userCode;

	//移動日
	private LocalDate stockingReceipt;

	//移動数
	private BigDecimal stockingQuantity;
	
	//ステータス
	private String stockingStatusName;

	//備考
	private String stComment;
	
	//ステータス
	private String stockingStatus;

	//移動元ID
	private BigDecimal stockingId;
	
	//移動先ID
	private BigDecimal storingId;

	// 移動元倉庫code
	private String storegeCode;

	// 移動先倉庫code
	private String storegeCode2;

	// カテゴリー
	private String categoryCode;

	// 備品code
	private String goodsCode;

	// 移動担当者code
	private String sheUser;
	
	// 移動日From
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate movingDayFrom;
	
	// 移動日To
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate movingDayTo;
	
	// ステータス
	private String status;
	
	// 倉庫名リスト
	private Map<String, String> storegeList;

	// カテゴリーリスト
	private Map<String, String> categoryList;

	// 備品リスト
	private Map<String, String> goodsList;

	// 担当者リスト
	private Map<String, String> sheUserList;
	
	// ステータスリスト
	private Map<String, String> statusList;
	
	private List<WM000V02Info> infoList;
	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getStockingStoregeName() {
		return stockingStoregeName;
	}

	public void setStockingStoregeName(String stockingStoregeName) {
		this.stockingStoregeName = stockingStoregeName;
	}

	public String getStoringStoregeName() {
		return storingStoregeName;
	}

	public void setStoringStoregeName(String storingStoregeName) {
		this.storingStoregeName = storingStoregeName;
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
	
	public String getStockingStatusName() {
		return stockingStatusName;
	}

	public void setStockingStatusName(String stockingStatusName) {
		this.stockingStatusName = stockingStatusName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public LocalDate getStockingReceipt() {
		return stockingReceipt;
	}

	public void setStockingReceipt(LocalDate stockingReceipt) {
		this.stockingReceipt = stockingReceipt;
	}

	public BigDecimal getStockingQuantity() {
		return stockingQuantity;
	}

	public void setStockingQuantity(BigDecimal stockingQuantity) {
		this.stockingQuantity = stockingQuantity;
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
	
	public BigDecimal getStockingId() {
		return stockingId;
	}

	public void setStockingId(BigDecimal stockingId) {
		this.stockingId = stockingId;
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

	public String getStoregeCode2() {
		return storegeCode2;
	}

	public void setStoregeCode2(String storegeCode2) {
		this.storegeCode2 = storegeCode2;
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

	public String getSheUser() {
		return sheUser;
	}

	public void setSheUser(String sheUser) {
		this.sheUser = sheUser;
	}

	public LocalDate getMovingDayFrom() {
		return movingDayFrom;
	}

	public void setMovingDayFrom(LocalDate movingDayFrom) {
		this.movingDayFrom = movingDayFrom;
	}

	public LocalDate getMovingDayTo() {
		return movingDayTo;
	}

	public void setMovingDayTo(LocalDate movingDayTo) {
		this.movingDayTo = movingDayTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, String> getStoregeList() {
		return storegeList;
	}

	public void setStoregeList(Map<String, String> storegeList) {
		this.storegeList = storegeList;
	}

	public Map<String, String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Map<String, String> categoryList) {
		this.categoryList = categoryList;
	}

	public Map<String, String> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(Map<String, String> goodsList) {
		this.goodsList = goodsList;
	}

	public Map<String, String> getSheUserList() {
		return sheUserList;
	}

	public void setSheUserList(Map<String, String> sheUserList) {
		this.sheUserList = sheUserList;
	}
	
	public List<WM000V02Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<WM000V02Info> infoList) {
		this.infoList = infoList;
	}

	public Map<String, String> getStatusList() {
		return statusList;
	}

	public void setStatusList(Map<String, String> statusList) {
		this.statusList = statusList;
	}
	

}
