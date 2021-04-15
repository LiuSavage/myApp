package com.cbs.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.InvertoryEntity;

/**
 * 棚卸入力
 */
@Mapper
public interface SI000V01Mapper {

	/**
	 * 棚卸情報登録
	 * @param invertory 棚卸情報
	 */
	int insert(InvertoryEntity invertory);

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