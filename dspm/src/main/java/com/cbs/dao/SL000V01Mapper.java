package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.form.SL000V01DetailInfo;
import com.cbs.form.SL000V01Form;

/**
 * 賞味期限一覧
 */
@Mapper
public interface SL000V01Mapper {

	/**
	 * 在庫全部データリスト
	 * @param 画面フォーム
	 * @return 在庫情報
	 */
	List<SL000V01DetailInfo> selectAll(SL000V01Form form);

	/**
	 * 在庫検索(表示リスト)
	 * @param offset 開始ページ
	 * @param size 表示件数
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @param expirationFrom 賞味/消費期限開始日
	 * @param expirationEnd 賞味/消費期限終了日
	 * @return
	 */
	List<SL000V01DetailInfo> search(@Param("offset") BigDecimal offset, @Param("size") BigDecimal size,
			@Param("storegeCode") String storegeCode, @Param("categoryCode") String categoryCode,
			@Param("goodsCode") String goodsCode, @Param("expirationFrom") LocalDate expirationFrom,
			@Param("expirationEnd") LocalDate expirationEnd);
}
