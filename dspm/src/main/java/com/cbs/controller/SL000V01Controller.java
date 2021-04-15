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

import com.cbs.form.SL000V01DetailInfo;
import com.cbs.form.SL000V01Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.CsvFileCommon;
import com.cbs.service.PageCommon;
import com.cbs.service.SL000V01Service;

/**
 * 賞味期限一覧のコントロール
 */
@Controller
public class SL000V01Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private PageCommon pageCommon;

	@Autowired
	private CsvFileCommon csvFileCommon;

	@Autowired
	private SL000V01Service service;

	/**
	 * 描画前処理
	 *
	 * @param form    画面フォーム
	 * @param session セッション
	 */
	@ModelAttribute(name = "sl000V01Form")
	public SL000V01Form prerender(@ModelAttribute(name = "sl000V01Form") SL000V01Form form) {
		// 倉庫
		form.setStoregeList(bussinessCommon.getStoregeList());
		// カテゴリー
		form.setCategoryCodeList(bussinessCommon.getCategoryList());
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		return form;

	}

	/**
	 * 初期化処理
	 * @param form   画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/stockExpirationList")
	public ModelAndView init(@ModelAttribute(name = "sl000V01Form") SL000V01Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.addObject("leftMenuClass", "stockExpirationList");

		form.setPageNo(BigDecimal.ONE);
		// 画面表示List
		form.setStockList(service.search(form));
		// 全部データリスト
		List<SL000V01DetailInfo> list = service.selectAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));

		// 総ページ数
		if (list != null && list.size() > 0 && list.size() <= 60) {
			form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		} else if (list != null && list.size() > 0 && list.size() > 60) {
			form.setSumPage(pageCommon.getSumPage(60, form.getPageSize()));
		} else {
			form.setSumPage(BigDecimal.ZERO);
		}
		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		model.setViewName("sl000V01");

		session.removeAttribute("SL000V01Form");
		session.setAttribute("SL000V01Form", form);
		return model;
	}

	/**
	 * 改ページ処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/stockExpirationListPage")
	public ModelAndView getPage(@ModelAttribute(name = "sl000V01Form") SL000V01Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName("sl000V01");

		SL000V01Form sessionForm = (SL000V01Form) session.getAttribute("SL000V01Form");
		sessionForm.setPageNo(form.getPageNo());
		session.setAttribute("SL000V01Form", sessionForm);

		form = search(form);

		return model;
	}

	/**
	 * 検索処理
	 * @param form   画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/stockExpirationSearch")
	public String search(@Valid @ModelAttribute(name = "sl000V01Form") SL000V01Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "stockExpirationSearch");

		if (result.hasErrors()) {
			return "sl000V01";
		}

		// 賞味/消費期限FROMは賞味/消費期限TOより場合
		if (form.getExpirationFrom() != null && form.getExpirationEnd() != null
				&& form.getExpirationFrom().compareTo(form.getExpirationEnd()) > 0) {
			result.reject("",
					messageSource.getMessage("com.date.compare", new Object[] { "賞味/消費期限FROM", "賞味/消費期限TO" }, null));
			return "sl000V01";
		}

		form.setPageNo(BigDecimal.ONE);
		form = search(form);

		session.setAttribute("SL000V01Form", form);
		return "sl000V01";
	}

	/**
	 * 検索共通
	 * @param form 画面フォーム
	 * @return 表示項目
	 */
	private SL000V01Form search(SL000V01Form form) {
		// 画面表示List
		form.setStockList(service.search(form));
		// 全部データリスト
		List<SL000V01DetailInfo> list = service.selectAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		// 総ページ数
		form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		return form;
	}

	/**
	 * ダウンロード処理
	 * @param session セッション
	 */
	@RequestMapping("/stockExpirationCsvDown")
	@ResponseBody
	public String StockcsvDown(HttpSession session) {

		SL000V01Form form = (SL000V01Form) session.getAttribute("SL000V01Form");

		// 全部データリスト
		List<SL000V01DetailInfo> list = service.selectAll(form);
		List<String> titleList = new ArrayList<>();
		titleList.add("NO");
		titleList.add("倉庫名");
		titleList.add("カテゴリー");
		titleList.add("備品名");
		titleList.add("賞味/消費期限");
		titleList.add("在庫数");
		titleList.add("在庫単位");
		titleList.add("期限切れ(日)");
		csvFileCommon.downLoadCSVFile("賞味消費期限一覧", titleList, list);

		return "";
	}

	/**
	 * 備品を取得処理
	 */
	@RequestMapping("/getStockGoods")
	public String getStockGoods(@ModelAttribute(name = "sl000V01Form") SL000V01Form form) {
		return "sl000V01";
	}

}
