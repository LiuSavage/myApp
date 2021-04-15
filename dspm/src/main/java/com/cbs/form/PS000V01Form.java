package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PS000V01Form extends Form {

	// 画面区分
	private String kbn;

	//ホム画面区分
	private String hkbn;

	// ID
	private BigDecimal id;

	// 倉庫名
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

	// 入庫予定日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stScheduled;

	// 入庫日
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stReceipt;

	// 賞味／消費期限
	@NotNull(message = "{com.notnull}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate stExpiration;

	// LOT/シリアルNO
	private String serialNumber;

	// 単価
	@NotNull(message = "{com.notnull}")
	private BigDecimal stPrice;

	// 入庫数
	@NotNull(message = "{com.notnull}")
	private BigDecimal stQuantity;

	// 入庫単位
	@NotBlank(message = "{com.notnull}")
	private String stUnit;

	// 単位リスト
	private Map<String, String> unitList;

	// 金額
	private BigDecimal stAmount;

	// 入庫理由
	@NotBlank(message = "{com.notnull}")
	private String stReason;

	// 理由リスト
	private Map<String, String> resonList;

	// ステータス
	@NotBlank(message = "{com.notnull}")
	private String stStatus;

	// ステータスリスト
	private Map<String, String> codeList;

	// 備考
	private String stComment;

	private MultipartFile[] files;

	// 写真
	private String photo;

	// 写真リスト
	private String[] photoList;

	// 写真削除リスト
	private String[] delFile;

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

	public LocalDate getStScheduled() {
		return stScheduled;
	}

	public void setStScheduled(LocalDate stScheduled) {
		this.stScheduled = stScheduled;
	}

	public LocalDate getStReceipt() {
		return stReceipt;
	}

	public void setStReceipt(LocalDate stReceipt) {
		this.stReceipt = stReceipt;
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

	public BigDecimal getStQuantity() {
		return stQuantity;
	}

	public void setStQuantity(BigDecimal stQuantity) {
		this.stQuantity = stQuantity;
	}

	public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
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

	public String getStReason() {
		return stReason;
	}

	public void setStReason(String stReason) {
		this.stReason = stReason;
	}

	public Map<String, String> getResonList() {
		return resonList;
	}

	public void setResonList(Map<String, String> resonList) {
		this.resonList = resonList;
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

	public String getStComment() {
		return stComment;
	}

	public void setStComment(String stComment) {
		this.stComment = stComment;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
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

	public String[] getDelFile() {
		return delFile;
	}

	public void setDelFile(String[] delFile) {
		this.delFile = delFile;
	}
}
