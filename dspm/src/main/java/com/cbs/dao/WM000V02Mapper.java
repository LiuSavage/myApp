package com.cbs.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cbs.form.WM000V02Form;
import com.cbs.form.WM000V02Info;

/**
 * 倉庫間移動一覧
 *
 */
@Mapper
public interface WM000V02Mapper {
	
	/**
	 * 倉庫間移動(表示リスト)
	 * @return　表示リスト
	 */
	List<WM000V02Info> search(@Param("offset") BigDecimal offset,@Param("size") BigDecimal size,
			 @Param("storegeCode") String storegeCode, @Param("storegeCode2") String storegeCode2,
			 @Param("categoryCode") String categoryCode, @Param("goodsCode") String goodsCode,
			 @Param("sheUser") String sheUser, @Param("movingDayFrom") LocalDate movingDayFrom,
			 @Param("movingDayTo") LocalDate movingDayTo, @Param("status") String status);

	/**
	 * 倉庫間移動(全部リスト)
	 * @param form　画面フォーム
	 * @return　全部リスト
	 */
	List<WM000V02Info> searchAll(WM000V02Form form);

	/**
	 * 出庫倉庫情報取得
	 * @param stockingId　出庫倉庫ID
	 * @return　出庫倉庫情報
	 */
	WM000V02Info selectInfo(@Param("stockingId") BigDecimal stockingId);

	/**
	 * 出庫倉庫情報削除
	 * @param stockingId　出庫倉庫ID
	 */
	int stockingDelete(@Param("stockingId") BigDecimal stockingId);

	/**
	 * 入庫倉庫情報削除
	 * @param storingId　入庫倉庫ID
	 */
	int storingDelete(@Param("storingId") BigDecimal storingId);


}
