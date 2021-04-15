package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SI000V02Form extends Form {

	//倉庫名
	private String storegeCode;

	//倉庫名リスト
	private Map<String, String> storegeList;

	//カテゴリー
	private String categoryCode;

	//カテゴリーリスト
	private Map<String, String> categoryList;

	//備品
	private String goodsCode;

	//備品リスト
	private Map<String, String> goodsList;

	//棚卸担当者
	private String sheUser;

	//棚卸担当者リスト
	private Map<String, String> sheUserList;

	//棚卸開始日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate starInvertory;

	//棚卸終了日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endInvertory;

	//棚卸結果
	private String shedResult;

	//棚卸結果
	private Map<String, String> shedResultList;

	//ステータス
	private String shedStatus;

	//ステータスリスト
	private Map<String, String> statusList;

	//棚卸一覧情報
	private List<SI000V02DetailInfo> detailInfo;

	//削除データID
	private BigDecimal deleteID;

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

	public List<SI000V02DetailInfo> getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(List<SI000V02DetailInfo> detailInfo) {
		this.detailInfo = detailInfo;
	}

	public String getShedResult() {
		return shedResult;
	}

	public void setShedResult(String shedResult) {
		this.shedResult = shedResult;
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

	public Map<String, String> getShedResultList() {
		return shedResultList;
	}

	public void setShedResultList(Map<String, String> shedResultList) {
		this.shedResultList = shedResultList;
	}

	public Map<String, String> getStatusList() {
		return statusList;
	}

	public void setStatusList(Map<String, String> statusList) {
		this.statusList = statusList;
	}

	public String getShedStatus() {
		return shedStatus;
	}

	public void setShedStatus(String shedStatus) {
		this.shedStatus = shedStatus;
	}

	public BigDecimal getDeleteID() {
		return deleteID;
	}

	public void setDeleteID(BigDecimal deleteID) {
		this.deleteID = deleteID;
	}
}