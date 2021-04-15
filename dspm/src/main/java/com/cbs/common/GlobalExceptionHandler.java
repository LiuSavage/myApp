package com.cbs.common;

import java.io.IOException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView runtimeExceptionHandler(RuntimeException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView nullPointerExceptionHandler(NullPointerException ex) {
	    System.err.println("NullPointerException:");
	    return resultFormat(ex);
	}

	@ExceptionHandler(ClassCastException.class)
	public ModelAndView classCastExceptionHandler(ClassCastException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler(IOException.class)
	public ModelAndView iOExceptionHandler(IOException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler(NoSuchMethodException.class)
	public ModelAndView noSuchMethodExceptionHandler(NoSuchMethodException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ModelAndView indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ModelAndView requestNotReadable(HttpMessageNotReadableException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({TypeMismatchException.class})
	public ModelAndView requestTypeMismatch(TypeMismatchException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({MissingServletRequestParameterException.class})
	public ModelAndView requestMissingServletRequest(MissingServletRequestParameterException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ModelAndView request405(HttpRequestMethodNotSupportedException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
	public ModelAndView request406(HttpMediaTypeNotAcceptableException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
	public ModelAndView server500(RuntimeException ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({StackOverflowError.class})
	public ModelAndView requestStackOverflow(StackOverflowError ex) {
	    return resultFormat(ex);
	}

	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView arithmeticException(ArithmeticException ex) {
	    return resultFormat(ex);
	}


	@ExceptionHandler({Exception.class})
	public ModelAndView exception(Exception ex) {
	    return resultFormat(ex);
	}

	private <T> ModelAndView resultFormat(T ex) {
	    ModelAndView model = new ModelAndView();

		model.addObject("errorMessage", "システムエラーです。管理者へご連絡してください。");
		model.setViewName("/common/error");
		return model;
	}

}
