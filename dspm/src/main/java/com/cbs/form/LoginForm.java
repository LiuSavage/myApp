package com.cbs.form;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {

	//ログイン名
	@NotBlank(message = "{com.notnull}")
	private String username;

	//パスワード
	@NotBlank(message = "{com.notnull}")
	private String password;

	//選択
	private Boolean rememberMe;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}