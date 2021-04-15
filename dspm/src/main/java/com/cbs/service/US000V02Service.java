package com.cbs.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cbs.dao.US000V02Mapper;
import com.cbs.entity.AreaMasterEntity;
import com.cbs.entity.UserMasterEntity;
import com.cbs.form.US000V02Form;

/**
 * ユーザ登録画面サービス
 */
@Service
@Transactional
public class US000V02Service {

	public static final String DATE_FORMAT = "yyyyMMddHHmmss";

	@Autowired
	private US000V02Mapper us000V02Mapper;

	/**
	 * 地域取得すること
	 */
	public Map<String, String> getAreaList() {
		Map<String, String> map = new TreeMap<>();
		map.put("", "選択してください");

		//地域情報を取得すること。
		List<AreaMasterEntity> areaList = us000V02Mapper.getAreaList();

		if (areaList != null && areaList.size() > 0) {
			for (AreaMasterEntity item : areaList) {
				map.put(item.getAreaCode(), item.getAreaName());
			}
		}
		return map;
	}

	/**
	 * 市区町村取得すること
	 * @param userArea 地域code
	 */
	public Map<String, String> getCityList(String userArea) {

		Map<String, String> map = new TreeMap<>();

		map.put("", "選択してください");
		if (StringUtils.isEmpty(userArea)) {
			return map;
		}

		//市区町村情報を取得すること。
		List<AreaMasterEntity> cityList = us000V02Mapper.getCityList(userArea);

		if (cityList != null && cityList.size() > 0) {
			for (AreaMasterEntity item : cityList) {
				map.put(item.getCityCode(), item.getCityName());
			}
		}
		return map;
	}

	/**
	 * ユーザ情報
	 * @param userId　ログインID
	 */
	public UserMasterEntity getUserInfo(String userId) {
		UserMasterEntity userInfo = us000V02Mapper.getUserInfo(userId);
		return userInfo;
	}

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 */
	public void insertUser(US000V02Form form, HttpSession session) {
		UserMasterEntity userInfo = new UserMasterEntity();
		userInfo.setUserID(form.getUserId());
		userInfo.setUserPassword(form.getUserPassword());
		userInfo.setUserName(form.getUserName());
		userInfo.setEmail(form.getUserMail());
		userInfo.setAreaID(form.getUserArea() + form.getUserCity());
		userInfo.setAddress1(form.getAddress1());
		userInfo.setUserAuth(new BigDecimal(4));
		userInfo.setInsertDate(LocalDateTime.now());
		userInfo.setInsertUser(form.getUserId());
		userInfo.setUpdateDate(LocalDateTime.now());
		userInfo.setUpdateUser(form.getUserId());
		us000V02Mapper.insertUser(userInfo);
	}

	/**
	 * 更新処理
	 * @param form 画面フォーム
	 */
	public Boolean updateUser(US000V02Form form, HttpSession session) {

		Boolean check = us000V02Mapper.delete(form.getUserId());
		UserMasterEntity userInfo = new UserMasterEntity();
		userInfo.setUserID(form.getUserId());
		userInfo.setUserPassword(form.getUserPassword());
		userInfo.setUserName(form.getUserName());
		userInfo.setEmail(form.getUserMail());
		userInfo.setAreaID(form.getUserArea() + form.getUserCity());
		userInfo.setAddress1(form.getAddress1());
		userInfo.setUserAuth(new BigDecimal(4));
		userInfo.setInsertDate(LocalDateTime.now());
		userInfo.setInsertUser(form.getUserId());
		userInfo.setUpdateDate(LocalDateTime.now());
		userInfo.setUpdateUser(form.getUserId());
		us000V02Mapper.insertUser(userInfo);

		return check;
	}
}