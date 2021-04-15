package com.cbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.AreaMasterEntity;
import com.cbs.entity.UserMasterEntity;

/**
 * 新規登録
 *
 */
@Mapper
public interface US000V02Mapper {
	
	/**
	 * 地域リスト
	 * @return　地域リスト
	 */
	List<AreaMasterEntity> getAreaList();

	/**
	 * 市区町村リスト
	 * @param userArea 地域code
	 * @return　市区町村リスト
	 */
	List<AreaMasterEntity> getCityList(@Param("userArea") String userArea);

	/**
	 * ユーザ情報
	 * @param userId ユーザID
	 * @return　ユーザ情報
	 */
	UserMasterEntity getUserInfo(@Param("userId") String userId);

	/**
	 * 登録処理
	 * @param userId ユーザ情報
	 */
	void insertUser(UserMasterEntity userInfo);
	
	/**
	 * 更新処理
	 * @param userId ユーザID
	 */
	Boolean delete(@Param("userId") String userId);

}
