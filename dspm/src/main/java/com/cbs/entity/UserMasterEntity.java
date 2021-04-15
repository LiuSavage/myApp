package com.cbs.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserMasterEntity {

	//ユーザーID
	private String userID;

	//ユーザーID
	private String userPassword;

	//ユーザー名
	private String userName;

	//ユーザー名(カタカナ)
	private String userNameKana;

	//ユーザー名(略称)
	private String userAbbre;

	//権限
	private BigDecimal userAuth;

	//電話番号
	private BigDecimal phoneNum;

	//メール
	private String email;

	//郵便番号
	private String postNum;

	//都道府県
	private String areaID;

	//住所1
	private String address1;

	//住所2
	private String address2;

	//住所3
	private String address3;

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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameKana() {
		return userNameKana;
	}

	public void setUserNameKana(String userNameKana) {
		this.userNameKana = userNameKana;
	}

	public String getUserAbbre() {
		return userAbbre;
	}

	public void setUserAbbre(String userAbbre) {
		this.userAbbre = userAbbre;
	}

	public BigDecimal getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(BigDecimal userAuth) {
		this.userAuth = userAuth;
	}

	public BigDecimal getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(BigDecimal phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostNum() {
		return postNum;
	}

	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
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

}