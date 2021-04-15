package com.cbs.controller;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbs.common.CookieUtils;
import com.cbs.common.EdsUtils;
import com.cbs.entity.UserMasterEntity;
import com.cbs.form.LoginForm;
import com.cbs.service.US000V01Service;

/**
 * ログインユー画面のコントロール
 */
@Controller
public class LoginController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private EdsUtils edsUtils;

	@Autowired
	private US000V01Service service;

	/**
	 * 描画前処理
	 * 
	 * @param form    画面フォーム
	 * @param session セッション
	 */
	@ModelAttribute(name = "us000V01Form")
	public LoginForm prerender() {
		LoginForm form = new LoginForm();
		return form;
	}

	/**
	 * 初期化処理
	 * 
	 * @param form    フォーム
	 * @param request リクエスト情報
	 * @throws MessagingException
	 */
	@RequestMapping("/")
	public ModelAndView init(@ModelAttribute(name = "us000V01Form") LoginForm form, HttpServletRequest request)
			throws MessagingException {
		ModelAndView model = new ModelAndView();
		model.setViewName("us000V01");

		Cookie loginID = CookieUtils.get(request, "loginID");
		Cookie password = CookieUtils.get(request, "password");

		if (loginID != null && password != null) {
			form.setUsername(loginID.getValue());
			form.setPassword(password.getValue());
			form.setRememberMe(true);
		}

		return model;
	}

	/**
	 * 登録処理
	 * 
	 * @param form     フォーム
	 * @param result   チェック結果
	 * @param session  セッション
	 * @param response レスポンス
	 */
	@RequestMapping("/login")
	public String login(@Valid @ModelAttribute(name = "us000V01Form") LoginForm form, BindingResult result,
			HttpSession session, HttpServletResponse response) {
		
		if (result.hasErrors()) {
			return "us000V01";
		}

//		String edsPassword = edsUtils.encryptBasedDes(form.getPassword());
//		UserMasterEntity userInfo = service.select(form.getUsername(), edsPassword);
		UserMasterEntity userInfo = service.select(form.getUsername(), form.getPassword());
		if (ObjectUtils.isEmpty(userInfo)) {
			result.reject("", messageSource.getMessage("US000V01.MS0001", new Object[] {}, null));
			return "us000V01";
		} else {

			// cookieクリア
			CookieUtils.set(response, "loginID", null, 0);
			CookieUtils.set(response, "password", null, 0);

			if (form.getRememberMe() != null && form.getRememberMe()) {
				// cookie
				CookieUtils.set(response, "loginID", userInfo.getUserID(), 7 * 24 * 60 * 60);
				CookieUtils.set(response, "password", form.getPassword(), 7 * 24 * 60 * 60);
			}

			session.setAttribute("loginID", userInfo.getUserID());
			session.setAttribute("loginName", userInfo.getUserName());
			session.setAttribute("userAuth", userInfo.getUserAuth());
		}

		return "redirect:/home";
	}

	/**
	 * ログアウト処理
	 * 
	 * @param session セッション
	 */
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("loginID");
		session.removeAttribute("loginName");
		session.removeAttribute("userAuth");
		session.removeAttribute("IS000V01Form");
		session.removeAttribute("OS000V02Form");
		session.removeAttribute("PS000V02Form");
		session.removeAttribute("SI000V02Form");
		session.removeAttribute("SL000V01Form");
		session.removeAttribute("WM000V02Form");
		return "redirect:/";
	}
}