package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cbs.entity.StockEntity;
import com.cbs.entity.StockingEntity;

/**
 * 出庫入力
 *
 */
@Mapper
@Repository
public interface OS000V01Mapper {

	/**
	 * 出庫情報を検索
	 * @param 出庫id
	 */
	StockingEntity select(@Param("id") BigDecimal id);

	/**
	 * 出庫情報登録
	 * @param 出庫情報
	 */
	int insert(StockingEntity stocking);

	/**
	 * 出庫情報更新
	 * @param 出庫情報
	 */
	int delete(@Param("id") BigDecimal id);

	/**
	 * 在庫検索
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品
	 * @param stPrice 単価
	 * @param stExpiration 賞味/消費期限
	 * @param stockingUnit 単位
	 * @return 在庫情報
	 */
	StockEntity selectStock(@Param("storegeCode") String storegeCode, @Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode, @Param("stPrice") BigDecimal stPrice,
			@Param("stExpiration") LocalDate stExpiration, @Param("stockingUnit") String stockingUnit);

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
	 * 在庫検索
	 * @param stockingResult 出庫情報
	 */
	StockEntity selectStockPhoto(StockingEntity stockingResult);

}
