package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cbs.form.IS000V01DetailInfo;
import com.cbs.form.IS000V01Form;

/**
 * 在庫一覧
 */
@Mapper
@Repository
public interface IS000V01Mapper {

	/**
	 * 画面表示情報
	 * @param offset 開始ページ
	 * @param size 表示件数
	 * @param storegeCode 倉庫コード
	 * @param stockStatus 在庫状況
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @param expirationFrom 賞味/消費期限開始日
	 * @param expirationEnd 賞味/消費期限終了日
	 * @return 在庫情報
	 */
	List<IS000V01DetailInfo> selectGroupby(@Param("offset") BigDecimal offset, @Param("size") BigDecimal size,
			@Param("storegeCode") String storegeCode, @Param("stockStatus") String stockStatus,
			@Param("categoryCode") String categoryCode, @Param("goodsCode") String goodsCode,
			@Param("expirationFrom") LocalDate expirationFrom, @Param("expirationEnd") LocalDate expirationEnd);

	/**
	 * 在庫(全部リスト)
	 * @param form 画面フォーム
	 * @return すべての在庫情報
	 */
	List<IS000V01DetailInfo> selectAll(IS000V01Form form);
}