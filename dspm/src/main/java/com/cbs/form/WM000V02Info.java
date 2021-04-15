package com.cbs.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class WM000V02Info {
		//No
		private BigDecimal id;

		//移動元倉庫名
		private String stockingStoregeName;
		
		//移動先倉庫名
		private String storingStoregeName;
		
		//カテゴリー名
		private String categoryName;
		
		//備品名
		private String goodsName;
		
		//移動担当者名
		private String userName;

		//移動日
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate stockingReceipt;

		//移動数
		private BigDecimal stockingQuantity;
		
		//ステータス
		private String stockingStatusName;

		//備考
		private String stComment;
		//移動元ID
		private BigDecimal stockingId;
		
		//移動先ID
		private BigDecimal storingId;
		
		//移動元倉庫コード
		private String stockingStoregeCode;

		//移動先倉庫コード
		private String storingStoregeCode;
		
		//カテゴリーコード
		private String categorCode;
		
		//備品code
		private String goodsCode;
		
		//移動担当者名
		private String userCode;
		
		//ステータス
		private String stockingStatus;
		
		public BigDecimal getId() {
			return id;
		}

		public void setId(BigDecimal id) {
			this.id = id;
		}
		
		public String getStockingStatusName() {
			return stockingStatusName;
		}

		public void setStockingStatusName(String stockingStatusName) {
			this.stockingStatusName = stockingStatusName;
		}

		public BigDecimal getStockingId() {
			return stockingId;
		}

		public void setStockingId(BigDecimal stockingId) {
			this.stockingId = stockingId;
		}

		public BigDecimal getStoringId() {
			return storingId;
		}

		public void setStoringId(BigDecimal storingId) {
			this.storingId = storingId;
		}

		public String getStockingStoregeCode() {
			return stockingStoregeCode;
		}

		public void setStockingStoregeCode(String stockingStoregeCode) {
			this.stockingStoregeCode = stockingStoregeCode;
		}

		public String getStoringStoregeCode() {
			return storingStoregeCode;
		}

		public void setStoringStoregeCode(String storingStoregeCode) {
			this.storingStoregeCode = storingStoregeCode;
		}

	

		public LocalDate getStockingReceipt() {
			return stockingReceipt;
		}

		public void setStockingReceipt(LocalDate stockingReceipt) {
			this.stockingReceipt = stockingReceipt;
		}
		
		public String getStockingStoregeName() {
			return stockingStoregeName;
		}

		public void setStockingStoregeName(String stockingStoregeName) {
			this.stockingStoregeName = stockingStoregeName;
		}

		public String getStoringStoregeName() {
			return storingStoregeName;
		}

		public void setStoringStoregeName(String storingStoregeName) {
			this.storingStoregeName = storingStoregeName;
		}

		public String getCategorCode() {
			return categorCode;
		}

		public void setCategorCode(String categorCode) {
			this.categorCode = categorCode;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getGoodsCode() {
			return goodsCode;
		}

		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public String getUserCode() {
			return userCode;
		}

		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public BigDecimal getStockingQuantity() {
			return stockingQuantity;
		}

		public void setStockingQuantity(BigDecimal stockingQuantity) {
			this.stockingQuantity = stockingQuantity;
		}

		public String getStockingStatus() {
			return stockingStatus;
		}

		public void setStockingStatus(String stockingStatus) {
			this.stockingStatus = stockingStatus;
		}

		public String getStComment() {
			return stComment;
		}

		public void setStComment(String stComment) {
			this.stComment = stComment;
		}

		
		

}
