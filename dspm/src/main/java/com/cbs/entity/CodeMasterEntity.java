package com.cbs.entity;

import java.math.BigDecimal;

public class CodeMasterEntity {

	//コードID
	private String codeID;

	//コード名
	private String codeName;

	//ファイル
	private String field;

	//削除フラグ
	private BigDecimal delFlg;

	public String getCodeID() {
		return codeID;
	}

	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public BigDecimal getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(BigDecimal delFlg) {
		this.delFlg = delFlg;
	}

}