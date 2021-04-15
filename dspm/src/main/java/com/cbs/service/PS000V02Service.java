package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.cbs.dao.PS000V02Mapper;
import com.cbs.entity.StoringEntity;
import com.cbs.form.PS000V02DetailInfo;
import com.cbs.form.PS000V02Form;

/**
 * 入庫一覧のサービス
 */
@Service
@Transactional
public class PS000V02Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PS000V02Mapper ps000V02Mapper;
	
	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * 入庫検索(表示リスト)
	 * @param 画面フォーム
	 * @return 入庫詳細情報
	 */
	public List<PS000V02DetailInfo> search(PS000V02Form form) {
		BigDecimal offset = form.getPageNo().subtract(BigDecimal.ONE).multiply(form.getPageSize());

		List<PS000V02DetailInfo> resultList = ps000V02Mapper.search(offset, form.getPageSize(),
				form.getStoregeCode(), form.getCategoryCode(), form.getGoodsCode(), form.getUserCode(),
				form.getStStatus(), form.getStoringDateFrom(), form.getStoringDateEnd());

		return resultList;
	}

	/**
	 * 入庫全部データリスト
	 *
	 * @param フォーム
	 * @return 全部リスト
	 */
	public List<PS000V02DetailInfo> selectAll(PS000V02Form form) {
		return ps000V02Mapper.selectAll(form.getStoregeCode(), form.getCategoryCode(), form.getGoodsCode(),
				form.getUserCode(),
				form.getStStatus(), form.getStoringDateFrom(), form.getStoringDateEnd());
	}

	/**
	 * 入庫削除
	 *
	 * @param ID
	 * @param 全部入庫結果
	 */
	public Boolean delete(BigDecimal id, BindingResult result) {

		//入庫情報取得
		StoringEntity invertory = ps000V02Mapper.select(id);
		if (invertory == null) {
			result.reject("", messageSource.getMessage("com.del.fail", new Object[] {}, null));
			return false;
		}
		
		//写真消去
		String[] photoList = null;
		if(invertory.getPhoto() != null) {
			photoList = invertory.getPhoto().split(",");
		}
		if(photoList != null) {
			fileUploadService.deleteFile(photoList, "storing");
		}

		//入庫卸情報削除
		int count = ps000V02Mapper.deleteStoring(id);
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.del.fail", new Object[] {}, null));
			return false;
		}

		return true;
	}
}
