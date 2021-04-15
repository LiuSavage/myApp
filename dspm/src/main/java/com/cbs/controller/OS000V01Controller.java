package com.cbs.controller;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.cbs.entity.StockEntity;
import com.cbs.entity.StockingEntity;
import com.cbs.form.OS000V01Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.OS000V01Service;

/**
 * 出庫入力のコントロール
 *
 */
@Controller
public class OS000V01Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private OS000V01Service os000V01Service;

	/**
	 * 描画前処理
	 */
	@ModelAttribute(name = "os000V01Form")
	public OS000V01Form prerender(@ModelAttribute(name = "os000V01Form") OS000V01Form form, HttpSession session) {
		// 倉庫名
		form.setStoregeList(bussinessCommon.getStoregeList());
		// カテゴリー
		form.setCategoryCodeList(bussinessCommon.getCategoryList());
		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		// 棚卸担当者
		form.setUserCode(session.getAttribute("loginID").toString());
		form.setUserCodeList((bussinessCommon.getUserList()));
		// ステータス
		form.setCodeList(bussinessCommon.getCodeList("4"));
		// 出庫理由リスト
		form.setReasonList(bussinessCommon.getCodeList("2"));
		// 単位
		form.setUnitList(bussinessCommon.getCodeList("7"));
		return form;
	}

	/**
	 * 初期化処理
	 * @param form フォーム
	 * @param チェック結果
	 */
	@RequestMapping("/initOS")
	public ModelAndView StockingDetails(@ModelAttribute(name = "os000V01Form") OS000V01Form form,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("os000V01");
		model.addObject("leftMenuClass", "initOS");

		// 参考 :1 更新 :2
		if ((StringUtils.equals(form.getKbn(), "1") || StringUtils.equals(form.getKbn(), "2"))
				&& form.getId() != null) {

			StockingEntity stockingResult = os000V01Service.select(form.getId());

			form.setStoregeCode(stockingResult.getStoregeCode());
			form.setCategoryCode(stockingResult.getCategoryCode());
			form.setGoodsCode(stockingResult.getGoodsCode());
			form.setUserCode(stockingResult.getUserCode());
			form.setStockingScheduled(stockingResult.getStockingScheduled());
			form.setStockingReceipt(stockingResult.getStockingReceipt());
			form.setStExpiration(stockingResult.getStExpiration());
			form.setSerialNumber(stockingResult.getSerialNumber());
			form.setStPrice(stockingResult.getStPrice());
			form.setStockingQuantity(stockingResult.getStockingQuantity());
			form.setStockingUnit(stockingResult.getStockingUnit());
			form.setStAmount(stockingResult.getStAmount());
			form.setStockingReason(stockingResult.getStockingReason());
			form.setStockingStatus(stockingResult.getStockingStatus());
			form.setStComment(stockingResult.getStComment());
			//在庫写真
			if (StringUtils.equals(form.getKbn(), "1")) {
				StockEntity stockInfo = os000V01Service.selectStockPhoto(stockingResult);
				if (stockInfo != null) {
					if (stockInfo.getPhoto() != null) {
						String[] photoList = stockInfo.getPhoto().split(",");
						form.setPhotoList(photoList);
					}
				}
			}

			form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		}
		return model;
	}

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 * @param result  チェック結果
	 * @param session  セッション
	 * @throws MessagingException
	 **/
	@RequestMapping(value = "/insertST", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute(name = "os000V01Form") OS000V01Form form, BindingResult result,
			HttpSession session, Model model) throws MessagingException {

		model.addAttribute("leftMenuClass", "insertST");

		if (result.hasErrors()) {
			return "os000V01";
		}

		// 出庫日は当日以降設定
		if (form.getStockingReceipt().compareTo(LocalDate.now()) < 0) {
			result.reject("", messageSource.getMessage("OS000V01.MS0002", null, null));
			return "os000V01";
		}

		// 倉庫移動理由
		if (form.getStockingReason().equals("1002")) {
			result.reject("", messageSource.getMessage("OS000V01.MS0001", null, null));
			return "os000V01";
		}

		boolean checkResult = os000V01Service.regter(form, session, result);
		if (!checkResult) {
			return "os000V01";
		}

		return "redirect:/stockingList";
	}

	/**
	 * 更新処理
	 * @param form   画面フォーム
	 * @param result チェック結果
	 * @param session セッション
	 * @throws MessagingException
	 */
	@RequestMapping("/updateST")
	public String edit(@Valid @ModelAttribute(name = "os000V01Form") OS000V01Form form, BindingResult result,
			HttpSession session, Model model) throws MessagingException {

		model.addAttribute("leftMenuClass", "updateST");

		if (result.hasErrors()) {
			return "os000V01";
		}

		// 出庫日は当日以降設定
		if (form.getStockingReceipt().compareTo(LocalDate.now()) < 0) {
			result.reject("", messageSource.getMessage("OS000V01.MS0002", null, null));
			return "os000V01";
		}

		// 倉庫移動理由
		if (form.getStockingReason().equals("1002")) {
			result.reject("", messageSource.getMessage("OS000V01.MS0001", null, null));
			return "os000V01";
		}

		boolean checkResult = os000V01Service.update(form, session, result);
		if (!checkResult) {
			return "os000V01";
		}

		return "redirect:/updStockingSearch";
	}

	/**
	 * 備品を取得処理
	 */
	@RequestMapping("/getStockingGoods")
	public String getStockingGoods(@ModelAttribute(name = "os000V01Form") OS000V01Form form) {

		return "os000V01";
	}
}
