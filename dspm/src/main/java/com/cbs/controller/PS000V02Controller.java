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

import com.cbs.form.PS000V02DetailInfo;
import com.cbs.form.PS000V02Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.CsvFileCommon;
import com.cbs.service.PS000V02Service;
import com.cbs.service.PageCommon;

/**
 * 入庫一覧のコントロール
 */
@Controller
public class PS000V02Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private PageCommon pageCommon;

	@Autowired
	private CsvFileCommon csvFileCommon;

	@Autowired
	private PS000V02Service ps000V02Service;

	/**
	 * 描画前処理
	 *
	 * @param form    画面フォーム
	 */
	@ModelAttribute(name = "ps000V02Form")
	public PS000V02Form prerender(@ModelAttribute(name = "ps000V02Form") PS000V02Form form) {
		// 倉庫
		form.setStoregeList(bussinessCommon.getStoregeList());
		// カテゴリー
		form.setCategoryCodeList(bussinessCommon.getCategoryList());
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		// 棚卸担当者
		form.setUserCodeList(bussinessCommon.getUserList());
		// ステータス
		form.setCodeList(bussinessCommon.getCodeList("3"));

		return form;

	}

	/**
	 * 初期化処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/initListPS")
	public ModelAndView init(@ModelAttribute(name = "ps000V02Form") PS000V02Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.addObject("leftMenuClass", "initListPS");

		form.setPageNo(BigDecimal.ONE);
		// 画面表示List
		form.setStoringList(ps000V02Service.search(form));
		// 全部データリスト
		List<PS000V02DetailInfo> list = ps000V02Service.selectAll(form);
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

		model.setViewName("ps000V02");

		session.removeAttribute("PS000V02Form");
		session.setAttribute("PS000V02Form", form);
		return model;
	}

	/**
	 * 検索処理
	 *
	 * @param form   画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/searchPS")
	public String search(@Valid @ModelAttribute(name = "ps000V02Form") PS000V02Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "searchPS");

		if (result.hasErrors()) {
			return "si000V02";
		}

		// 入庫日FROMは入庫日TOより場合
		if (form.getStoringDateFrom() != null && form.getStoringDateEnd() != null
				&& form.getStoringDateFrom().compareTo(form.getStoringDateEnd()) > 0) {
			result.reject("", messageSource.getMessage("com.date.compare", new Object[] { "入庫日FROM", "入庫日TO" }, null));
			return "ps000V02";
		}

		form.setPageNo(BigDecimal.ONE);
		form = search(form);

		session.setAttribute("PS000V02Form", form);

		return "ps000V02";
	}

	/**
	 * 更新後の検索処理
	 *
	 * @param result チェック結果
	 */
	@RequestMapping("/updSearchPS")
	public String searchUpd(@ModelAttribute(name = "ps000V02Form") PS000V02Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "updSearchPS");

		PS000V02Form sessionForm = (PS000V02Form) session.getAttribute("PS000V02Form");
		form.setStoregeCode(sessionForm.getStoregeCode());
		form.setStStatus(sessionForm.getStStatus());
		form.setCategoryCode(sessionForm.getCategoryCode());
		form.setGoodsCode(sessionForm.getGoodsCode());
		form.setStoringDateFrom(sessionForm.getStoringDateFrom());
		form.setStoringDateEnd(sessionForm.getStoringDateEnd());
		form.setUserCode(sessionForm.getUserCode());
		form.setPageNo(sessionForm.getPageNo());
		form = search(form);

		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		return "ps000V02";
	}

	/**
	 * 検索共通
	 *
	 * @param form 画面フォーム
	 * @return 表示項目
	 */
	private PS000V02Form search(PS000V02Form form) {
		// 画面表示List
		form.setStoringList(ps000V02Service.search(form));
		// 全部データリスト
		List<PS000V02DetailInfo> list = ps000V02Service.selectAll(form);
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
	 * @param form
	 * @return
	 */
	@RequestMapping("/deletePS")
	public String delete(@ModelAttribute(name = "ps000V02Form") PS000V02Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "deletePS");
		Boolean checkResult = ps000V02Service.delete(form.getId(), result);
		if (!checkResult) {
			return "ps000V02";
		}
		// 全部データリスト
		List<PS000V02DetailInfo> list = ps000V02Service.selectAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		// 総ページ数
		BigDecimal sumPage = pageCommon.getSumPage(list.size(), form.getPageSize());
		form.setSumPage(sumPage);

		if (sumPage.compareTo(BigDecimal.ZERO) == 0) {
			return "ps000V02";
		}

		if (sumPage.compareTo(form.getPageNo()) < 0) {
			form.setPageNo(sumPage);
		}
		// 画面表示List
		form.setStoringList(ps000V02Service.search(form));

		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		session.setAttribute("PS000V02Form", form);

		return "ps000V02";

	}

	/**
	 * 一覧ダウンロード処理
	 * @param session セッション
	 */
	@RequestMapping("/storingCsvDown")
	@ResponseBody
	public String csvDown(HttpSession session) {

		PS000V02Form form = (PS000V02Form) session.getAttribute("PS000V02Form");

		// 全部データリスト
		List<PS000V02DetailInfo> list = ps000V02Service.selectAll(form);
		List<String> titleList = new ArrayList<>();
		titleList.add("NO");
		titleList.add("倉庫名");
		titleList.add("カテゴリー");
		titleList.add("備品名");
		titleList.add("入庫者");
		titleList.add("入庫日");
		titleList.add("入庫数");
		titleList.add("入庫単位");
		titleList.add("入庫理由");
		titleList.add("入庫ステータス");
		csvFileCommon.downLoadCSVFile("入庫一覧", titleList, list);

		return "";
	}

	/**
	 * 改ページ処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/storingPage")
	public ModelAndView getPage(@ModelAttribute(name = "ps000V02Form") PS000V02Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName("ps000V02");

		PS000V02Form sessionForm = (PS000V02Form) session.getAttribute("PS000V02Form");
		sessionForm.setPageNo(form.getPageNo());
		session.setAttribute("PS000V02Form", sessionForm);

		// 画面表示List
		form.setStoringList(ps000V02Service.search(form));
		// 全部データリスト
		List<PS000V02DetailInfo> list = ps000V02Service.selectAll(form);
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
}
