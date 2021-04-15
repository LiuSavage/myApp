package com.cbs.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.cbs.common.EdsUtils;
import com.cbs.entity.UserMasterEntity;
import com.cbs.form.US000V02Form;
import com.cbs.service.US000V02Service;

/**
 * ユーザ登録画面のコントロール
 *
 */
@Controller
public class US000V02Controller {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private EdsUtils edsUtils;

	@Autowired
	private US000V02Service service;

	/**
	 * 描画前処理
	 * @param form 画面フォーム
	 * @param session セッション
	 */
	@ModelAttribute(name = "us000V02Form")
	public US000V02Form prerender(@ModelAttribute(name = "us000V02Form") US000V02Form form, HttpSession session) {
		//地域名
		form.setAreaList(service.getAreaList());

		//市区町村
		form.setCityList(service.getCityList(form.getUserArea()));

		return form;
	}

	/**
	 * 市区町村を取得処理
	 * @param form 画面フォーム
	 */
	@RequestMapping("/getCityUS")
	public String getCity(@ModelAttribute(name = "us000V02Form") US000V02Form form) {

		return "us000V02";
	}

	/**
	 * 初期化処理
	 * @param session セッション
	 * @param form 画面フォーム
	 */
	@RequestMapping("/initUS")
	public ModelAndView init(@ModelAttribute(name = "us000V02Form") US000V02Form form, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("us000V02");

		return model;
	}

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 * @param result チェック結果
	 */
	@RequestMapping("/regterUS")
	public String login(@Valid @ModelAttribute(name = "us000V02Form") US000V02Form form, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "us000V02";
		}
		if (!form.getUserPassword().equals(form.getCheckPassword())) {
			result.reject("", messageSource.getMessage("US000V02.MS0001", new Object[] {}, null));
			return "us000V02";
		}

		UserMasterEntity userInfo = service.getUserInfo(form.getUserId());
		if (userInfo != null) {
			result.reject("", messageSource.getMessage("US000V02.MS0002", new Object[] {}, null));
			return "us000V02";
		}

		form.setUserPassword(edsUtils.encryptBasedDes(form.getUserPassword()));

		service.insertUser(form, session);

		return "redirect:/";
	}

	/**
	 * 更新初期化
	 * @param form フォーム
	 * @param session セッション
	 */
	@RequestMapping("/updInitUS")
	public String updateInit(@ModelAttribute(name = "us000V02Form") US000V02Form form, HttpSession session) {

		UserMasterEntity userInfo = service.getUserInfo((String) session.getAttribute("loginID"));

		if (userInfo == null) {
			return "redirect:/";
		}

		form.setKbn("2");
		form.setUserId(userInfo.getUserID());
		form.setUserName(userInfo.getUserName());
		form.setUserPassword(edsUtils.decryptBasedDes(userInfo.getUserPassword()));
		form.setCheckPassword(edsUtils.decryptBasedDes(userInfo.getUserPassword()));
		form.setUserMail(userInfo.getEmail());
		form.setUserArea(StringUtils.substring(userInfo.getAreaID(), 0, 2));
		form.setUserCity(StringUtils.substring(userInfo.getAreaID(), 2));
		form.setAddress1(userInfo.getAddress1());

		//備品
		form.setCityList(service.getCityList(form.getUserArea()));

		return "us000V02";
	}

	/**
	 * 更新処理
	 * @param form フォーム
	 * @param session セッション
	 */
	@RequestMapping("/updateUS")
	public String update(@ModelAttribute(name = "us000V02Form") US000V02Form form, HttpSession session,
			BindingResult result) {

		if (result.hasErrors()) {
			return "us000V02";
		}
		if (!form.getUserPassword().equals(form.getCheckPassword())) {
			result.reject("", messageSource.getMessage("US000V02.MS0001", new Object[] {}, null));
			return "us000V02";
		}
		form.setUserId(session.getAttribute("loginID").toString());

		form.setUserPassword(edsUtils.encryptBasedDes(form.getUserPassword()));

		Boolean check = service.updateUser(form, session);
		if (check) {
			result.reject("", messageSource.getMessage("com.upd.success", new Object[] {}, null));
			return "us000V02";
		} else {
			form.setUserPassword(form.getCheckPassword());
			result.reject("", messageSource.getMessage("com.upd.fail", new Object[] {}, null));
			return "us000V02";
		}
	}

}
