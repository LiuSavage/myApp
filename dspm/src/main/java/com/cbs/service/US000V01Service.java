package com.cbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbs.dao.US000V01Mapper;
import com.cbs.entity.UserMasterEntity;

/**
 * ログインのサービス
 */
@Service
@Transactional
public class US000V01Service {

	@Autowired
	private US000V01Mapper us000V01Mapper;

	/**
	 * ログインIDとパスワードを検索
	 * @param userID ログインID
	 * @param password パスワード
	 * @return ユーザーが存在情報
	 */
	public UserMasterEntity select(String userID, String password) {
		return us000V01Mapper.select(userID, password);
	}

}