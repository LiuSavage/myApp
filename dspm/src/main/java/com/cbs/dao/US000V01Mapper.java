package com.cbs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cbs.entity.UserMasterEntity;

/**
 * ログイン画面
 */
@Mapper
public interface US000V01Mapper {

	/**
	 * ユーザー情報を取得すること。
	 * @param userName ユーザー名称
	 * @return ユーザー情報
	 */
	UserMasterEntity select(@Param("userID") String userID, @Param("password") String password);

}