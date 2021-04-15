package com.cbs.form;

import java.util.Map;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class US000V02Form {

	//画面区分
	private String kbn;

	//ログインID
	@Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,9}$", message = "{US000V02.MS0003}")
	private String userId;
	//氏名
	@NotBlank(message = "{com.notnull}")
	private String userName;

	//パスワード
	@Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,20}$", message = "{US000V02.MS0003}")
	private String userPassword;

	//パスワード
	@NotBlank(message = "{com.notnull}")
	private String checkPassword;

	//メールアドレス
	@NotBlank(message = "{com.notnull}")
	@Email(message = "{com.notemail}")
	private String userMail;

	//地域
	@NotBlank(message = "{com.notnull}")
	private String userArea;

	//市区町村
	@NotBlank(message = "{com.notnull}")
	private String userCity;

	//住所
	private String address1;

	//地域リスト
	private Map<String, String> areaList;

	//市区町村リスト
	private Map<String, String> cityList;

	public String getKbn() {
		return kbn;
	}

	public void setKbn(String kbn) {
		this.kbn = kbn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public Map<String, String> getCityList() {
		return cityList;
	}

	public void setCityList(Map<String, String> cityList) {
		this.cityList = cityList;
	}

	public Map<String, String> getAreaList() {
		return areaList;
	}

	public void setAreaList(Map<String, String> areaList) {
		this.areaList = areaList;
	}
}