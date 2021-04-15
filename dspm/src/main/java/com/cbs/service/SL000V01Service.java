package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbs.dao.SL000V01Mapper;
import com.cbs.form.SL000V01DetailInfo;
import com.cbs.form.SL000V01Form;

/**
 * 賞味期限一覧のサービス
 */
@Service
@Transactional
public class SL000V01Service {

	@Autowired
	private SL000V01Mapper sl000V01Mapper;

	/**
	 * 賞味期限検索(表示リスト)
	 *
	 * @param form　画面フォーム
	 * @return 在庫情報
	 */
	public List<SL000V01DetailInfo> search(SL000V01Form form) {
		BigDecimal offset = form.getPageNo().subtract(BigDecimal.ONE).multiply(form.getPageSize());

		return sl000V01Mapper.search(offset, form.getPageSize(), form.getStoregeCode(),
				form.getCategoryCode(), form.getGoodsCode(), form.getExpirationFrom(), form.getExpirationEnd());
	}

	/**
	 * すべての検索情報
	 * @param form フォーム
	 * @return 在庫情報
	 */
	public List<SL000V01DetailInfo> selectAll(SL000V01Form form) {
		return sl000V01Mapper.selectAll(form);
	}
}
