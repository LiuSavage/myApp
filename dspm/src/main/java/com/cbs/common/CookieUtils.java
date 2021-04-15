package com.cbs.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieUtils {

	/**
	* cookie作成
	* @param response HttpServletResponse
	* @param key cookieキー
	* @param value cookie内容
	* @param maxAge cookie期間
	*/
	public static void set(HttpServletResponse response,
			String key,
			String value,
			int maxAge) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * cookie検索
	 * @param request HttpServletRequest
	 * @param key cookieキー
	 * @return
	 */
	public static Cookie get(HttpServletRequest request,
			String key) {
		Map<String, Cookie> cookieMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		if (cookieMap.containsKey(key)) {
			return cookieMap.get(key);
		} else {
			return null;
		}
	}
}
