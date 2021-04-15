package com.cbs.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.cbs.dao.SI000V01Mapper;
import com.cbs.entity.InvertoryEntity;
import com.cbs.form.SI000V01Form;

/**
 * 棚卸入力のサービス
 */
@Service
@Transactional
public class SI000V01Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SI000V01Mapper SI000V01Mapper;

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 * @return エラーあるかどうか
	 */
	public Boolean insert(SI000V01Form form, HttpSession session, BindingResult result) {

		InvertoryEntity param = copyInvertory(form, session);
		int count = SI000V01Mapper.insert(param);
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
			return false;
		}

		return true;
	}

	/**
	 * 棚卸情報取得
	 * @param id ID
	 * @return 棚卸情報
	 */
	public InvertoryEntity select(BigDecimal id) {

		//検索
		return SI000V01Mapper.select(id);
	}

	/**
	 * 更新処理
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 * @return エラーあるかどうか
	 */
	public Boolean update(SI000V01Form form, HttpSession session, BindingResult result) {

		//元データ削除
		int count = SI000V01Mapper.delete(form.getId());
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.upd.fail", new Object[] {}, null));
			return false;
		}
		//更新データの登録
		InvertoryEntity param = copyInvertory(form, session);
		count = SI000V01Mapper.insert(param);
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.upd.fail", new Object[] {}, null));
			return false;
		}

		return true;
	}

	/**
	 * 登録項目
	 * @param form 画面フォーム
	 * @param session セッション
	 * @return 棚卸情報
	 */
	private InvertoryEntity copyInvertory(SI000V01Form form, HttpSession session) {
		InvertoryEntity param = new InvertoryEntity();
		param.setStoregeCode(form.getStoregeCode());
		param.setCategoryCode(form.getCategoryCode());
		param.setGoodsCode(form.getGoodsCode());
		param.setUserCode(form.getSheUser());
		param.setStExpiration(form.getStExpiration());
		param.setStPrice(form.getStPrice());
		param.setStUnit(form.getStUnit());
		param.setStarInvertory(form.getStarInvertory());
		param.setEndInvertory(form.getEndInvertory());
		param.setInvertNumber(form.getInvertNumber());
		param.setStatus(form.getCode());
		param.setComment(form.getComment());
		param.setDelFlg(BigDecimal.ZERO);
		param.setInsertDate(LocalDateTime.now());
		param.setInsertUser(session.getAttribute("loginID").toString());
		param.setUpdateDate(LocalDateTime.now());
		param.setUpdateUser(session.getAttribute("loginID").toString());
		return param;
	}

}