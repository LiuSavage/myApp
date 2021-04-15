package com.cbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbs.dao.HP000V01Mapper;
import com.cbs.form.HP000V01Form;
import com.cbs.form.HP000V01Info;

/**
 * ホーム画面のサービス
 *
 */
@Service
@Transactional
public class HP000V01Service {
	@Autowired
	private HP000V01Mapper hp000V01Mapper;
	
	/**
	 * 備品数データリスト
	 * @param goodsCode 備品
	 * @param areaID 地域
	 * @return　備品リスト
	 */
	public HP000V01Info selectGoodsSumList(String goodsCode, String areaID) {
		HP000V01Info goodsSumList = hp000V01Mapper.selectGoodsSumList(goodsCode, areaID);
		return goodsSumList;
	}
	
	/**
	 * 人口動態
	 * @param form　画面フォーム
	 * @return　人口数
	 */
	public HP000V01Info selectPeaple(HP000V01Form form) {
		HP000V01Info peaple = hp000V01Mapper.selectPeaple(form);
		return peaple;
	}

	/**
	 * お知らせ
	 * @return お知らせリスト
	 */
	public List<HP000V01Info> selectDataList() {
		List<HP000V01Info> dataList= new ArrayList<HP000V01Info>();
		dataList = hp000V01Mapper.selectDataList();
		return dataList;
	}

}
