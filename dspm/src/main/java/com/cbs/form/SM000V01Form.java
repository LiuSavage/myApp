package com.cbs.form;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SM000V01Form {

	//登録選択
	@NotBlank(message = "{com.notnull}")
	private String select;

	//登録選択リスト
	private Map<String, String> selectList;

	//アップロードファイル
	private MultipartFile file;

	//成功データリスト
	private List<SM000V01DetailInfo> successList;

	//失敗データリスト
	private List<SM000V01DetailInfo> failList;

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public Map<String, String> getSelectList() {
		return selectList;
	}

	public void setSelectList(Map<String, String> selectList) {
		this.selectList = selectList;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public List<SM000V01DetailInfo> getSuccessList() {
		return successList;
	}

	public void setSuccessList(List<SM000V01DetailInfo> successList) {
		this.successList = successList;
	}

	public List<SM000V01DetailInfo> getFailList() {
		return failList;
	}

	public void setFailList(List<SM000V01DetailInfo> failList) {
		this.failList = failList;
	}


}