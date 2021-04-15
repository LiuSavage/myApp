package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.cbs.dao.SI000V02Mapper;
import com.cbs.entity.InvertoryEntity;
import com.cbs.form.SI000V02DetailInfo;
import com.cbs.form.SI000V02Form;

/**
 * 棚卸入力のサービス
 */
@Service
@Transactional
public class SI000V02Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SI000V02Mapper SI000V02Mapper;

	/**
	 * 棚卸検索(表示リスト)
	 * @param form 画面フォーム
	 * @return 表示棚卸結果
	 */
	public List<SI000V02DetailInfo> search(SI000V02Form form) {
		BigDecimal offset = form.getPageNo().subtract(BigDecimal.ONE).multiply(form.getPageSize());

		return SI000V02Mapper.search(offset, form.getPageSize(), form.getStoregeCode(), form.getGoodsCode(),
				form.getSheUser(), form.getStarInvertory(), form.getEndInvertory(), form.getShedStatus(),
				form.getCategoryCode(),form.getShedResult());
	}

	/**
	 * 棚卸検索(全部リスト)
	 * @param form 画面フォーム
	 * @return 全部棚卸結果
	 */
	public List<SI000V02DetailInfo> searchAll(SI000V02Form form) {

		return SI000V02Mapper.searchAll(form);
	}

	/**
	 * 棚卸削除
	 * @param ID
	 * @return 全部棚卸結果
	 */
	public Boolean delete(BigDecimal id, BindingResult result) {

		//棚卸情報取得
		InvertoryEntity invertory = SI000V02Mapper.select(id);
		if (invertory == null) {
			result.reject("", messageSource.getMessage("com.del.fail", new Object[] {}, null));
			return false;
		}

		//元棚卸情報削除
		int count = SI000V02Mapper.delete(id);
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.del.fail", new Object[] {}, null));
			return false;
		}

		return true;
	}

}