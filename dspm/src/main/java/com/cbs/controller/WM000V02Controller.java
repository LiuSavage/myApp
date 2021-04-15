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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cbs.form.WM000V02Form;
import com.cbs.form.WM000V02Info;
import com.cbs.service.BussinessCommon;
import com.cbs.service.CsvFileCommon;
import com.cbs.service.PageCommon;
import com.cbs.service.WM000V02Service;

/**
 * 倉庫間移動一覧のコントロール
 */
@Controller
public class WM000V02Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private WM000V02Service service;

	@Autowired
	private PageCommon pageCommon;

	@Autowired
	private CsvFileCommon csvFileCommon;

	@Autowired
	private BussinessCommon bussinessCommon;

	/**
	 * 描画前処理
	 * 
	 * @param form 画面フォーム
	 */
	@ModelAttribute(name = "wm000V02Form")
	public WM000V02Form prerender(@ModelAttribute(name = "wm000V02Form") WM000V02Form form) {
		// 倉庫名
		form.setStoregeList(bussinessCommon.getStoregeList());
		// カテゴリー
		form.setCategoryList(bussinessCommon.getCategoryList());
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		// 担当者
		form.setSheUserList(bussinessCommon.getUserList());
		// ステータス
		form.setStatusList(bussinessCommon.getCodeList("9"));
		return form;
	}

	/**
	 * 備品を取得処理
	 * 
	 * @param form 画面フォーム
	 */
	@RequestMapping("/getGoodsMoving")
	public String getGood(@ModelAttribute(name = "wm000V02Form") WM000V02Form form) {

		return "wm000V02";
	}

	/**
	 * 初期化処理
	 * 
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@RequestMapping("/moveList")
	public ModelAndView init(@ModelAttribute(name = "wm000V02Form") WM000V02Form form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("wm000V02");
		model.addObject("leftMenuClass", "moveList");

		form.setPageNo(BigDecimal.ONE);
		// 画面表示List
		form.setInfoList(service.search(form));
		// 全部データリスト
		List<WM000V02Info> list = service.searchAll(form);
		// 検索件数
		form.setSumCount(new BigDecimal(list.size()));
		// 総ページ数
		if (list != null && list.size() > 0 && list.size() <= 60) {
			form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		} else if (list != null && list.size() > 0 && list.size() > 60) {
			form.setSumPage(pageCommon.getSumPage(60, form.getPageSize()));
		}

		form.setSumPage(pageCommon.getSumPage(list.size(), form.getPageSize()));
		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		model.setViewName("wm000V02");

		// セッション削除
		session.removeAttribute("WM000V02Form");
		session.setAttribute("WM000V02Form", form);

		return model;

	}

	/**
	 * 検索処理
	 * 
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 */
	@RequestMapping("/moveSearch")
	public String moveSearch(@Valid @ModelAttribute(name = "wm000V02Form") WM000V02Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "moveSearch");

		if (result.hasErrors()) {
			return "wm000V02";
		}

		// 移動日FROMは移動日TOより大きい場合
		if (form.getMovingDayFrom() != null && form.getMovingDayTo() != null
				&& form.getMovingDayFrom().compareTo(form.getMovingDayTo()) > 0) {
			result.reject("", messageSource.getMessage("com.date.compare", new Object[] { "移動日FROM", "移動日TO" }, null));
			return "wm000V02";
		}

		form.setPageNo(BigDecimal.ONE);
		// 検索共通
		form = updAfterSearch(form);

		session.setAttribute("WM000V02Form", form);

		return "wm000V02";
	}

	/**
	 * 更新後の検索処理
	 * 
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 */
	@RequestMapping("/moveUpdSearch")
	public String moveUpdSearch(@ModelAttribute(name = "wm000V02Form") WM000V02Form form, BindingResult result,
			HttpSession session, Model model) {
		model.addAttribute("leftMenuClass", "moveUpdSearch");

		WM000V02Form sessionForm = (WM000V02Form) session.getAttribute("WM000V02Form");
		form.setStoregeCode(sessionForm.getStoregeCode());
		form.setStoregeCode2(sessionForm.getStoregeCode2());
		form.setCategoryCode(sessionForm.getCategoryCode());
		form.setGoodsCode(sessionForm.getGoodsCode());
		form.setSheUser(sessionForm.getSheUser());
		form.setMovingDayFrom(sessionForm.getMovingDayFrom());
		form.setMovingDayTo(sessionForm.getMovingDayTo());
		form.setStatus(sessionForm.getStatus());
		form.setPageNo(sessionForm.getPageNo());
		// 検索共通
		form = updAfterSearch(form);
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		return "wm000V02";
	}

	/**
	 * 検索共通
	 * 
	 * @param form 画面フォーム
	 * @return 表示項目
	 */
	private WM000V02Form updAfterSearch(WM000V02Form form) {
		// 画面表示List
		form.setInfoList(service.search(form));
		// 全部データリスト
		List<WM000V02Info> list = service.searchAll(form);
		// 検索件数
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
	 * ページ
	 * 
	 * @param form 画面フォーム
	 * @param result チェック結果
	 * @param session セッション
	 */
	@PostMapping("/pageWM000V02")
	public ModelAndView getPage(@ModelAttribute(name = "wm000V02Form") WM000V02Form form, BindingResult result,
			HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName("wm000V02");
		// 検索共通
		form = updAfterSearch(form);

		session.setAttribute("WM000V02Form", form);

		return model;
	}

	/**
	 * 削除処理
	 * 
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 */
	@RequestMapping("/deleteWM")
	public String delete(@ModelAttribute(name = "wm000V02Form") WM000V02Form form, BindingResult result,
			HttpSession session, Model model) {

		model.addAttribute("leftMenuClass", "deleteWM");

		WM000V02Form sessionForm = (WM000V02Form) session.getAttribute("WM000V02Form");
		form.setStoregeCode(sessionForm.getStoregeCode());
		form.setStoregeCode2(sessionForm.getStoregeCode2());
		form.setCategoryCode(sessionForm.getCategoryCode());
		form.setGoodsCode(sessionForm.getGoodsCode());
		form.setSheUser(sessionForm.getSheUser());
		form.setMovingDayFrom(sessionForm.getMovingDayFrom());
		form.setMovingDayTo(sessionForm.getMovingDayTo());
		form.setStatus(sessionForm.getStatus());
		form.setPageNo(sessionForm.getPageNo());

		Boolean checkResult = service.delete(form.getStockingId(), form.getStoringId(), result);
		if (!checkResult) {
			return "wm000V02";
		}

		// 全部データリスト
		List<WM000V02Info> list = service.searchAll(form);
		// 検索件数
		form.setSumCount(new BigDecimal(list.size()));
		// 総ページ数
		BigDecimal sumPage = pageCommon.getSumPage(list.size(), form.getPageSize());
		form.setSumPage(sumPage);
		
		if (sumPage.compareTo(BigDecimal.ZERO) == 0) {
			return "wm000V02";
		}

		if (sumPage.compareTo(form.getPageNo()) < 0) {
			form.setPageNo(sumPage);
		}
		// 画面表示List
		form.setInfoList(service.search(form));

		// 表示開始ページ数
		form.setStartPage(pageCommon.getStarPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));
		// 表示終了ページ数
		form.setEndPage(pageCommon.getEndPageNo(form.getSumPage(), form.getPageNo(), form.getSize()));

		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));

		session.setAttribute("WM000V02Form", form);

		return "wm000V02";
	}

	/**
	 * CSV ダウンロード
	 * 
	 * @param session セッション
	 */
	@RequestMapping("/csvDownWM000V02")
	@ResponseBody
	public String csvDown(HttpSession session) {

		ModelAndView model = new ModelAndView();
		model.setViewName("wm000V02");

		WM000V02Form form = (WM000V02Form) session.getAttribute("WM000V02Form");

		// 全部データリスト
		List<WM000V02Info> sumCnt = service.searchAll(form);

		List<String> titleList = new ArrayList<>();
		titleList.add("NO");
		titleList.add("移動先倉庫名");
		titleList.add("移動元倉庫名");
		titleList.add("カテゴリー名");
		titleList.add("備品名");
		titleList.add("移動担当者名");
		titleList.add("移動日");
		titleList.add("移動数");
		titleList.add("ステータス");
		titleList.add("備考");

		csvFileCommon.downLoadCSVFile("倉庫間移動", titleList, sumCnt);

		return "";
	}

}
