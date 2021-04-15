package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cbs.dao.CommonMapper;
import com.cbs.entity.CodeMasterEntity;
import com.cbs.entity.StockpileMasterEntity;
import com.cbs.entity.StoregeEntity;
import com.cbs.entity.UserMasterEntity;

/**
 * 共通サービス
 */
@Service
@Transactional
public class BussinessCommon {

	public static final String DATE_FORMAT = "yyyyMMddHHmmss";

	@Autowired
	private CommonMapper commonMapper;

	/**
	 * 倉庫情報と取得すること。
	 * @param storegeCode 倉庫コード
	 */
	public StoregeEntity getStorege(String storegeCode) {
		return commonMapper.getStorege(storegeCode);
	}

	/**
	 * 倉庫ドロップダウンリストと取得すること
	 */
	public Map<String, String> getStoregeList() {

		Map<String, String> map = new TreeMap<>();
		map.put("", "選択してください");

		//倉庫情報を取得すること。
		List<StoregeEntity> storegeList = commonMapper.getStoregeList();

		if (storegeList != null && storegeList.size() > 0) {
			for (StoregeEntity item : storegeList) {
				map.put(item.getStoregeCode(), item.getStoregeName());
			}
		}
		return map;
	}

	/**
	 * カテゴリードロップダウンリストと取得すること
	 */
	public Map<String, String> getCategoryList() {

		Map<String, String> map = new TreeMap<>();
		map.put("", "選択してください");

		//カテゴリー情報を取得すること。
		List<StockpileMasterEntity> categoryList = commonMapper.getCategoryList();

		if (categoryList != null && categoryList.size() > 0) {
			for (StockpileMasterEntity item : categoryList) {
				map.put(item.getCategoryCode(), item.getCategoryName());
			}
		}
		return map;
	}

	/**
	 * ユーザードロップダウンリストと取得すること
	 */
	public Map<String, String> getUserList() {

		Map<String, String> map = new TreeMap<>();
		map.put("", "選択してください");

		//ユーザー情報を取得すること。
		List<UserMasterEntity> userList = commonMapper.getUserList();

		if (userList != null && userList.size() > 0) {
			for (UserMasterEntity item : userList) {
				map.put(item.getUserID(), item.getUserName());
			}
		}
		return map;
	}

	/**
	 * 備品ドロップダウンリストと取得すること
	 * @param categoryCode カテゴリーID
	 */
	public Map<String, String> getGoodsList(String categoryCode) {

		Map<String, String> map = new TreeMap<>();

		map.put("", "選択してください");
		if (StringUtils.isEmpty(categoryCode)) {
			return map;
		}

		//カテゴリー情報を取得すること。
		List<StockpileMasterEntity> categoryList = commonMapper.getGoodsList(categoryCode);

		if (categoryList != null && categoryList.size() > 0) {
			for (StockpileMasterEntity item : categoryList) {
				map.put(item.getGoodsCode(), item.getGoodsName());
			}
		}
		return map;
	}

	/**
	 * ステータスリストと取得すること
	 * @param id コードID
	 */
	public Map<String, String> getCodeList(String id) {

		Map<String, String> map = new TreeMap<>();
		map.put("", "選択してください");

		//コード情報を取得すること。
		List<CodeMasterEntity> codeList = commonMapper.getCodeList(id);

		if (codeList != null && codeList.size() > 0) {
			for (CodeMasterEntity item : codeList) {
				map.put(item.getCodeID(), item.getCodeName());
			}
		}
		return map;
	}

	/**
	 * 在庫ステータスを判断すること。
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @param sumStockNumber 総備品数
	 * @return (1026)在庫充足/(1025)在庫不足
	 */
	public String getAmount(String storegeCode, String categoryCode, String goodsCode, BigDecimal sumStockNumber) {

		if (StringUtils.isEmpty(storegeCode)) {
			return "";
		}

		//一人用数量
		BigDecimal amount = commonMapper.getPersonAmount(categoryCode, goodsCode);

		//地域人口数
		BigDecimal sumPerson = commonMapper.getSumPerson(storegeCode);
		//区域需求数
		BigDecimal sumAmount = sumPerson.multiply(amount);

		if (sumStockNumber.compareTo(sumAmount) >= 0) {
			commonMapper.updStockStatus(storegeCode, categoryCode, goodsCode, "1025");
			return "1025";
		} else {
			commonMapper.updStockStatus(storegeCode, categoryCode, goodsCode, "1026");
			return "1026";
		}
	}

	/**
	 * 入庫前の備品数を取得すること。
	 * @param storegeCode 倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @return 入庫前の備品数
	 */
	public BigDecimal getSumStockNumber(String storegeCode, String categoryCode, String goodsCode) {

		if (StringUtils.isEmpty(storegeCode) || StringUtils.isEmpty(categoryCode) || StringUtils.isEmpty(goodsCode)) {
			return BigDecimal.ZERO;
		}

		//入庫前の備品数
		return commonMapper.getSumStockNumber(storegeCode, categoryCode, goodsCode);
	}
}