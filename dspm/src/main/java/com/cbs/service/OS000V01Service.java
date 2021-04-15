package com.cbs.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.cbs.dao.OS000V01Mapper;
import com.cbs.entity.StockEntity;
import com.cbs.entity.StockingEntity;
import com.cbs.form.OS000V01Form;

/**
 * 出庫入力のサービス
 */
@Service
@Transactional
public class OS000V01Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private OS000V01Mapper os000V01Mapper;

	@Autowired
	private EmailCommon emailCommon;

	/**
	 * 出庫情報を検索
	 * @param id
	 */
	public StockingEntity select(BigDecimal id) {
		return os000V01Mapper.select(id);
	}

	/**
	 * 登録処理
	 * @param form    画面フォーム
	 * @param session セッション
	 * @param result  チェック結果
	 * @return エラーあるかどうか
	 * @throws MessagingException
	 */
	public boolean regter(OS000V01Form form, HttpSession session, BindingResult result) throws MessagingException {

		//在庫データを検索
		StockEntity stockInfo = os000V01Mapper.selectStock(form.getStoregeCode(), form.getCategoryCode(),
				form.getGoodsCode(), form.getStPrice(), form.getStExpiration(), form.getStockingUnit());

		//在庫備品あり
		if (stockInfo == null) {
			result.reject("", messageSource.getMessage("OS000V01.MS0004", new Object[] {}, null));
			return false;
		}

		//在庫数は出庫数より大きい
		if (stockInfo.getStockNumber() != null
				&& stockInfo.getStockNumber().compareTo(form.getStockingQuantity()) < 0) {
			result.reject("", messageSource.getMessage("OS000V01.MS0005", new Object[] {}, null));
			return false;
		}

		//出庫登録
		StockingEntity param = copyStocking(form, session);
		int count = os000V01Mapper.insert(param);
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.reg.fail", null, null));
			return false;
		}

		//在庫の在庫数を更新
		boolean resultFlg = stockUpd(form, session, "com.reg.fail", result);
		if (!resultFlg) {
			return false;
		}

		//ステータス：入庫完了の場合
		if (form.getStockingStatus().equals("1011")) {
			//送信
			emailCommon.sendMail("出庫", form.getUserCode(), form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getStockingQuantity(), form.getStockingUnit(), BigDecimal.ZERO, "",
					session);
		}

		return true;
	}

	/**
	 * 更新処理
	 * @param form    画面フォーム
	 * @param session セッション
	 * @param result  チェック結果
	 * @return エラーあるかどうか
	 * @throws MessagingException
	 */
	public boolean update(OS000V01Form form, HttpSession session, BindingResult result) throws MessagingException {

		//在庫データを検索
		StockEntity stockInfo = os000V01Mapper.selectStock(form.getStoregeCode(), form.getCategoryCode(),
				form.getGoodsCode(), form.getStPrice(), form.getStExpiration(), form.getStockingUnit());

		//在庫備品あり
		if (stockInfo == null) {
			result.reject("", messageSource.getMessage("OS000V01.MS0004", new Object[] {}, null));
			return false;
		}

		//在庫数は出庫数より大きい
		if (stockInfo.getStockNumber() != null
				&& stockInfo.getStockNumber().compareTo(form.getStockingQuantity()) < 0) {
			result.reject("", messageSource.getMessage("OS000V01.MS0005", new Object[] {}, null));
			return false;
		}

		//出庫検索
		StockingEntity stockingInfo = os000V01Mapper.select(form.getId());
		if (stockingInfo == null) {
			result.reject("", messageSource.getMessage("com.upd.fail", null, null));
			return false;
		}

		//元データ削除
		int count = os000V01Mapper.delete(form.getId());
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.upd.fail", null, null));
			return false;
		}

		// 出庫登録
		StockingEntity param = copyStocking(form, session);
		count = os000V01Mapper.insert(param);
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.upd.fail", null, null));
			return false;
		}

		//在庫の在庫数を更新
		boolean resultFlg = stockUpd(form, session, "com.reg.fail", result);
		if (!resultFlg) {
			return false;
		}

		//ステータス：入庫完了の場合
		if (form.getStockingStatus().equals("1011")) {
			//送信
			emailCommon.sendMail("出庫", form.getUserCode(), form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getStockingQuantity(), form.getStockingUnit(), BigDecimal.ONE, "",
					session);
		}

		return true;
	}

	/**
	 * 在庫の在庫数を更新
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param message メッセージ内容
	 * @param result チェック結果
	 * @return 登録結果
	 * @throws MessagingException
	 */
	private boolean stockUpd(OS000V01Form form, HttpSession session, String message, BindingResult result)
			throws MessagingException {
		//ステータス：入庫完了の場合
		if (form.getStockingStatus().equals("1011")) {
			//在庫データを検索
			StockEntity stockInfo = os000V01Mapper.selectStock(form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getStPrice(), form.getStExpiration(), form.getStockingUnit());

			//入庫前の備品数
			BigDecimal sumNumber = bussinessCommon.getSumStockNumber(form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode());

			//同じ在庫データが存在する場合
			if (stockInfo != null) {
				//元データ削除
				int count = os000V01Mapper.deleteStock(stockInfo.getId());
				if (count != 1) {
					result.reject("", messageSource.getMessage(message, null, null));
					return false;
				}
				//新規
				StockEntity stockparam = copyStock(form, session, sumNumber, stockInfo.getStockNumber());
				count = os000V01Mapper.insertStock(stockparam);
				if (count != 1) {
					result.reject("", messageSource.getMessage(message, null, null));
					return false;
				}

				return true;
			}

			//同じ在庫データが存在しない場合
			StockEntity stockparam = copyStock(form, session, sumNumber, BigDecimal.ZERO);
			int count = os000V01Mapper.insertStock(stockparam);
			if (count != 1) {
				result.reject("", messageSource.getMessage(message, null, null));
				return false;
			}
		}

		return true;
	}

	/**
	 * 出庫登録項目
	 * @param form 画面フォーム
	 * @param session セッション
	 * @return 出庫情報
	 */
	private StockingEntity copyStocking(OS000V01Form form, HttpSession session) {
		StockingEntity param = new StockingEntity();
		param.setStoregeCode(form.getStoregeCode());
		param.setCategoryCode(form.getCategoryCode());
		param.setGoodsCode(form.getGoodsCode());
		param.setUserCode(form.getUserCode());
		param.setStockingScheduled(form.getStockingScheduled());
		param.setStockingReceipt(form.getStockingReceipt());
		param.setStExpiration(form.getStExpiration());
		param.setSerialNumber(form.getSerialNumber());
		param.setStPrice(form.getStPrice());
		param.setStockingQuantity(form.getStockingQuantity());
		param.setStockingUnit(form.getStockingUnit());
		param.setStAmount(form.getStPrice().multiply(form.getStockingQuantity()));
		param.setStockingReason(form.getStockingReason());
		param.setStockingStatus(form.getStockingStatus());
		param.setStComment(form.getStComment());
		param.setDelFlg(BigDecimal.ZERO);
		param.setInsertDate(LocalDateTime.now());
		param.setInsertUser(session.getAttribute("loginID").toString());
		param.setUpdateDate(LocalDateTime.now());
		param.setUpdateUser(session.getAttribute("loginID").toString());

		return param;
	}

	/**
	 * 在庫登録項目
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param sumNumber 入庫前の備品数
	 * @param stockNumber 在庫数
	 * @return 在庫情報
	 */
	private StockEntity copyStock(OS000V01Form form, HttpSession session, BigDecimal sumNumber,
			BigDecimal stockNumber) {
		StockEntity param = new StockEntity();
		param.setStoregeCode(form.getStoregeCode());
		param.setCategoryCode(form.getCategoryCode());
		param.setGoodsCode(form.getGoodsCode());
		param.setStockNumber(stockNumber.subtract(form.getStockingQuantity()));
		param.setStUnit(form.getStockingUnit());
		param.setStExpiration(form.getStExpiration());
		param.setSerialNumber(form.getSerialNumber());
		param.setStPrice(form.getStPrice());
		param.setStAmount(form.getStPrice().multiply(param.getStockNumber()));
		//在庫ステータス
		String status = bussinessCommon.getAmount(form.getStoregeCode(), form.getCategoryCode(),
				form.getGoodsCode(), sumNumber.subtract(form.getStockingQuantity()));
		param.setStockStatus(status);
		param.setStComment(form.getStComment());
		param.setPhoto("");
		param.setDelFlg(BigDecimal.ZERO);
		param.setInsertDate(LocalDateTime.now());
		param.setInsertUser(session.getAttribute("loginID").toString());
		param.setUpdateDate(LocalDateTime.now());
		param.setUpdateUser(session.getAttribute("loginID").toString());

		return param;
	}

	/**
	 * 在庫備品写真選択
	 * @param moveResult 画面フォーム
	 * @return 在庫備品情報
	 */
	public StockEntity selectStockPhoto(StockingEntity stockingResult) {
		StockEntity stockPhoto = os000V01Mapper.selectStockPhoto(stockingResult);
		return stockPhoto;
	}

}
