package com.cbs.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StoregeEntity {

	//ID
	private Integer id;

	//倉庫コード
	private String storegeCode;

	//倉庫名
	private String storegeName;

	//タイプ
	private BigDecimal stType;

	//地域コード
	private BigDecimal areaID;

	//設置住所
	private String setAddress;

	//担当者コード
	private BigDecimal userCode;

	//担当者
	private String userName;

	//削除フラグ
	private BigDecimal delFlg;

	//登録日
	private LocalDateTime insertDate;

	//登録者
	private String insertUser;

	//更新日
	private LocalDateTime updateDate;

	//更新者
	private String updateUser;

	public String getStoregeCode() {
		return storegeCode;
	}

	public void setStoregeCode(String storegeCode) {
		this.storegeCode = storegeCode;
	}

	public String getStoregeName() {
		return storegeName;
	}

	public void setStoregeName(String storegeName) {
		this.storegeName = storegeName;
	}

	public BigDecimal getStType() {
		return stType;
	}

	public void setStType(BigDecimal stType) {
		this.stType = stType;
	}

	public BigDecimal getAreaID() {
		return areaID;
	}

	public void setAreaID(BigDecimal areaID) {
		this.areaID = areaID;
	}

	public String getSetAddress() {
		return setAddress;
	}

	public void setSetAddress(String setAddress) {
		this.setAddress = setAddress;
	}

	public BigDecimal getUserCode() {
		return userCode;
	}

	public void setUserCode(BigDecimal userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(BigDecimal delFlg) {
		this.delFlg = delFlg;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}