package com.cbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbs.form.HP000V01Form;
import com.cbs.form.HP000V01Info;
import com.cbs.service.BussinessCommon;
import com.cbs.service.HP000V01Service;

/**
 * ホーム画面のコントロール
 *
 */
@Controller
public class HP000V01Controller {
	@Autowired
	private HP000V01Service service;

	@Autowired
	private BussinessCommon bussinessCommon;

	/**
	 * 描画前処理
	 * 
	 * @param form
	 *            画面フォーム
	 * @param session
	 *            セッション
	 */
	@ModelAttribute(name = "hp000V01Form")
	public HP000V01Form prerender(@ModelAttribute(name = "hp000V01Form") HP000V01Form form, HttpSession session) {
		// 倉庫名
		form.setStoregeList(bussinessCommon.getStoregeList());

		// カテゴリー
		form.setCategoryList(bussinessCommon.getCategoryList());

		// カテゴリーcode
		if (form.getCategoryCode() == null) {
			for (String code : form.getCategoryList().keySet()) {
				if (code != "") {
					form.setCategoryCode(code);
					break;
				}
			}
		}

		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));

		return form;
	}

	/**
	 * 初期化処理
	 * 
	 * @param form
	 *            画面フォーム
	 * @param session
	 *            セッション
	 */
	@RequestMapping("/home")
	public ModelAndView init(@ModelAttribute(name = "hp000V01Form") HP000V01Form form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("hp000V01");
		model.addObject("leftMenuClass", "home");

		form.setUserCode(session.getAttribute("loginID"));
		// 人口動態
		HP000V01Info peaple = service.selectPeaple(form);

		form.setInfoList(peaple);

		// 備品数データリスト
		List<HP000V01Info> goodsNumInfo = new ArrayList<HP000V01Info>();
		Integer checkListNum = 0;
//		for (String goodsCode : form.getGoodsList().keySet()) {
//			HP000V01Info goodsSumList = service.selectGoodsSumList(goodsCode, peaple.getAreaID());
//			if (checkListNum <= 19) {
//				if (goodsSumList != null) {
//					goodsNumInfo.add(goodsSumList);
//					checkListNum = checkListNum + 1;
//				}
//			} else {
//				break;
//			}
//		}
		form.setGoodsNumList(goodsNumInfo);

		// 最新更新テータ
		List<HP000V01Info> dataList = new ArrayList<HP000V01Info>();
		dataList = service.selectDataList();
		form.setMessageList(dataList);

		return model;
	}

}
