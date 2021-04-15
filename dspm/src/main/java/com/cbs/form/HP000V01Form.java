package com.cbs.form;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class HP000V01Form {
	
	//地域code
	private String areaCode;
	
	//ユーザーID
	private Object userCode;
		
	// カテゴリー
	private String categoryCode;
	
	// 倉庫名リスト
	private Map<String, String> storegeList;
	
	// 地域リスト
	private Map<String, String> areaList;
	
	// 人口リスト
	private Map<String, String> peapleList;
	
	// カテゴリーリスト
	private Map<String, String> categoryList;
	
	// 備品リスト
	private Map<String, String> goodsList;
	
	// 備品リスト
	private List<HP000V01Info> goodsNumList;
	
	// 人口情報
	private HP000V01Info infoList;
	
	// メッセージリスト
	private List<HP000V01Info> messageList;

	
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Object getUserCode() {
		return userCode;
	}

	public void setUserCode(Object userCode) {
		this.userCode = userCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Map<String, String> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(Map<String, String> goodsList) {
		this.goodsList = goodsList;
	}

	public Map<String, String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Map<String, String> categoryList) {
		this.categoryList = categoryList;
	}

	public Map<String, String> getAreaList() {
		return areaList;
	}

	public void setAreaList(Map<String, String> areaList) {
		this.areaList = areaList;
	}

	public Map<String, String> getPeapleList() {
		return peapleList;
	}

	public void setPeapleList(Map<String, String> peapleList) {
		this.peapleList = peapleList;
	}

	public Map<String, String> getStoregeList() {
		return storegeList;
	}

	public void setStoregeList(Map<String, String> storegeList) {
		this.storegeList = storegeList;
	}

	public List<HP000V01Info> getGoodsNumList() {
		return goodsNumList;
	}

	public void setGoodsNumList(List<HP000V01Info> goodsNumList) {
		this.goodsNumList = goodsNumList;
	}

	public HP000V01Info getInfoList() {
		return infoList;
	}

	public void setInfoList(HP000V01Info infoList) {
		this.infoList = infoList;
	}

	public List<HP000V01Info> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<HP000V01Info> messageList) {
		this.messageList = messageList;
	}
	

}
