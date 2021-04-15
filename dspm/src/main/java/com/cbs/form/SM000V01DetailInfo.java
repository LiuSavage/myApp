package com.cbs.form;

import lombok.Data;

@Data
public class SM000V01DetailInfo {

	//コード
	private String code;

	//名称
	private String name;

	//メッセージ
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}