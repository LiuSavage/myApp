package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SI000V01Form {

	//画面区分
	private String kbn;

	//ID
	private BigDecimal id;

	//倉庫名
	@NotBlank(message = "{com.notnull}")
	private String storegeCode;

	//倉庫名リスト
	private Map<String, String> storegeList;

	//カテゴリー
	@NotBlank(message = "{com.notnull}")
	private String categoryCode;

	//カテゴリーリスト
	private Map<String, String> categoryList;

	//備品
	@NotBlank(message = "{com.notnull}")
	private String goodsCode;

	//備品リスト
	private Map<String, String> goodsList;

	// 賞味／消費期限
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stExpiration;

	// 在庫単価
	@NotNull(message = "{com.notnull}")
	private BigDecimal stPrice;

	// 在庫数
	@NotBlank(message = "{com.notnull}")
	private String stockNumber;

	// 在庫単位
	@NotBlank(message = "{com.notnull}")
	private String stUnit;

	// 単位リスト
	private Map<String, String> unitList;

	//棚卸担当者
	@NotBlank(message = "{com.notnull}")
	private String sheUser;

	//棚卸担当者リスト
	private Map<String, String> sheUserList;

	//棚卸開始日
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate starInvertory;

	//棚卸終了日
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endInvertory;

	//棚卸数
	@NotNull(message = "{com.notnull}")
	private BigDecimal invertNumber;

	//ステータス
	@NotBlank(message = "{com.notnull}")
	private String code;

	//ステータスリスト
	private Map<String, String> codeList;

	//備考
	private String comment;

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

	public Map<String, String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Map<String, String> categoryList) {
		this.categoryList = categoryList;
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

	public LocalDate getStExpiration() {
		return stExpiration;
	}

	public void setStExpiration(LocalDate stExpiration) {
		this.stExpiration = stExpiration;
	}

	public BigDecimal getStPrice() {
		return stPrice;
	}

	public void setStPrice(BigDecimal stPrice) {
		this.stPrice = stPrice;
	}

	public Map<String, String> getUnitList() {
		return unitList;
	}

	public void setUnitList(Map<String, String> unitList) {
		this.unitList = unitList;
	}

	public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
	}

	public String getSheUser() {
		return sheUser;
	}

	public void setSheUser(String sheUser) {
		this.sheUser = sheUser;
	}

	public Map<String, String> getSheUserList() {
		return sheUserList;
	}

	public void setSheUserList(Map<String, String> sheUserList) {
		this.sheUserList = sheUserList;
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

	public BigDecimal getInvertNumber() {
		return invertNumber;
	}

	public void setInvertNumber(BigDecimal invertNumber) {
		this.invertNumber = invertNumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, String> getCodeList() {
		return codeList;
	}

	public void setCodeList(Map<String, String> codeList) {
		this.codeList = codeList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
	}

}