package com.cbs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.EmailCommonEntity;

/**
 * メール共通Dao
 */
@Mapper
public interface EmailCommonMapper {

	/**
	 * 期限切れた情報を取得すること。
	 *
	 * @return 期限切れた情報
	 */
	List<EmailCommonEntity> selectAll();

	/**
	 * 倉庫名を取得すること。
	 *
	 * @return 倉庫名
	 */
	String getStoregeName(@Param("storegeCode") String storegeCode);

	/**
	 * 備品名を取得すること。
	 *
	 * @return 備品名
	 */
	String getGoodsName(@Param("categoryCode") String categoryCode, @Param("goodsCode") String goodsCode);

	/**
	 * 単位を取得すること。
	 *
	 * @return 単位
	 */
	String getUnitName(@Param("codeID") String codeID);

	/**
	 * すべてのユーザー制限を取得すること。
	 *
	 * @return ユーザー制限リスト
	 */
	String[] getAllUserAuth();

	/**
	 * ユーザー制限を取得すること。
	 *
	 * @return ユーザー制限リスト
	 */
	String[] getUserAuth(@Param("userAuth") BigDecimal userAuth, @Param("loginID") String loginID);
}