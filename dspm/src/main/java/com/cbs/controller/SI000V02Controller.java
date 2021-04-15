package com.cbs.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbs.form.SI000V02DetailInfo;
import com.cbs.form.SI000V02Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.CsvFileCommon;
import com.cbs.service.PageCommon;
import com.cbs.service.SI000V02Service;

/**
 * 棚卸入力のコントロール
 */
@Controller
public class SI000V02Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private PageCommon pageCommon;

	@Autowired
	private CsvFileCommon csvFileCommon;

	@Autowired
	private SI000V02Service service;

	/**
	 * 描画前処理
	 * @param form 画面フォーム
	 */
	@ModelAttribute(name = "si000V02Form")
	public SI000V02Form prerender(@ModelAttribute(name = "si000V02Form") SI000V02Form form) {
		//倉庫
		form.setStoregeList(bussinessCommon.getStoregeList());
		//カテゴリー
		form.setCategoryList(bussinessCommon.getCategoryList());
		//備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		//棚卸担当者
		form.setSheUserList(bussinessCommon.getUserList());
		//ステータス
		form.setStatusList(bussinessCommon.getCodeList("5"));
		//棚卸結果
		form.setShedResultList(bussinessCommon.getCodeList("6"));

		return form;
	}

	/**
	 * 初期化処理
	 * @param session セッション
	 */
	@RequestMapping("/shedList")
	public ModelAndView init(@ModelAttribute(name = "si000V02Form") SI000V02Form form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.addObject("leftMenuClass", "shedList");

		form.setPageNo(BigDecimal.ONE);
		//画面表示List
		form.setDetailInfo(service.search(form));
		//全部データリスト
		List<SI000V02DetailInfo> list = service.searchAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));

		//総ページ数
		if (list != null && list.size() > 0 && list.size() <= 60) {
			form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		} else if (list != null && list.size() > 0 && list.size() > 60) {
			form.setSumPage(pageCommon.getSumPage(60, form.getPageSize()));
		} else {
			form.setSumPage(BigDecimal.ZERO);
		}
		//表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		//表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		model.setViewName("si000V02");

		//セッション削除
		session.removeAttribute("SI000V02Form");
		session.setAttribute("SI000V02Form", form);
		return model;
	}

	/**
	 * 検索処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/searchSI")
	public String search(@Valid @ModelAttribute(name = "si000V02Form") SI000V02Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "searchSI");

		if (result.hasErrors()) {
			return "si000V02";
		}
		//棚卸開始日は棚卸終了日より大きい場合
		if (form.getStarInvertory() != null && form.getEndInvertory() != null
				&& form.getStarInvertory().compareTo(form.getEndInvertory()) > 0) {
			result.reject("", messageSource.getMessage("com.date.compare", new Object[] { "棚卸開始日", "棚卸終了日" }, null));
			return "si000V02";
		}

		form.setPageNo(BigDecimal.ONE);
		form = updAfterSearch(form);

		session.setAttribute("SI000V02Form", form);

		return "si000V02";
	}

	/**
	 * 更新後の検索処理
	 * @param result チェック結果
	 */
	@RequestMapping("/updSearchSI")
	public String searchUpd(@ModelAttribute(name = "si000V02Form") SI000V02Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "updSearchSI");

		//棚卸開始日は棚卸終了日より大きい場合
		if (form.getStarInvertory() != null && form.getEndInvertory() != null
				&& form.getStarInvertory().compareTo(form.getEndInvertory()) > 0) {
			result.reject("", messageSource.getMessage("com.date.compare", new Object[] { "棚卸開始日", "棚卸終了日" }, null));
			return "si000V02";
		}

		SI000V02Form sessionForm = (SI000V02Form) session.getAttribute("SI000V02Form");
		form.setStoregeCode(sessionForm.getStoregeCode());
		form.setShedStatus(sessionForm.getShedStatus());
		form.setCategoryCode(sessionForm.getCategoryCode());
		form.setGoodsCode(sessionForm.getGoodsCode());
		form.setStarInvertory(sessionForm.getStarInvertory());
		form.setEndInvertory(sessionForm.getEndInvertory());
		form.setSheUser(sessionForm.getSheUser());
		form.setShedResult(sessionForm.getShedResult());
		form.setPageNo(sessionForm.getPageNo());
		form = updAfterSearch(form);
		//備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		return "si000V02";
	}

	/**
	 * 検索共通
	 * @param form 画面フォーム
	 * @return 表示項目
	 */
	private SI000V02Form updAfterSearch(SI000V02Form form) {
		//画面表示List
		form.setDetailInfo(service.search(form));
		//全部データリスト
		List<SI000V02DetailInfo> list = service.searchAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		//総ページ数
		form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		//表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		//表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		return form;
	}

	/**
	 * 削除処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/deleteSI")
	public String delete(@ModelAttribute(name = "si000V02Form") SI000V02Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "deleteSI");

		Boolean checkResult = service.delete(form.getDeleteID(), result);
		if (!checkResult) {
			return "si000V02";
		}

		//全部データリスト
		List<SI000V02DetailInfo> list = service.searchAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		//総ページ数
		BigDecimal sumPage = pageCommon.getSumPage(list.size(), form.getPageSize());
		form.setSumPage(sumPage);

		if (sumPage.compareTo(BigDecimal.ZERO) == 0) {
			return "si000V02";
		}

		if (sumPage.compareTo(form.getPageNo()) < 0) {
			form.setPageNo(sumPage);
		}
		//画面表示List
		form.setDetailInfo(service.search(form));

		//表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		//表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		session.setAttribute("SI000V02Form", form);

		return "si000V02";
	}

	/**
	 * 改ページ処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/shedPage")
	public ModelAndView getPage(@ModelAttribute(name = "si000V02Form") SI000V02Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName("si000V02");

		SI000V02Form sessionForm = (SI000V02Form) session.getAttribute("SI000V02Form");
		sessionForm.setPageNo(form.getPageNo());
		session.setAttribute("SI000V02Form", sessionForm);

		//画面表示List
		form.setDetailInfo(service.search(form));
		//全部データリスト
		List<SI000V02DetailInfo> list = service.searchAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		//総ページ数
		form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		//表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		//表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		return model;
	}

	/**
	 * 一覧ダウンロード処理
	 * @param session セッション
	 */
	@RequestMapping("/shedCsvDown")
	@ResponseBody
	public String csvDown(HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName("si000V02");

		SI000V02Form form = (SI000V02Form) session.getAttribute("SI000V02Form");

		//全部データリスト
		List<SI000V02DetailInfo> list = service.searchAll(form);
		List<String> titleList = new ArrayList<>();
		titleList.add("NO");
		titleList.add("倉庫名");
		titleList.add("備品名");
		titleList.add("棚卸担当者");
		titleList.add("棚卸開始日");
		titleList.add("棚卸終了日");
		titleList.add("在庫数");
		titleList.add("棚卸数");
		titleList.add("ステータス");
		titleList.add("棚卸結果");
		csvFileCommon.downLoadCSVFile("棚卸一覧", titleList, list);

		return "";
	}

	/**
	 * 備品を取得処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/getShedGoods")
	public String getGoods(@ModelAttribute(name = "si000V02Form") SI000V02Form form, BindingResult result) {

		return "si000V02";
	}

}