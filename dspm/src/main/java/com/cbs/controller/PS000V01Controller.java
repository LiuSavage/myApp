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

import com.cbs.entity.StoringEntity;
import com.cbs.form.PS000V01Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.PS000V01Service;

/**
 * 入庫入力のコントロール
 *
 */
@Controller
public class PS000V01Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private PS000V01Service ps000V01Service;

	/**
	 * 描画前処理
	 */
	@ModelAttribute(name = "ps000V01Form")
	public PS000V01Form prerender(@ModelAttribute(name = "ps000V01Form") PS000V01Form form, HttpSession session) {
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
		form.setCodeList(bussinessCommon.getCodeList("3"));
		// 単位
		form.setUnitList(bussinessCommon.getCodeList("7"));
		// 理由
		form.setResonList(bussinessCommon.getCodeList("1"));
		return form;
	}

	/**
	 * 初期化処理
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 */
	@RequestMapping("/initPS")
	public ModelAndView StoringDetails(@ModelAttribute(name = "ps000V01Form") PS000V01Form form, HttpSession session,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("ps000V01");
		model.addObject("leftMenuClass", "initPS");

		// 参考 :1 更新 :2
		if ((StringUtils.equals(form.getKbn(), "1") || StringUtils.equals(form.getKbn(), "2"))
				&& form.getId() != null) {

			StoringEntity storingResult = ps000V01Service.select(form.getId());
			form.setStoregeCode(storingResult.getStoregeCode());
			form.setCategoryCode(storingResult.getCategoryCode());
			form.setGoodsCode(storingResult.getGoodsCode());
			form.setUserCode(storingResult.getUserCode());
			form.setStScheduled(storingResult.getStScheduled());
			form.setStReceipt(storingResult.getStReceipt());
			form.setStExpiration(storingResult.getStExpiration());
			form.setSerialNumber(storingResult.getSerialNumber());
			form.setStPrice(storingResult.getStPrice());
			form.setStQuantity(storingResult.getStQuantity());
			form.setStUnit(storingResult.getStUnit());
			form.setStAmount(storingResult.getStAmount());
			form.setStReason(storingResult.getStReason());
			form.setStStatus(storingResult.getStStatus());
			form.setStComment(storingResult.getStComment());
			form.setPhoto(storingResult.getPhoto());
			if(form.getStReason().equals("1002")) {
				form.setPhoto(ps000V01Service.selectStockPhoto(form.getId()));
			}
			if(form.getPhoto() != null) {
				String[] photoList = form.getPhoto().split(",");
				form.setPhotoList(photoList);
			}

			form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));
		}
		return model;
	}

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 * @throws MessagingException
	 */
	@RequestMapping(value = "/regterPS", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute(name = "ps000V01Form") PS000V01Form form, BindingResult result,
			HttpSession session, Model model) throws MessagingException {

		model.addAttribute("leftMenuClass", "regterPS");

		if (result.hasErrors()) {
			return "ps000V01";
		}

		// 入庫日は当日以降設定
		if (form.getStReceipt().compareTo(LocalDate.now()) < 0) {
			result.reject("", messageSource.getMessage("PS000V01.MS0002", null, null));
			return "ps000V01";
		}

		// 賞味／消費期限は当日以降設定
		if (form.getStExpiration().compareTo(LocalDate.now()) < 0) {
			result.reject("", messageSource.getMessage("PS000V01.MS0003", null, null));
			return "ps000V01";
		}

		//入庫理由は倉庫移動の場合
		if (form.getStReason().equals("1002")) {
			result.reject("", messageSource.getMessage("PS000V01.MS0001", null, null));
			return "ps000V01";
		}

		boolean checkResult = ps000V01Service.insert(form, session, result);
		if (!checkResult) {
			return "ps000V01";
		}

		return "redirect:/initListPS";
	}

	/**
	 * 更新処理
	 *
	 * @param form   画面フォーム
	 * @param result チェック結果
	 * @throws MessagingException
	 */
	@RequestMapping("/updatePS")
	public String edit(@Valid @ModelAttribute(name = "ps000V01Form") PS000V01Form form, BindingResult result,
			HttpSession session, Model model) throws MessagingException {

		model.addAttribute("leftMenuClass", "updatePS");

		if (result.hasErrors()) {
			return "ps000V01";
		}

		// 入庫日は当日以降設定
		if (form.getStReceipt().compareTo(LocalDate.now()) < 0) {
			result.reject("", messageSource.getMessage("PS000V01.MS0002", null, null));
			return "ps000V01";
		}

		// 賞味／消費期限は当日以降設定
		if (form.getStExpiration().compareTo(LocalDate.now()) < 0) {
			result.reject("", messageSource.getMessage("PS000V01.MS0003", null, null));
			return "ps000V01";
		}

		//入庫理由は倉庫移動の場合
		if (form.getStReason().equals("1002")) {
			result.reject("", messageSource.getMessage("PS000V01.MS0001", null, null));
			return "ps000V01";
		}

		boolean checkResult = ps000V01Service.update(form, session, result);
		if (!checkResult) {
			return "ps000V01";
		}
		return "redirect:/updSearchPS";
	}

	/**
	 * 備品を取得処理
	 */
	@RequestMapping("/getStoringGoods")
	public String getStoringGoods(@ModelAttribute(name = "ps000V01Form") PS000V01Form form) {

		// 備品
		form.setGoodsList(bussinessCommon.getGoodsList(form.getCategoryCode()));

		return "ps000V01";
	}

}
