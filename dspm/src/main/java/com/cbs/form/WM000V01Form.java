package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WM000V01Form {
	//画面区分
	private String kbn;

	//ホム画面区分
	private String hkbn;

	// 受信Id
	private BigDecimal id;

	// 移動元倉庫Id
	private BigDecimal stockingId;

	// 移動先倉庫Id
	private BigDecimal storingId;

	// 移動元倉庫
	@NotBlank(message = "{com.notnull}")
	private String storegeCode;

	// 移動先倉庫
	@NotBlank(message = "{com.notnull}")
	private String storegeCode2;

	// カテゴリー
	@NotBlank(message = "{com.notnull}")
	private String categoryCode;

	// 備品
	@NotBlank(message = "{com.notnull}")
	private String goodsCode;

	// 移動担当者
	@NotBlank(message = "{com.notnull}")
	private String sheUser;

	// 移動日
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate movingDay;

	// 賞味／消費期限
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiration;

	// LOT/シリアルNO
	private String serialNumber;

	// 単価
	private BigDecimal price;

	// 在庫数
	private BigDecimal stockNumber;

	// 移動数
	@NotNull(message = "{com.notnull}")
	private BigDecimal movingNumber;

	// 出庫単位
	@NotBlank(message = "{com.notnull}")
	private String stockingUnit;

	// 金額
	private BigDecimal amount;

	// 出庫ステータス
	@NotBlank(message = "{com.notnull}")
	private String status;

	// 入庫ステータス
	private String storingStatus;

	// 倉庫間移動CODE
	private String stockingReason;

	// 備考
	private String comment;

	// 写真
	private String photo;

	// 写真リスト
	private String[] photoList;

	// 倉庫名リスト
	private Map<String, String> storegeList;

	// カテゴリーリスト
	private Map<String, String> categoryList;

	// 備品リスト
	private Map<String, String> goodsList;

	// 担当者リスト
	private Map<String, String> sheUserList;

	//単位リスト
	private Map<String, String> codeList;

	//ステータスリスト
	private Map<String, String> statusList;

	// 登録日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime insertDate;

	// 登録者
	private String insertUser;

	// 更新日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime updateDate;

	// 更新者
	private String updateUser;

	public String getKbn() {
		return kbn;
	}

	public void setKbn(String kbn) {
		this.kbn = kbn;
	}

	public String getHkbn() {
		return hkbn;
	}

	public void setHkbn(String hkbn) {
		this.hkbn = hkbn;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public LocalDate getMovingDay() {
		return movingDay;
	}

	public void setMovingDay(LocalDate movingDay) {
		this.movingDay = movingDay;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigDecimal getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(BigDecimal stockNumber) {
		this.stockNumber = stockNumber;
	}

	public BigDecimal getMovingNumber() {
		return movingNumber;
	}

	public void setMovingNumber(BigDecimal movingNumber) {
		this.movingNumber = movingNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStoringStatus() {
		return storingStatus;
	}

	public void setStoringStatus(String storingStatus) {
		this.storingStatus = storingStatus;
	}

	public String getStockingReason() {
		return stockingReason;
	}

	public void setStockingReason(String stockingReason) {
		this.stockingReason = stockingReason;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStockingUnit() {
		return stockingUnit;
	}

	public void setStockingUnit(String stockingUnit) {
		this.stockingUnit = stockingUnit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String[] getPhotoList() {
		return photoList;
	}

	public void setPhotoList(String[] photoList) {
		this.photoList = photoList;
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

	public Map<String, String> getCodeList() {
		return codeList;
	}

	public void setCodeList(Map<String, String> codeList) {
		this.codeList = codeList;
	}

	public Map<String, String> getStatusList() {
		return statusList;
	}

	public void setStatusList(Map<String, String> statusList) {
		this.statusList = statusList;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime localDateTime) {
		this.insertDate = localDateTime;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
