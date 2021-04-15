package com.cbs.controller;

import java.io.IOException;

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

import com.cbs.form.SM000V01Form;
import com.cbs.service.BussinessCommon;
import com.cbs.service.SM000V01Service;

/**
 * 棚卸入力のコントロール
 */
@Controller
public class SM000V01Controller {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private SM000V01Service service;

	/**
	 * 描画前処理
	 * @param form 画面フォーム
	 */
	@ModelAttribute(name = "sm000V01Form")
	public SM000V01Form prerender(@ModelAttribute(name = "sm000V01Form") SM000V01Form form) {
		//登録選択リスト
		form.setSelectList(bussinessCommon.getCodeList("8"));
		return form;
	}

	/**
	 * 初期化処理
	 * @param form 画面フォーム
	 */
	@RequestMapping("/initSM")
	public ModelAndView init(@ModelAttribute(name = "sm000V01Form") SM000V01Form form) {
		ModelAndView model = new ModelAndView();
		model.setViewName("sm000V01");
		model.addObject("leftMenuClass", "initSM");

		return model;
	}

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 * @param session セッション
	 * @throws IOException
	 */
	@RequestMapping("/regterSM")
	public String regter(@Valid @ModelAttribute(name = "sm000V01Form") SM000V01Form form, BindingResult result,
			HttpSession session, Model model) throws IOException {

		model.addAttribute("leftMenuClass", "regterSM");

		if (result.hasErrors()) {
			return "sm000V01";
		}

		if (form.getFile().isEmpty()) {
			if (StringUtils.equals(form.getSelect(), "1021") || StringUtils.equals(form.getSelect(), "1023")) {
				result.reject("", messageSource.getMessage("SM000V01.MS0001", new Object[] {}, null));
			} else if (StringUtils.equals(form.getSelect(), "1022") || StringUtils.equals(form.getSelect(), "1024")) {
				result.reject("", messageSource.getMessage("SM000V01.MS0002", new Object[] {}, null));
			}
			return "sm000V01";
		}

		//登録
		Boolean checkResult = service.regter(form, session, result);
		if (!checkResult) {
			return "sm000V01";
		}

		return "sm000V01";
	}

}