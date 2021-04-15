package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.StockEntity;
import com.cbs.entity.StockingEntity;
import com.cbs.entity.StoringEntity;
import com.cbs.form.WM000V01Form;

/**
 * 倉庫間移動入力
 *
 */
@Mapper
public interface WM000V01Mapper {
	
	/**
	 * 在庫数検索
	 * @param storegeCode　倉庫code
	 * @param categoryCode　カテゴリー
	 * @param goodsCode　備品
	 * @param stockingUnit　出庫単位
	 * @return　在庫数
	 */
	BigDecimal stockNum(@Param("storegeCode") String storegeCode,
			@Param("categoryCode") String categoryCode,@Param("goodsCode") String goodsCode,@Param("stockingUnit") String stockingUnit);
	
	/**
	 * 在庫数リスト検索
	 * @param storegeCode　倉庫code
	 * @param categoryCode　カテゴリー
	 * @param goodsCode　備品
	 * @param stockingUnit　出庫単位
	 * @return　在庫リスト
	 */
	List<StockEntity> stockList(@Param("storegeCode") String storegeCode,
			@Param("categoryCode") String categoryCode,@Param("goodsCode") String goodsCode,@Param("stockingUnit") String stockingUnit);

	/**
	 * 出庫処理
	 * @param stockingInfo　出庫情報
	 */
	int insertStocking(StockingEntity stockingInfo);
	
	/**
	 * 入庫処理
	 * @param stockingInfo　入庫情報
	 */
	int insertStoring(StoringEntity storingInfo);

	/**　
	 * 在庫出処理
	 * @param id　更新ID
	 * @param stockNum　数量
	 * @param amount　金額
	 * @param delFlg　削除フラグ
	 * @param updateUser　更新者
	 * @param updateDate　更新日
	 */
	int updateStockOut(@Param("id") BigDecimal id,@Param("stockNum") BigDecimal stockNum,
			@Param("amount") BigDecimal amount, @Param("delFlg") BigDecimal delFlg,
			@Param("updateUser") String updateUser,@Param("updateDate") LocalDateTime updateDate);
	
	/**
	 * 在庫入処理
	 * @param inItem　在庫情報
	 */
	int InsertStockIn(StockEntity inItem);

	/**
	 * 在庫更新処理
	 * @param upId ID
	 */
	int updateStockIn(@Param("upId") BigDecimal upId);

	/**
	 * 備品選択
	 * @param stockingId 出庫Id
	 */
	WM000V01Form select(@Param("stockingId") BigDecimal stockingId);

	/**
	 * 更新入庫
	 * @param form フォーム
	 */
	int updateStoring(WM000V01Form form);
	
	/**
	 * 更新出庫
	 * @param form フォーム
	 */
	int updateStocking(WM000V01Form form);

	/**
	 * 在庫検索
	 * @param form フォーム
	 */
	StockEntity selectStock(WM000V01Form form);
	
	/**
	 * 在庫検索
	 * @param moveResult 入出庫備品情報
	 */
	StockEntity selectStockPhoto(WM000V01Form moveResult);

	/**
	 * 入出庫検索
	 * @param stockingId　出庫ID
	 * @return　入出庫情報
	 */
	StockEntity selectStocking(BigDecimal stockingId);

}
