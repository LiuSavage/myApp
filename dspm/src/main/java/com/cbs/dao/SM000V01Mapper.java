package com.cbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.StockpileMasterEntity;
import com.cbs.entity.StoregeEntity;

/**
 * マスタ管理
 */
@Mapper
public interface SM000V01Mapper {

	/**
	 * 倉庫情報登録
	 * @param storege 倉庫情報
	 */
	int insertStorege(StoregeEntity storege);

	/**
	 * 倉庫情報取得
	 * @param storegeCode 倉庫コード
	 */
	String selectStorege(@Param("storegeCode") String storegeCode);

	/**
	 * 倉庫情報削除
	 * @param storegeCode 倉庫コード
	 */
	int deleteStorege(@Param("storegeCode") String storegeCode);

	/**
	 * 備蓄品情報登録
	 * @param stockpile 備蓄品情報
	 */
	int insertStockpile(StockpileMasterEntity stockpile);

	/**
	 * 備蓄品情報取得
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 */
	String selectStockpile(@Param("categoryCode") String categoryCode, @Param("goodsCode") String goodsCode);

	/**
	 * 備蓄品情報削除
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 */
	int deleteStockpile(@Param("categoryCode") String categoryCode, @Param("goodsCode") String goodsCode);

	/**
	 * 在庫情報更新
	 */
	int updateStock();

	/**
	 * 入庫情報更新
	 */
	int updateStoring();

	/**
	 * 出庫情報更新
	 */
	int updateStocking();

	/**
	 * 棚卸情報更新
	 */
	int updateInvertory();

}