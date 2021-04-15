package com.cbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.form.HP000V01Form;
import com.cbs.form.HP000V01Info;

/**
 * ホーム画面
 *
 */
@Mapper
public interface HP000V01Mapper {

	/**
	 * 備品数データリスト
	 * @param goodsCode 備品
	 * @param areaID 地域
	 * @return　備品数
	 */
	HP000V01Info selectGoodsSumList(@Param("goodsCode") String goodsCode, @Param("areaID") String areaID);

	/**
	 * 人口動態
	 * @param form 画面フォーム
	 * @return　人口数
	 */
	HP000V01Info selectPeaple(HP000V01Form form);

	/**
	 * お知らせ
	 * @return　お知らせリスト
	 */
	List<HP000V01Info> selectDataList();

}
