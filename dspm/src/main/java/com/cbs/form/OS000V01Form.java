package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OS000V01Form extends Form {

	// 画面区分
	private String kbn;

	//ホム画面区分
	private String hkbn;

	// 出庫ID
	private BigDecimal id;

	// 倉庫コード
	@NotBlank(message = "{com.notnull}")
	private String storegeCode;

	// 倉庫名リスト
	private Map<String, String> storegeList;

	// カテゴリー
	@NotBlank(message = "{com.notnull}")
	private String categoryCode;

	// カテゴリーリスト
	private Map<String, String> categoryCodeList;

	// 備品
	@NotBlank(message = "{com.notnull}")
	private String goodsCode;

	// 備品リスト
	private Map<String, String> goodsList;

	// 担当者コード
	@NotBlank(message = "{com.notnull}")
	private String userCode;

	// 担当者リスト
	private Map<String, String> userCodeList;

	// 出庫予定日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stockingScheduled;

	// 出庫日
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stockingReceipt;

	// 賞味／消費期限
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stExpiration;

	// LOT/シリアルNO
	private String serialNumber;

	// 単価
	@NotNull(message = "{com.notnull}")
	private BigDecimal stPrice;

	// 出庫数
	@NotNull(message = "{com.notnull}")
	private BigDecimal stockingQuantity;

	// 入庫単位
	@NotBlank(message = "{com.notnull}")
	private String stockingUnit;

	// 単位リスト
	private Map<String, String> unitList;

	// 金額
	private BigDecimal stAmount;

	// 出庫理由
	@NotBlank(message = "{com.notnull}")
	private String stockingReason;

	// 出庫理由リスト
	private Map<String, String> reasonList;

	// 出庫ステータス
	@NotBlank(message = "{com.notnull}")
	private String stockingStatus;

	// 出庫ステータスリスト
	private Map<String, String> codeList;

	// 備考
	private String stComment;
	
	// 写真リスト
	private String[] photoList;

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

	public Map<String, String> getUnitList() {
		return unitList;
	}

	public void setUnitList(Map<String, String> unitList) {
		this.unitList = unitList;
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

	public Map<String, String> getReasonList() {
		return reasonList;
	}

	public void setReasonList(Map<String, String> reasonList) {
		this.reasonList = reasonList;
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

	public String getStComment() {
		return stComment;
	}

	public void setStComment(String stComment) {
		this.stComment = stComment;
	}

	public String[] getPhotoList() {
		return photoList;
	}

	public void setPhotoList(String[] photoList) {
		this.photoList = photoList;
	}

	public String getHkbn() {
		return hkbn;
	}

	public void setHkbn(String hkbn) {
		this.hkbn = hkbn;
	}
}
