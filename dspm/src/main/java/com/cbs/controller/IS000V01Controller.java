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

import com.cbs.form.IS000V01DetailInfo;
import com.cbs.form.IS000V01Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.CsvFileCommon;
import com.cbs.service.IS000V01Service;
import com.cbs.service.PageCommon;

/**
 * 在庫一覧のコントロール
 */
@Controller
public class IS000V01Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PageCommon pageCommon;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private CsvFileCommon csvFileCommon;

	@Autowired
	private IS000V01Service service;

	/**
	 * 描画前処理
	 *
	 * @param form 画面フォーム
	 */
	@ModelAttribute(name = "is000V01Form")
	public IS000V01Form prerender(@ModelAttribute(name = "is000V01Form") IS000V01Form form) {
		// 倉庫
		form.setStoregeList(bussinessCommon.getStoregeList());
		// カテゴリー
		form.setCategoryCodeList(bussinessCommon.getCategoryList());
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		// ステータス
		form.setStockStatusList(bussinessCommon.getCodeList("10"));

		return form;
	}

	/**
	 * 初期化処理
	 * @param form　画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/initIS")
	public ModelAndView init(@ModelAttribute(name = "is000V01Form") IS000V01Form form, HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.addObject("leftMenuClass", "initIS");

		form.setPageNo(BigDecimal.ONE);
		// 画面表示リスト
		form.setStockList(service.search(form));
		// 全部データリスト
		//List<IS000V01DetailInfo> list = service.selectAll(form);
		//検索件数
//		form.setSumCount(new BigDecimal(list.size()));

		// 総ページ数
//		if (list != null && list.size() > 0 && list.size() <= 60) {
//			form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
//		} else if (list != null && list.size() > 0 && list.size() > 60) {
//			form.setSumPage(pageCommon.getSumPage(60, form.getPageSize()));
//		} else {
//			form.setSumPage(BigDecimal.ZERO);
//		}
		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		model.setViewName("is000V01");

		session.removeAttribute("IS000V01Form");
		session.setAttribute("IS000V01Form", form);
		return model;
	}

	/**
	 * 検索処理
	 * @param form   画面フォーム
	 * @param result チェック結果
	 * @param session セッション
	 */
	@RequestMapping("/searchIS")
	public String search(@Valid @ModelAttribute(name = "is000V01Form") IS000V01Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "searchIS");

		if (result.hasErrors()) {
			return "is000V01";
		}

		// 賞味/消費期限Fromは賞味/消費期限Toより大きい場合
		if (form.getExpirationFrom() != null && form.getExpirationEnd() != null
				&& form.getExpirationFrom().compareTo(form.getExpirationEnd()) > 0) {
			result.reject("",
					messageSource.getMessage("com.date.compare", new Object[] { "賞味/消費期限From", "賞味/消費期限To" }, null));
			return "is000V01";
		}

		form.setPageNo(BigDecimal.ONE);
		// 画面表示List
		form.setStockList(service.search(form));
		// 全部データリスト
		List<IS000V01DetailInfo> list = service.selectAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		// 総ページ数
		form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		session.setAttribute("IS000V01Form", form);

		return "is000V01";
	}

	/**
	 * 改ページ処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/stockPage")
	public ModelAndView getPage(@ModelAttribute(name = "is000V01Form") IS000V01Form form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("is000V01");

		IS000V01Form sessionForm = (IS000V01Form) session.getAttribute("IS000V01Form");
		sessionForm.setPageNo(form.getPageNo());
		session.setAttribute("IS000V01Form", sessionForm);

		// 画面表示List
		form.setStockList(service.search(form));
		// 全部データリスト
		List<IS000V01DetailInfo> list = service.selectAll(form);
		//検索件数
		form.setSumCount(new BigDecimal(list.size()));
		// 総ページ数
		form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		return model;
	}

	/**
	 * csv ダウンロード
	 */
	@RequestMapping("/stockCsvDown")
	@ResponseBody
	public String StockcsvDown(HttpSession session) {

		IS000V01Form form = (IS000V01Form) session.getAttribute("IS000V01Form");

		// 全部データリスト
		List<IS000V01DetailInfo> list = service.selectAll(form);
		List<String> titleList = new ArrayList<>();
		titleList.add("NO");
		titleList.add("倉庫名");
		titleList.add("カテゴリー");
		titleList.add("備品名");
		titleList.add("在庫数");
		titleList.add("在庫単位");
		titleList.add("単価/円");
		titleList.add("賞味／消費期限");
		titleList.add("在庫状況");
		csvFileCommon.downLoadCSVFile("在庫一覧", titleList, list);

		return "";
	}

	/**
	 * 備品を取得処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/getStockListGoods")
	public String getGoods(@ModelAttribute(name = "is000V01Form") IS000V01Form form) {

		return "is000V01";
	}
}
