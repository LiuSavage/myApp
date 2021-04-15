package com.cbs.form;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Form {

	//選択ページ番号
	private BigDecimal pageNo;

	//表示件数
	private BigDecimal pageSize = BigDecimal.TEN;

	//表示ページ数
	private BigDecimal size = new BigDecimal(5);

	//総ページ数
	private BigDecimal sumPage;

	//表示開始ページ数
	private BigDecimal startPage;

	//表示終了ページ数
	private BigDecimal endPage;
	
	//検索件数
	private BigDecimal sumCount;

	public BigDecimal getPageNo() {
		return pageNo;
	}

	public void setPageNo(BigDecimal pageNo) {
		this.pageNo = pageNo;
	}

	public BigDecimal getPageSize() {
		return pageSize;
	}

	public void setPageSize(BigDecimal pageSize) {
		this.pageSize = pageSize;
	}

	public BigDecimal getSumPage() {
		return sumPage;
	}

	public void setSumPage(BigDecimal sumPage) {
		this.sumPage = sumPage;
	}

	public BigDecimal getStartPage() {
		return startPage;
	}

	public void setStartPage(BigDecimal startPage) {
		this.startPage = startPage;
	}

	public BigDecimal getEndPage() {
		return endPage;
	}

	public void setEndPage(BigDecimal endPage) {
		this.endPage = endPage;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public BigDecimal getSumCount() {
		return sumCount;
	}

	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}

}