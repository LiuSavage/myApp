package com.cbs.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Error404Controller implements ErrorController {

	@RequestMapping(value = "/error")
	public ModelAndView error(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 500){
            model.addObject("errorMessage", "システムエラーです。管理者へご連絡してください。");
        }else if(statusCode == 404){
        	model.addObject("errorCode", statusCode);
        	model.addObject("errorMessage", "申し訳ありません。ページが見つかりません。");
        }else if(statusCode == 403){
        	model.addObject("errorMessage", "システムエラーです。管理者へご連絡してください。");
        }else{
        	model.addObject("errorMessage", "システムエラーです。管理者へご連絡してください。");
        }
        model.setViewName("/common/error");
        return model;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
