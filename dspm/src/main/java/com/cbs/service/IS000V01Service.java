package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbs.dao.IS000V01Mapper;
import com.cbs.form.IS000V01DetailInfo;
import com.cbs.form.IS000V01Form;

/**
 * 在庫一覧のサービス
 */
@Service
@Transactional
public class IS000V01Service {

	@Autowired
	private IS000V01Mapper is000V01Mapper;

	/**
	 * 画面表示詳細
	 * @param form　画面フォーム
	 * @return 在庫結果
	 */
	public List<IS000V01DetailInfo> search(IS000V01Form form) {
		BigDecimal offset = form.getPageNo().subtract(BigDecimal.ONE).multiply(form.getPageSize());

		return is000V01Mapper.selectGroupby(offset, form.getPageSize(), form.getStoregeCode(), form.getStockStatus(),
				form.getCategoryCode(), form.getGoodsCode(), form.getExpirationFrom(), form.getExpirationEnd());
	}

	/**
	 * すべての在庫情報
	 * @param form　画面フォーム
	 * @return 在庫情報
	 */
	public List<IS000V01DetailInfo> selectAll(IS000V01Form form) {
		return is000V01Mapper.selectAll(form);
	}
}
