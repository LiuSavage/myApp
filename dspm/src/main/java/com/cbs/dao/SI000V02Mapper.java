package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.InvertoryEntity;
import com.cbs.form.SI000V02DetailInfo;
import com.cbs.form.SI000V02Form;

/**
 * 棚卸一覧
 */
@Mapper
public interface SI000V02Mapper {

	/**
	 * 棚卸検索(表示リスト)
	 * @param offset 開始ページ
	 * @param size 表示件数
	 * @param form 表示フォーム
	 * @return 棚卸情報
	 */
	List<SI000V02DetailInfo> search(@Param("offset") BigDecimal offset, @Param("size") BigDecimal size,
			@Param("storegeCode") String storegeCode, @Param("goodsCode") String goodsCode,
			@Param("sheUser") String sheUser, @Param("starInvertory") LocalDate starInvertory,
			@Param("endInvertory") LocalDate endInvertory, @Param("shedStatus") String shedStatus,
			@Param("categoryCode") String categoryCode, @Param("shedResult") String shedResult);

	/**
	 * 棚卸検索(全部リスト)
	 * @param form 表示フォーム
	 * @return 棚卸情報
	 */
	List<SI000V02DetailInfo> searchAll(SI000V02Form form);

	/**
	 * 棚卸情報取得
	 * @param id 棚卸ID
	 * @return 棚卸情報
	 */
	InvertoryEntity select(@Param("id") BigDecimal id);

	/**
	 * 棚卸情報削除
	 * @param id 棚卸ID
	 * @return 棚卸情報
	 */
	int delete(@Param("id") BigDecimal id);
}