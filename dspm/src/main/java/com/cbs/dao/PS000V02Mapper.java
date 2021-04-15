package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.StoringEntity;
import com.cbs.form.PS000V02DetailInfo;

/**
 * 入庫一覧
 *
 */
@Mapper
public interface PS000V02Mapper {

	/**
	 * 入庫検索(表示リスト)
	 * @param offset 開始ページ
	 * @param size   表示件数
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @param userCode 担当者コード
	 * @param stStatus ステータス
	 * @param storingDateFrom 入庫開始日
	 * @param storingDateEnd 入庫終了日
	 * @return 入庫詳細情報
	 */
	List<PS000V02DetailInfo> search(@Param("offset") BigDecimal offset, @Param("size") BigDecimal size,
			@Param("storegeCode") String storegeCode, @Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode, @Param("userCode") String userCode,
			@Param("stStatus") String stStatus, @Param("storingDateFrom") LocalDate storingDateFrom,
			@Param("storingDateEnd") LocalDate storingDateEnd);

	/**
	 * 入庫検索(全部リスト)
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @param userCode 担当者コード
	 * @param stStatus ステータス
	 * @param storingDateFrom 入庫開始日
	 * @param storingDateEnd 入庫終了日
	 * @return 入庫詳細情報
	 */
	List<PS000V02DetailInfo> selectAll(@Param("storegeCode") String storegeCode,
			@Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode, @Param("userCode") String userCode,
			@Param("stStatus") String stStatus, @Param("storingDateFrom") LocalDate storingDateFrom,
			@Param("storingDateEnd") LocalDate storingDateEnd);

	/**
	 * 入庫情報を検索
	 *
	 * @param id 入庫ID
	 * @return 入庫情報
	 */
	StoringEntity select(@Param("id") BigDecimal id);

	/**
	 * 入庫情報更新
	 *
	 * @param id 入庫ID
	 */
	int deleteStoring(@Param("id") BigDecimal id);

}
