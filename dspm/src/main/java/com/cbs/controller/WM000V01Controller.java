package com.cbs.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.mail.MessagingException;
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

import com.cbs.entity.StockEntity;
import com.cbs.form.WM000V01Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.WM000V01Service;

/**
 * 倉庫間移動入力のコントロール
 */
@Controller
public class WM000V01Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private WM000V01Service wm000V01Service;

	/**
	 * 描画前処理
	 *
	 * @param form　画面フォーム
	 * @param session　セッション
	 */
	@ModelAttribute(name = "wm000V01Form")
	public WM000V01Form prerender(@ModelAttribute(name = "wm000V01Form") WM000V01Form form, HttpSession session) {
		// 倉庫名
		form.setStoregeList(bussinessCommon.getStoregeList());
		// カテゴリー
		form.setCategoryList(bussinessCommon.getCategoryList());
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		// 担当者
		form.setSheUser(session.getAttribute("loginID").toString());
		form.setSheUserList(bussinessCommon.getUserList());
		// 単位
		form.setCodeList(bussinessCommon.getCodeList("7"));
		// ステータス
		form.setStatusList(bussinessCommon.getCodeList("9"));

		return form;
	}

	/**
	 * 備品を取得処理
	 *
	 * @param form　画面フォーム
	 */
	@RequestMapping("/getGoodsWM")
	public String getGood(@ModelAttribute(name = "wm000V01Form") WM000V01Form form) {

		return "wm000V01";
	}

	/**
	 * 初期化処理
	 *
	 * @param form　画面フォーム
	 * @param session　セッション
	 */
	@RequestMapping("/moveInWM")
	public ModelAndView init(@ModelAttribute(name = "wm000V01Form") WM000V01Form form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("wm000V01");
		model.addObject("leftMenuClass", "moveInWM");

		// 参考(1)、更新(2)
		if ((StringUtils.equals(form.getKbn(), "1") || StringUtils.equals(form.getKbn(), "2"))
				&& form.getStockingId() != null) {
			// 備品情報取得

			WM000V01Form moveResult = wm000V01Service.select(form.getStockingId());
			form.setStoregeCode(moveResult.getStoregeCode());
			form.setStoregeCode2(moveResult.getStoregeCode2());
			form.setCategoryCode(moveResult.getCategoryCode());
			form.setGoodsCode(moveResult.getGoodsCode());
			form.setSheUser(moveResult.getSheUser());
			form.setMovingDay(moveResult.getMovingDay());
			form.setMovingNumber(moveResult.getMovingNumber());
			form.setStockingUnit(moveResult.getStockingUnit());
			form.setStatus(moveResult.getStatus());
			form.setComment(moveResult.getComment());
			//在庫写真
			if (StringUtils.equals(form.getKbn(), "1")) {
				StockEntity stockInfo = wm000V01Service.selectStockPhoto(moveResult);
				if (stockInfo.getPhoto() != null) {
					String[] photoList = stockInfo.getPhoto().split(",");
					form.setPhotoList(photoList);
				}
			}

			// 備品
			form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		}
		return model;
	}

	/**
	 * 登録処理
	 *
	 * @param form　画面フォーム
	 * @param session　セッション
	 * @param result　チェック結果
	 * @throws MessagingException
	 */
	@RequestMapping("/regterWM")
	public String regter(@Valid @ModelAttribute(name = "wm000V01Form") WM000V01Form form, BindingResult result,
			HttpSession session, Model model) throws MessagingException {

		model.addAttribute("leftMenuClass", "regterWM");

		if (result.hasErrors()) {
			return "wm000V01";
		}

		if (form.getStoregeCode().equals(form.getStoregeCode2())) {
			result.reject("", messageSource.getMessage("WM000V01.MS0003", null, null));
			return "wm000V01";
		}
		// 移動数
		BigDecimal movingNum = form.getMovingNumber();

		// 在庫数
		BigDecimal stockNum = wm000V01Service.stockNum(form);
		//在庫備品あり
		if (stockNum == null) {
			result.reject("", messageSource.getMessage("WM000V01.MS0001", new Object[] {}, null));
			return "wm000V01";
		}
		//在庫数は出庫数より大きい
		if (stockNum != null && stockNum.compareTo(movingNum) < 0) {
			result.reject("", messageSource.getMessage("WM000V01.MS0002", new Object[] { stockNum }, null));
			return "wm000V01";
		}

		// 在庫List
		List<StockEntity> wm000V01List = wm000V01Service.stockList(form);

		if (wm000V01List != null && wm000V01List.size() > 0) {
			//倉庫間移動操作
			Boolean checkResult = wm000V01Service.InsertMove(form, wm000V01List, session, result);
			if (!checkResult) {
				return "wm000V01";
			}
		}

		return "redirect:/moveList";
	}

	/**
	 * 更新処理
	 *
	 * @param form　画面フォーム
	 * @param session　セッション
	 * @param result　チェック結果
	 * @throws MessagingException
	 */
	@RequestMapping("/updateWM")
	public String updateWM(@Valid @ModelAttribute(name = "wm000V01Form") WM000V01Form form, BindingResult result,
			HttpSession session, Model model) throws MessagingException {

		model.addAttribute("leftMenuClass", "updateWM");
		if (result.hasErrors()) {
			return "wm000V01";
		}
		// 移動数
		BigDecimal movingNum = form.getMovingNumber();

		// 在庫数
		BigDecimal stockNum = wm000V01Service.stockNum(form);
		if (stockNum == null || stockNum.compareTo(movingNum) < 0) {
			if (stockNum == null) {
				stockNum = new BigDecimal("0");
			}
			result.reject("", messageSource.getMessage("WM000V01.MS0001", new Object[] { stockNum }, null));
			return "wm000V01";
		}
		// 在庫List
		List<StockEntity> wm000V01List = wm000V01Service.stockList(form);

		if (wm000V01List != null && wm000V01List.size() > 0) {
			//倉庫間移動更新操作
			Boolean checkResult = wm000V01Service.updateMove(form, wm000V01List, session, result);
			if (!checkResult) {
				return "wm000V01";
			}
		}

		return "redirect:/moveUpdSearch";
	}

}
