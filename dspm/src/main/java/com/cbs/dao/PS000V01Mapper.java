package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.StockEntity;
import com.cbs.entity.StoringEntity;

/**
 * 入庫入力
 *
 */
@Mapper
public interface PS000V01Mapper {

	/**
	 * 入庫情報を検索
	 *
	 * @param id 入庫ID
	 * @return 入庫情報
	 */
	StoringEntity select(@Param("id") BigDecimal id);

	/**
	 * 入庫情報登録
	 *
	 * @param 入庫情報
	 */
	int insertStoring(StoringEntity storing);

	/**
	 * 入庫情報更新
	 *
	 * @param id 入庫ID
	 */
	int deleteStoring(@Param("id") BigDecimal id);

	/**
	 * 在庫検索
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品
	 * @param stPrice 単価
	 * @param stExpiration 賞味/消費期限
	 * @param stUnit 単位
	 * @return 在庫情報
	 */
	StockEntity selectStock(@Param("storegeCode") String storegeCode, @Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode, @Param("stPrice") BigDecimal stPrice,
			@Param("stExpiration") LocalDate stExpiration,@Param("stUnit") String stUnit);

	/**
	 * 在庫情報削除
	 * @param id 在庫ID
	 * @return 在庫情報
	 */
	int deleteStock(@Param("id") BigDecimal id);

	/**
	 * 在庫情報登録
	 * @param stock 在庫情報
	 */
	int insertStock(StockEntity stock);

	/**
	 * 在庫備品写真選択
	 * @param id 在庫備品ID
	 * @return 写真URL
	 */
	String selectStockPhoto(@Param("id") BigDecimal id);
}
