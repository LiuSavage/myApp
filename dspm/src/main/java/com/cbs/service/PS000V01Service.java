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
import org.springframework.web.multipart.MultipartFile;

import com.cbs.dao.PS000V01Mapper;
import com.cbs.entity.StockEntity;
import com.cbs.entity.StoringEntity;
import com.cbs.form.PS000V01Form;

/**
 * 入庫入力画面のサービス
 */
@Service
@Transactional
public class PS000V01Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private PS000V01Mapper ps000V01Mapper;

	@Autowired
	private EmailCommon emailCommon;

	/**
	 * IDで検索
	 *
	 * @param id 入庫ID
	 * @return 入庫情報
	 */
	public StoringEntity select(BigDecimal id) {
		return ps000V01Mapper.select(id);
	}

	/**
	 * 登録処理
	 * @param form 画面フォーム
	 * @param session セッション
	 * @param result チェック結果
	 * @return 登録結果
	 * @throws MessagingException
	 */
	public boolean insert(PS000V01Form form, HttpSession session, BindingResult result) throws MessagingException {

		//入庫写真アップロード
		MultipartFile[] files = form.getFiles();

		if (files != null) {
			String photo = "";
			if (files.length > 0) {
				if (form.getStStatus().equals("1011")) {
					photo = fileUploadService.FilesUpload(files, "stock");
				} else {
					photo = fileUploadService.FilesUpload(files, "storing");
				}
			}
			if (photo.equals("")) {
				photo = null;
			}
			form.setPhoto(photo);
		}

		// 入庫登録
		StoringEntity param = copyStoring(form, session);
		int count = ps000V01Mapper.insertStoring(param);
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
		if (form.getStStatus().equals("1011")) {
			//送信
			emailCommon.sendMail("入庫", form.getUserCode(), form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getStQuantity(), form.getStUnit(), BigDecimal.ZERO, "", session);
		}

		return true;
	}

	/**
	 * 更新処理
	 *
	 * @param 画面フォーム
	 * @param セッション
	 * @param チェック結果
	 * @return 更新結果
	 * @throws MessagingException
	 */
	public boolean update(PS000V01Form form, HttpSession session, BindingResult result) throws MessagingException {

		StoringEntity storingInfo = ps000V01Mapper.select(form.getId());
		if (storingInfo == null) {
			result.reject("", messageSource.getMessage("com.upd.fail", null, null));
			return false;
		}
		int count = ps000V01Mapper.deleteStoring(form.getId());
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.upd.fail", null, null));
			return false;
		}

		//入庫写真消除
		if (form.getDelFile() != null) {
			fileUploadService.deleteFile(form.getDelFile(), "storing");
		}

		//入庫写真更新
		String photo = "";
		MultipartFile[] files = form.getFiles();
		if (files != null) {
			if (files.length > 0) {
				if (form.getStStatus().equals("1011")) {
					photo = fileUploadService.FilesUpload(files, "stock");
				} else {
					photo = fileUploadService.FilesUpload(files, "storing");
				}
			}
		}
		if (form.getPhotoList() != null) {
			for (int i = 0; i < form.getPhotoList().length; i++) {
				if (form.getStStatus().equals("1011")) {
					fileUploadService.storingFileCopy(form.getPhotoList()[i]);
				}
				if (photo.equals("")) {
					photo = form.getPhotoList()[i];
				} else {
					photo = photo + "," + form.getPhotoList()[i];
				}
			}
		}
		if (photo.equals("")) {
			photo = null;
		}
		form.setPhoto(photo);
		// 入庫登録
		StoringEntity param = copyStoring(form, session);
		count = ps000V01Mapper.insertStoring(param);
		if (count != 1) {
			result.reject("", messageSource.getMessage("com.upd.fail", null, null));
			return false;
		}

		//在庫の在庫数を更新
		boolean resultFlg = stockUpd(form, session, "com.upd.fail", result);
		if (!resultFlg) {
			return false;
		}

		//ステータス：入庫完了の場合
		if (form.getStStatus().equals("1011")) {
			//送信
			emailCommon.sendMail("入庫", form.getUserCode(), form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getStQuantity(), form.getStUnit(), BigDecimal.ONE, "", session);
		}

		return true;
	}

	/**
	 * 入庫登録項目
	 * @param form 画面フォーム
	 * @param session セッション
	 * @return 入庫情報
	 */
	private StoringEntity copyStoring(PS000V01Form form, HttpSession session) {
		StoringEntity param = new StoringEntity();
		param.setStoregeCode(form.getStoregeCode());
		param.setCategoryCode(form.getCategoryCode());
		param.setGoodsCode(form.getGoodsCode());
		param.setUserCode(form.getUserCode());
		param.setStScheduled(form.getStScheduled());
		param.setStReceipt(form.getStReceipt());
		param.setStExpiration(form.getStExpiration());
		param.setSerialNumber(form.getSerialNumber());
		param.setStPrice(form.getStPrice());
		param.setStQuantity(form.getStQuantity());
		param.setStUnit(form.getStUnit());
		param.setStAmount(form.getStPrice().multiply(form.getStQuantity()));
		param.setStReason(form.getStReason());
		param.setStStatus(form.getStStatus());
		param.setStComment(form.getStComment());
		param.setPhoto(form.getPhoto());
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
	private StockEntity copyStock(PS000V01Form form, HttpSession session, BigDecimal sumNumber,
			BigDecimal stockNumber) {
		StockEntity param = new StockEntity();
		param.setStoregeCode(form.getStoregeCode());
		param.setCategoryCode(form.getCategoryCode());
		param.setGoodsCode(form.getGoodsCode());
		param.setStockNumber(form.getStQuantity().add(stockNumber));
		param.setStUnit(form.getStUnit());
		param.setStExpiration(form.getStExpiration());
		param.setSerialNumber(form.getSerialNumber());
		param.setStPrice(form.getStPrice());
		param.setStAmount(form.getStPrice().multiply(param.getStockNumber()));
		//在庫ステータス
		String status = bussinessCommon.getAmount(form.getStoregeCode(), form.getCategoryCode(),
				form.getGoodsCode(), sumNumber.add(form.getStQuantity()));
		param.setStockStatus(status);
		param.setStComment(form.getStComment());
		param.setPhoto(form.getPhoto());
		param.setDelFlg(BigDecimal.ZERO);
		param.setInsertDate(LocalDateTime.now());
		param.setInsertUser(session.getAttribute("loginID").toString());
		param.setUpdateDate(LocalDateTime.now());
		param.setUpdateUser(session.getAttribute("loginID").toString());

		return param;
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
	private boolean stockUpd(PS000V01Form form, HttpSession session, String message, BindingResult result)
			throws MessagingException {
		//ステータス：入庫完了の場合
		if (form.getStStatus().equals("1011")) {
			//在庫データを検索
			StockEntity stockInfo = ps000V01Mapper.selectStock(form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getStPrice(), form.getStExpiration(), form.getStUnit());

			//入庫前の備品数
			BigDecimal sumNumber = bussinessCommon.getSumStockNumber(form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode());

			//同じ在庫データが存在する場合
			if (stockInfo != null) {
				//同じ在庫データが存在する場合元在庫写真消除
				if (stockInfo.getPhoto() != null) {
					String[] photoList = stockInfo.getPhoto().split(",");
					fileUploadService.deleteFile(photoList, "stock");
				}

				//元データ削除
				int count = ps000V01Mapper.deleteStock(stockInfo.getId());
				if (count != 1) {
					result.reject("", messageSource.getMessage(message, null, null));
					return false;
				}
				//新規
				StockEntity stockparam = copyStock(form, session, sumNumber, stockInfo.getStockNumber());
				count = ps000V01Mapper.insertStock(stockparam);
				if (count != 1) {
					result.reject("", messageSource.getMessage(message, null, null));
					return false;
				}

				return true;
			}

			//同じ在庫データが存在しない場合
			StockEntity stockparam = copyStock(form, session, sumNumber, BigDecimal.ZERO);
			int count = ps000V01Mapper.insertStock(stockparam);
			if (count != 1) {
				result.reject("", messageSource.getMessage(message, null, null));
				return false;
			}
		}

		return true;
	}

	/**
	 * 在庫備品写真選択
	 * @param id 在庫備品ID
	 * @return 写真URL
	 */
	public String selectStockPhoto(BigDecimal id) {
		String stockPhoto = ps000V01Mapper.selectStockPhoto(id);
		return stockPhoto;
	}

}
