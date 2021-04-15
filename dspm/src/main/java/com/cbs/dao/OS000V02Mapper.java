package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.StockingEntity;
import com.cbs.form.OS000V02DetailInfo;
import com.cbs.form.OS000V02Form;

/**
 * 出庫一覧
 *
 */
@Mapper
public interface OS000V02Mapper {

	/**
	 * 出庫検索(表示リスト)
	 * @param offset 開始ページ
	 * @param size 表示件数
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @param userCode 担当者コード
	 * @param stockingStatus ステータス
	 * @param stockingDateFrom 出庫開始日
	 * @param stockingDateEnd 出庫終了日
	 * @return 出庫詳細情報
	 */
	List<OS000V02DetailInfo> search(@Param("offset") BigDecimal offset, @Param("size") BigDecimal size,
			@Param("storegeCode") String storegeCode, @Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode, @Param("userCode") String userCode,
			@Param("stockingStatus") String stockingStatus, @Param("stockingDateFrom") LocalDate stockingDateFrom,
			@Param("stockingDateEnd") LocalDate stockingDateEnd);

	/**
	 * 入庫全部データリスト
	 * @param 画面フォーム
	 * @return　全部リスト　
	 */
	List<OS000V02DetailInfo> selectAll(OS000V02Form form);

	/**
	 * 出庫情報を検索
	 * @param 出庫id
	 */
	StockingEntity select(@Param("id") BigDecimal id);

	/**
	 * 出庫情報更新
	 * @param 出庫情報
	 */
	int delete(@Param("id") BigDecimal id);
}
