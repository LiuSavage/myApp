package com.cbs.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.cbs.entity.InvertoryEntity;
import com.cbs.form.SI000V01Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.SI000V01Service;

/**
 * 棚卸入力のコントロール
 */
@Controller
public class SI000V01Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private SI000V01Service service;

	/**
	 * 描画前処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@ModelAttribute(name = "si000V01Form")
	public SI000V01Form prerender(@ModelAttribute(name = "si000V01Form") SI000V01Form form, HttpSession session) {
		//倉庫
		form.setStoregeList(bussinessCommon.getStoregeList());
		//カテゴリー
		form.setCategoryList(bussinessCommon.getCategoryList());
		//備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		//棚卸担当者
		form.setSheUser(session.getAttribute("loginID").toString());
		form.setSheUserList(bussinessCommon.getUserList());
		//ステータス
		form.setCodeList(bussinessCommon.getCodeList("5"));
		// 単位
		form.setUnitList(bussinessCommon.getCodeList("7"));
		return form;
	}

	/**
	 * 初期化処理
	 * @param session セッション
	 */
	@RequestMapping("/initSI")
	public ModelAndView init(@ModelAttribute(name = "si000V01Form") SI000V01Form form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("si000V01");
		model.addObject("leftMenuClass", "initSI");

		//参考(1)、更新(2)
		if ((StringUtils.equals(form.getKbn(), "1") || StringUtils.equals(form.getKbn(), "2"))
				&& form.getId() != null) {
			//棚卸情報取得
			InvertoryEntity invertoryResult = service.select(form.getId());
			form.setStoregeCode(invertoryResult.getStoregeCode());
			form.setCategoryCode(invertoryResult.getCategoryCode());
			form.setGoodsCode(invertoryResult.getGoodsCode());
			form.setStExpiration(invertoryResult.getStExpiration());
			form.setStUnit(invertoryResult.getStUnit());
			form.setStPrice(invertoryResult.getStPrice());
			form.setSheUser(invertoryResult.getUserCode());
			form.setStarInvertory(invertoryResult.getStarInvertory());
			form.setEndInvertory(invertoryResult.getEndInvertory());
			form.setInvertNumber(invertoryResult.getInvertNumber());
			form.setCode(invertoryResult.getStatus());
			form.setComment(invertoryResult.getComment());

			//備品
			form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		}

		return model;
	}

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/regterSI")
	public String regter(@Valid @ModelAttribute(name = "si000V01Form") SI000V01Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "regterSI");

		if (result.hasErrors()) {
			return "si000V01";
		}

		//棚卸開始日は棚卸終了日より大きい場合
		if (form.getStarInvertory().compareTo(form.getEndInvertory()) > 0) {
			result.reject("", messageSource.getMessage("com.date.compare", new Object[] { "棚卸開始日", "棚卸終了日" }, null));
			return "si000V01";
		}

		Boolean checkResult = service.insert(form, session, result);
		if (!checkResult) {
			return "si000V01";
		}

		return "redirect:/searchSI";
	}

	/**
	 * 更新処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/updateSI")
	public String update(@Valid @ModelAttribute(name = "si000V01Form") SI000V01Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "updateSI");

		if (result.hasErrors()) {
			return "si000V01";
		}

		//棚卸開始日は棚卸終了日より大きい場合
		if (form.getStarInvertory().compareTo(form.getEndInvertory()) > 0) {
			result.reject("", messageSource.getMessage("com.date.compare", new Object[] { "棚卸開始日", "棚卸終了日" }, null));
			return "si000V01";
		}

		Boolean checkResult = service.update(form, session, result);
		if (!checkResult) {
			return "si000V01";
		}
		return "redirect:/updSearchSI";
	}

	/**
	 * 備品を取得処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/getGoods")
	public String getGoods(@ModelAttribute(name = "si000V01Form") SI000V01Form form) {

		return "si000V01";
	}

}