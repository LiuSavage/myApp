package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.cbs.dao.WM000V02Mapper;
import com.cbs.form.WM000V02Form;
import com.cbs.form.WM000V02Info;

/**
 * 倉庫間移動一覧のサービス
 */
@Service
@Transactional
public class WM000V02Service {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private WM000V02Mapper wm000V02Mapper;
	
	/**
	 * 倉庫間移動検索(表示リスト)
	 * @param form 画面フォーム
	 * @return 表示倉庫間移動結果
	 */
	public List<WM000V02Info> search(WM000V02Form form) {
		BigDecimal offset = form.getPageNo().subtract(BigDecimal.ONE).multiply(form.getPageSize());

		return wm000V02Mapper.search(offset,form.getPageSize(),form.getStoregeCode(), form.getStoregeCode2(),
				form.getCategoryCode(), form.getGoodsCode(), form.getSheUser(), form.getMovingDayFrom(), 
				form.getMovingDayTo(), form.getStatus());
	}

	/**
	 * 倉庫間移動検索(全部リスト)
	 * @param form 画面フォーム
	 * @return 倉庫間移動全部リスト
	 */
	public List<WM000V02Info> searchAll(WM000V02Form form) {

		return wm000V02Mapper.searchAll(form);
	}

	/**
	 * 倉庫移動情報削除
	 * @param stockingId　出庫ID
	 * @param storingId　入庫ID
	 */
	public Boolean delete(BigDecimal stockingId, BigDecimal storingId, BindingResult result) {
		//移動元倉庫情報取得
		WM000V02Info stockingInfo = wm000V02Mapper.selectInfo(stockingId);
		if (stockingInfo == null) {
			result.reject("", messageSource.getMessage("com.del.fail", new Object[] {}, null));
			return false;
		}

		//倉庫移動情報削除
		int kCount = wm000V02Mapper.stockingDelete(stockingId);
		int rCount = wm000V02Mapper.storingDelete(storingId);
		if (kCount != 1 && rCount != 1) {
			result.reject("", messageSource.getMessage("com.del.fail", new Object[] {}, null));
			return false;
		}

		return true;
	}


}
