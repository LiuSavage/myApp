package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.cbs.dao.OS000V02Mapper;
import com.cbs.entity.StockingEntity;
import com.cbs.form.OS000V02DetailInfo;
import com.cbs.form.OS000V02Form;

/**
 * 出庫一覧のサービス
 */
@Service
@Transactional
public class OS000V02Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private OS000V02Mapper os000V02Mapper;

	/**
	 * 出庫検索(表示リスト)
	 * @param 画面フォーム
	 * @return 検索結果
	 */
	public List<OS000V02DetailInfo> search(OS000V02Form form) {
		BigDecimal offset = form.getPageNo().subtract(BigDecimal.ONE).multiply(form.getPageSize());

		List<OS000V02DetailInfo> resultList = os000V02Mapper.search(offset, form.getPageSize(),
				form.getStoregeCode(), form.getCategoryCode(), form.getGoodsCode(), form.getUserCode(),
				form.getStockingStatus(), form.getStockingDateFrom(), form.getStockingDateEnd());

		return resultList;
	}

	/**
	 * 出庫全部データリスト
	 * @param フォーム
	 * @return 全部データリスト
	 */
	public List<OS000V02DetailInfo> selectAll(OS000V02Form form) {
		return os000V02Mapper.selectAll(form);
	}

	/**
	 * 出庫削除
	 *
	 * @param ID
	 */
	public boolean delete(OS000V02Form form, BindingResult result) {

		//出庫検索
		StockingEntity stockingInfo = os000V02Mapper.select(form.getId());
		if (stockingInfo == null) {
			result.reject("", messageSource.getMessage("com.del.fail", null, null));
			return false;
		}

		//元データ削除
		int count = os000V02Mapper.delete(form.getId());
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.del.fail", null, null));
			return false;
		}

		return true;
	}
}
