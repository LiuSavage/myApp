package com.cbs.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import com.cbs.dao.SM000V01Mapper;
import com.cbs.entity.StockpileMasterEntity;
import com.cbs.entity.StoregeEntity;
import com.cbs.form.SM000V01DetailInfo;
import com.cbs.form.SM000V01Form;

/**
 * ファイル登録のサービス
 */
@Service
@Transactional
public class SM000V01Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SM000V01Mapper sm000V01Mapper;

	@Autowired
	private CsvFileCommon csvFileCommon;

	/**
	 * 登録処理
	 * @param form フォーム
	 * @param session セッション
	 * @param result チェック結果
	 * @return エラー結果
	 * @throws IOException
	 */
	public Boolean regter(SM000V01Form form, HttpSession session, BindingResult result) throws IOException {

		//ファイル内容を取得
		List<List<String>> fileList = csvFileCommon.readCSVFile(form.getFile().getInputStream());

		//成功データ
		List<SM000V01DetailInfo> successList = new ArrayList<>();
		//失敗データ
		List<SM000V01DetailInfo> failList = new ArrayList<>();

		if (fileList != null && fileList.size() > 1) {

			int index = 0;
			for (List<String> fieldList : fileList) {
				SM000V01DetailInfo success = null;
				SM000V01DetailInfo fail = null;

				if (fieldList != null && fieldList.size() > 0) {
					//タイトルを飛び出す。
					if (index == 0) {
						index++;
						continue;
					}

					//倉庫登録
					if (StringUtils.equals(form.getSelect(), "1021")) {

						if (fieldList.size() < 7 || StringUtils.isEmpty(fieldList.get(0))
								|| StringUtils.isEmpty(fieldList.get(1))
								|| StringUtils.isEmpty(fieldList.get(2)) || StringUtils.isEmpty(fieldList.get(3))
								|| StringUtils.isEmpty(fieldList.get(4)) || StringUtils.isEmpty(fieldList.get(5))
								|| StringUtils.isEmpty(fieldList.get(6))) {

							fail = new SM000V01DetailInfo();
							if (fieldList.size() < 1 || StringUtils.isEmpty(fieldList.get(0))) {
								fail.setCode("");
							} else {
								fail.setCode(fieldList.get(0));
							}
							if (fieldList.size() < 2 || StringUtils.isEmpty(fieldList.get(1))) {
								fail.setName("");
							} else {
								fail.setName(fieldList.get(1));
							}
							fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
							failList.add(fail);
							continue;
						}

						//検索
						String storegeCode = sm000V01Mapper.selectStorege(fieldList.get(0));

						if (!StringUtils.isEmpty(storegeCode)) {
							//削除
							int count = sm000V01Mapper.deleteStorege(fieldList.get(0));
							if (count != 1) {
								fail = new SM000V01DetailInfo();
								fail.setCode(fieldList.get(0));
								fail.setName(fieldList.get(1));
								fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
							}
						}

						StoregeEntity param = regStoregeInfo(fieldList, session);
						//ファイルのデータは不正
						if (param == null) {
							fail = new SM000V01DetailInfo();
							fail.setCode(fieldList.get(0));
							fail.setName(fieldList.get(1));
							fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
						} else {
							//登録
							int count = sm000V01Mapper.insertStorege(param);
							if (count != 1) {
								fail = new SM000V01DetailInfo();
								fail.setCode(fieldList.get(0));
								fail.setName(fieldList.get(1));
								fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
							} else if (count == 1) {
								success = new SM000V01DetailInfo();
								success.setCode(fieldList.get(0));
								success.setName(fieldList.get(1));
								success.setMessage(messageSource.getMessage("com.reg.success", new Object[] {}, null));
							}
						}

						//倉庫削除
					} else if (StringUtils.equals(form.getSelect(), "1022")) {

						//検索
						String storegeCode = sm000V01Mapper.selectStorege(fieldList.get(0));

						if (!StringUtils.isEmpty(storegeCode)) {
							//削除
							int count = sm000V01Mapper.deleteStorege(fieldList.get(0));
							if (count != 1) {
								fail = new SM000V01DetailInfo();
								fail.setCode(fieldList.get(0));
								fail.setName(fieldList.get(1));
								fail.setMessage(messageSource.getMessage("com.del.fail", new Object[] {}, null));
							} else {
								success = new SM000V01DetailInfo();
								success.setCode(fieldList.get(0));
								success.setName(fieldList.get(1));
								success.setMessage(messageSource.getMessage("com.del.success", new Object[] {}, null));
							}
						} else {
							fail = new SM000V01DetailInfo();
							fail.setCode(fieldList.get(0));
							fail.setName(fieldList.get(1));
							fail.setMessage(messageSource.getMessage("com.del.fail", new Object[] {}, null));
						}

						//備品登録
					} else if (StringUtils.equals(form.getSelect(), "1023")) {

						if (fieldList.size() < 5 || StringUtils.isEmpty(fieldList.get(0))
								|| StringUtils.isEmpty(fieldList.get(1))
								|| StringUtils.isEmpty(fieldList.get(2)) || StringUtils.isEmpty(fieldList.get(3))
								|| StringUtils.isEmpty(fieldList.get(4))) {

							fail = new SM000V01DetailInfo();
							if (fieldList.size() < 1 || StringUtils.isEmpty(fieldList.get(0))) {
								fail.setCode("");
							} else {
								fail.setCode(fieldList.get(0));
							}
							if (fieldList.size() < 2 || StringUtils.isEmpty(fieldList.get(1))) {
								fail.setName("");
							} else {
								fail.setName(fieldList.get(1));
							}
							fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
							failList.add(fail);
							continue;
						}

						//検索
						String categoryCode = sm000V01Mapper.selectStockpile(fieldList.get(0), fieldList.get(2));

						if (!StringUtils.isEmpty(categoryCode)) {
							//削除
							int count = sm000V01Mapper.deleteStockpile(fieldList.get(0), fieldList.get(2));
							if (count != 1) {
								fail = new SM000V01DetailInfo();
								fail.setCode(fieldList.get(0));
								fail.setName(fieldList.get(1));
								fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
							}
						}

						StockpileMasterEntity param = regStockpile(fieldList, session);
						if (param == null) {
							fail = new SM000V01DetailInfo();
							fail.setCode(fieldList.get(0));
							fail.setName(fieldList.get(1));
							fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
						} else {
							//登録
							int count = sm000V01Mapper.insertStockpile(param);
							if (count != 1) {
								fail = new SM000V01DetailInfo();
								fail.setCode(fieldList.get(0));
								fail.setName(fieldList.get(1));
								fail.setMessage(messageSource.getMessage("com.reg.fail", new Object[] {}, null));
							} else if (count == 1) {
								success = new SM000V01DetailInfo();
								success.setCode(fieldList.get(0));
								success.setName(fieldList.get(1));
								success.setMessage(messageSource.getMessage("com.reg.success", new Object[] {}, null));
							}
						}

						//備品削除
					} else if (StringUtils.equals(form.getSelect(), "1024")) {

						if (fieldList.size() < 3 || StringUtils.isEmpty(fieldList.get(0))
								|| StringUtils.isEmpty(fieldList.get(2))) {

							fail = new SM000V01DetailInfo();
							if (fieldList.size() < 1 || StringUtils.isEmpty(fieldList.get(0))) {
								fail.setCode("");
							} else {
								fail.setCode(fieldList.get(0));
							}
							if (fieldList.size() < 2 || StringUtils.isEmpty(fieldList.get(1))) {
								fail.setName("");
							} else {
								fail.setName(fieldList.get(1));
							}
							fail.setMessage(messageSource.getMessage("com.del.fail", new Object[] {}, null));
							failList.add(fail);
							continue;
						}

						//検索
						String categoryCode = sm000V01Mapper.selectStockpile(fieldList.get(0), fieldList.get(2));

						if (!StringUtils.isEmpty(categoryCode)) {
							//削除
							int count = sm000V01Mapper.deleteStockpile(fieldList.get(0), fieldList.get(2));
							if (count != 1) {
								fail = new SM000V01DetailInfo();
								fail.setCode(fieldList.get(0));
								fail.setName(fieldList.get(1));
								fail.setMessage(messageSource.getMessage("com.del.fail", new Object[] {}, null));
							} else {
								success = new SM000V01DetailInfo();
								success.setCode(fieldList.get(0));
								success.setName(fieldList.get(1));
								success.setMessage(messageSource.getMessage("com.del.success", new Object[] {}, null));
							}
						} else {
							fail = new SM000V01DetailInfo();
							fail.setCode(fieldList.get(0));
							fail.setName(fieldList.get(1));
							fail.setMessage(messageSource.getMessage("com.del.fail", new Object[] {}, null));
						}
					}

					if (success != null) {
						successList.add(success);
					}
					if (fail != null) {
						failList.add(fail);
					}
				}
			}

		} else {
			result.reject("", messageSource.getMessage("SM000V01.MS0003", new Object[] {}, null));
			return false;
		}

		//在庫、入庫、出庫を更新
		if (successList != null && successList.size() > 0) {
			sm000V01Mapper.updateStock();
			sm000V01Mapper.updateStoring();
			sm000V01Mapper.updateStocking();
			sm000V01Mapper.updateInvertory();
		}

		form.setSuccessList(successList);
		form.setFailList(failList);

		return true;
	}

	/**
	 * 倉庫情報
	 * @param fieldList ファイルデータ
	 * @param session セッション
	 * @return 倉庫情報
	 */
	private StoregeEntity regStoregeInfo(List<String> fieldList, HttpSession session) {
		StoregeEntity storege = new StoregeEntity();
		//倉庫コード
		storege.setStoregeCode(fieldList.get(0));
		//倉庫名
		storege.setStoregeName(fieldList.get(1));
		//タイプ
		if (isNumeric(fieldList.get(2))) {
			storege.setStType(new BigDecimal(fieldList.get(2)));
		} else {
			return null;
		}
		//地域コード
		if (isNumeric(fieldList.get(3))) {
			storege.setAreaID(new BigDecimal(fieldList.get(3)));
		} else {
			return null;
		}
		//設置住所
		storege.setSetAddress(fieldList.get(4));
		//担当者コード
		if (isNumeric(fieldList.get(5))) {
			storege.setUserCode(new BigDecimal(fieldList.get(5)));
		} else {
			return null;
		}
		//担当者
		storege.setUserName(fieldList.get(6));
		//削除フラグ
		storege.setDelFlg(BigDecimal.ZERO);
		//登録日
		storege.setInsertDate(LocalDateTime.now());
		//登録者
		storege.setInsertUser(session.getAttribute("loginID").toString());
		//更新日
		storege.setUpdateDate(LocalDateTime.now());
		//更新者
		storege.setUpdateUser(session.getAttribute("loginID").toString());
		return storege;
	}

	/**
	 * 備蓄品情報
	 * @param fieldList ファイルデータ
	 * @param session セッション
	 * @return 備蓄品情報
	 */
	private StockpileMasterEntity regStockpile(List<String> fieldList, HttpSession session) {
		StockpileMasterEntity stockpile = new StockpileMasterEntity();
		//カテゴリー
		stockpile.setCategoryCode(fieldList.get(0));
		//カテゴリー名
		stockpile.setCategoryName(fieldList.get(1));
		//備品コード
		stockpile.setGoodsCode(fieldList.get(2));
		//備品名
		stockpile.setGoodsName(fieldList.get(3));
		//一人用数量
		if (isNumeric(fieldList.get(4))) {
			stockpile.setAmount(new BigDecimal(fieldList.get(4)));
		} else {
			return null;
		}
		//削除フラグ
		stockpile.setDelFlg(BigDecimal.ZERO);
		//登録日
		stockpile.setInsertDate(LocalDateTime.now());
		//登録者
		stockpile.setInsertUser(session.getAttribute("loginID").toString());
		//更新日
		stockpile.setUpdateDate(LocalDateTime.now());
		//更新者
		stockpile.setUpdateUser(session.getAttribute("loginID").toString());
		return stockpile;
	}

	/**
	 * 判断数字
	 * @param str パラメータ
	 * @return 数字かどうか
	 */
	public boolean isNumeric(String str) {
		if (!StringUtils.isEmpty(str.trim()))
			return str.matches("^[0-9]*$");
		else
			return false;
	}

}