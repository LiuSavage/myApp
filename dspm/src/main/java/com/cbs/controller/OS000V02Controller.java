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

import com.cbs.form.OS000V02DetailInfo;
import com.cbs.form.OS000V02Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.CsvFileCommon;
import com.cbs.service.OS000V02Service;
import com.cbs.service.PageCommon;

/**
 * 出庫一覧のコントロール
 */
@Controller
public class OS000V02Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private PageCommon pageCommon;

	@Autowired
	private CsvFileCommon csvFileCommon;

	@Autowired
	private OS000V02Service service;

	/**
	 * 描画前処理
	 *
	 * @param form    画面フォーム
	 * @param session セッション
	 */
	@ModelAttribute(name = "os000V02Form")
	public OS000V02Form prerender(@ModelAttribute(name = "os000V02Form") OS000V02Form form) {
		// 倉庫
		form.setStoregeList(bussinessCommon.getStoregeList());
		// カテゴリー
		form.setCategoryCodeList(bussinessCommon.getCategoryList());
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		// 棚卸担当者
		form.setUserCodeList(bussinessCommon.getUserList());
		// ステータス
		form.setCodeList(bussinessCommon.getCodeList("4"));

		return form;

	}

	/**
	 * 初期化処理
	 * @param form   画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/stockingList")
	public ModelAndView init(@ModelAttribute(name = "os000V02Form") OS000V02Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();

		model.addObject("leftMenuClass", "stockingList");

		form.setPageNo(BigDecimal.ONE);
		// 画面表示List
		form.setStockingList(service.search(form));
		// 全部データリスト
		List<OS000V02DetailInfo> list = service.selectAll(form);
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

		model.setViewName("os000V02");

		session.removeAttribute("OS000V02Form");
		session.setAttribute("OS000V02Form", form);
		return model;
	}

	/**
	 * 検索処理
	 * @param form   画面フォーム
	 * @param result チェック結果
	 * @param session セッション
	 */
	@RequestMapping("/stockingSearch")
	public String search(@Valid @ModelAttribute(name = "os000V02Form") OS000V02Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "stockingSearch");

		if (result.hasErrors()) {
			return "os000V02";
		}

		// 出庫日FROMは出庫日TOより場合
		if (form.getStockingDateFrom() != null && form.getStockingDateEnd() != null
				&& form.getStockingDateFrom().compareTo(form.getStockingDateEnd()) > 0) {
			result.reject("", messageSource.getMessage("com.date.compare", new Object[] { "出庫日FROM", "出庫日TO" }, null));
			return "os000V02";
		}

		form.setPageNo(BigDecimal.ONE);
		form = search(form);

		session.setAttribute("OS000V02Form", form);

		return "os000V02";
	}

	/**
	 * 更新後の検索処理
	 * @param form   画面フォーム
	 * @param result チェック結果
	 * @param session セッション
	 */
	@RequestMapping("/updStockingSearch")
	public String updStockingSearch(@ModelAttribute(name = "os000V02Form") OS000V02Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "updStockingSearch");

		OS000V02Form sessionForm = (OS000V02Form) session.getAttribute("OS000V02Form");
		form.setStoregeCode(sessionForm.getStoregeCode());
		form.setStockingStatus(sessionForm.getStockingStatus());
		form.setCategoryCode(sessionForm.getCategoryCode());
		form.setGoodsCode(sessionForm.getGoodsCode());
		form.setStockingDateFrom(sessionForm.getStockingDateFrom());
		form.setStockingDateEnd(sessionForm.getStockingDateEnd());
		form.setUserCode(sessionForm.getUserCode());
		form.setPageNo(sessionForm.getPageNo());
		form = search(form);

		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		return "os000V02";
	}

	/**
	 * 検索共通
	 * @param form 画面フォーム
	 * @return 表示項目
	 */
	private OS000V02Form search(OS000V02Form form) {
		// 画面表示List
		form.setStockingList(service.search(form));
		// 全部データリスト
		List<OS000V02DetailInfo> list = service.selectAll(form);
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
	 * 削除処理
	 *
	 * @param form   画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/deleteStocking")
	public String delete(@ModelAttribute(name = "os000V02Form") OS000V02Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "deleteStocking");
		Boolean checkResult = service.delete(form, result);
		if (!checkResult) {
			return "os000V02";
		}

		// 全部データリスト
		List<OS000V02DetailInfo> list = service.selectAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		// 総ページ数
		BigDecimal sumPage = pageCommon.getSumPage(list.size(), form.getPageSize());
		form.setSumPage(sumPage);

		if (sumPage.compareTo(BigDecimal.ZERO) == 0) {
			return "os000V02";
		}

		if (sumPage.compareTo(form.getPageNo()) < 0) {
			form.setPageNo(sumPage);
		}
		// 画面表示List
		form.setStockingList(service.search(form));

		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		session.setAttribute("OS000V02Form", form);

		return "os000V02";
	}

	/**
	 * 改ページ処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/stockingPage")
	public ModelAndView getPage(@ModelAttribute(name = "os000V02Form") OS000V02Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName("os000V02");

		OS000V02Form sessionForm = (OS000V02Form) session.getAttribute("OS000V02Form");
		sessionForm.setPageNo(form.getPageNo());
		session.setAttribute("OS000V02Form", sessionForm);

		form = search(form);

		return model;
	}

	/**
	 * 一覧ダウンロード処理
	 * @param session セッション
	 */
	@RequestMapping("/stockingCsvDown")
	@ResponseBody
	public String csvDown(HttpSession session) {

		OS000V02Form form = (OS000V02Form) session.getAttribute("OS000V02Form");

		// 全部データリスト
		List<OS000V02DetailInfo> list = service.selectAll(form);
		List<String> titleList = new ArrayList<>();
		titleList.add("NO");
		titleList.add("倉庫名");
		titleList.add("カテゴリー");
		titleList.add("備品名");
		titleList.add("出庫者");
		titleList.add("出庫日");
		titleList.add("出庫数");
		titleList.add("出庫単位");
		titleList.add("出庫理由");
		titleList.add("出庫ステータス");
		csvFileCommon.downLoadCSVFile("出庫一覧", titleList, list);

		return "";
	}

	/**
	 * 備品を取得処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/getSTListGoods")
	public String getGoods(@ModelAttribute(name = "os000V02Form") OS000V02Form form) {

		return "os000V02";
	}

}
