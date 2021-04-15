package com.cbs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.CodeMasterEntity;
import com.cbs.entity.StockpileMasterEntity;
import com.cbs.entity.StoregeEntity;
import com.cbs.entity.UserMasterEntity;

/**
 * 共通Dao
 */
@Mapper
public interface CommonMapper {

	/**
	 * 倉庫情報と取得すること。
	 * @param storegeCode 倉庫コード
	 * @return 倉庫情報
	 */
	StoregeEntity getStorege(@Param("storegeCode") String storegeCode);

	/**
	 * 倉庫名リストと取得すること。
	 * @return 倉庫名リスト
	 */
	List<StoregeEntity> getStoregeList();

	/**
	 * カテゴリーリストと取得すること。
	 * @return カテゴリーリスト
	 */
	List<StockpileMasterEntity> getCategoryList();

	/**
	 * ユーザーリストと取得すること。
	 * @return ユーザーリスト
	 */
	List<UserMasterEntity> getUserList();

	/**
	 * 備品リストと取得すること。
	 * @param categoryCode カテゴリーコード
	 * @return 備品リスト
	 */
	List<StockpileMasterEntity> getGoodsList(@Param("categoryCode") String categoryCode);

	/**
	 * コードリストと取得すること。
	 * @param id コード
	 * @return コードリスト
	 */
	List<CodeMasterEntity> getCodeList(@Param("id") String id);

	/**
	 * 地域実際の備品数を取得すること。
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 */
	BigDecimal getSumStockNumber(@Param("storegeCode") String storegeCode, @Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode);

	/**
	 * 地域人口数を取得すること。
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 */
	BigDecimal getSumPerson(@Param("storegeCode") String storegeCode);

	/**
	 * 一人用数量を取得すること。
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 */
	BigDecimal getPersonAmount(@Param("categoryCode") String categoryCode, @Param("goodsCode") String goodsCode);

	/**
	 * 区域の倉庫情報を取得すること。
	 * @param storegeCode 倉庫コード
	 */
	List<String> getAreaStoregeCode(@Param("storegeCode") String storegeCode);

	/**
	 * 在庫ステータスを更新すること。
	 * @param storegeCode 倉庫コードリスト
	 */
	int updStockStatus(@Param("storegeCode") String storegeCode, @Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode,
			@Param("status") String status);

}