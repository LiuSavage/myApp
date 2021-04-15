package com.cbs.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.cbs.dao.WM000V01Mapper;
import com.cbs.entity.StockEntity;
import com.cbs.entity.StockingEntity;
import com.cbs.entity.StoringEntity;
import com.cbs.form.WM000V01Form;

/**
 * 倉庫間移動入力のサービス
 */
@Service
@Transactional
public class WM000V01Service {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BussinessCommon bussinessCommon;

	@Autowired
	private WM000V01Mapper wm000V01Mapper;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private EmailCommon emailCommon;

	/**
	 * 在庫数検索
	 * @param form 画面フォーム
	 * @return 在庫数
	 */
	public BigDecimal stockNum(WM000V01Form form) {
		BigDecimal stockNum = BigDecimal.ZERO;
		//在庫list
		stockNum = wm000V01Mapper.stockNum(form.getStoregeCode(), form.getCategoryCode(), form.getGoodsCode(),
				form.getStockingUnit());
		return stockNum;
	}

	/**
	 * 在庫数リスト検索
	 * @param form 画面フォーム
	 * @return 在庫数リスト
	 */
	public List<StockEntity> stockList(WM000V01Form form) {

		List<StockEntity> stockList = new ArrayList<StockEntity>();
		//在庫list
		stockList = wm000V01Mapper.stockList(form.getStoregeCode(), form.getCategoryCode(), form.getGoodsCode(),
				form.getStockingUnit());
		return stockList;
	}

	/**
	 * 移動備品選択
	 * @param stockingId　出庫Id
	 * @return　移動備品情報
	 */
	public WM000V01Form select(BigDecimal stockingId) {
		WM000V01Form moveInfo = wm000V01Mapper.select(stockingId);
		return moveInfo;
	}

	/**
	 * 在庫備品選択
	 * @param form　画面フォーム
	 * @return　在庫備品情報
	 */
	public StockEntity selectStock(WM000V01Form form) {
		StockEntity stockInfo = wm000V01Mapper.selectStock(form);
		return stockInfo;
	}

	/**
	 * 在庫備品写真選択
	 * @param moveResult 画面フォーム
	 * @return 在庫備品情報
	 */
	public StockEntity selectStockPhoto(WM000V01Form moveResult) {
		StockEntity stockPhoto = wm000V01Mapper.selectStockPhoto(moveResult);
		return stockPhoto;
	}

	/**
	 * 出庫情報
	 * @param session
	 * @param item
	 * @param 画面フォーム
	 * @return 出庫情報
	 */
	public StockingEntity setStockingEntity(WM000V01Form form, StockEntity item, HttpSession session) {
		StockingEntity stockingInfo = new StockingEntity();
		stockingInfo.setStoregeCode(form.getStoregeCode());
		stockingInfo.setCategoryCode(form.getCategoryCode());
		stockingInfo.setGoodsCode(form.getGoodsCode());
		stockingInfo.setUserCode(form.getSheUser());
		stockingInfo.setStockingReceipt(form.getMovingDay());
		stockingInfo.setStExpiration(item.getStExpiration());
		stockingInfo.setSerialNumber(item.getSerialNumber());
		stockingInfo.setStPrice(item.getStPrice());
		stockingInfo.setStockingQuantity(form.getMovingNumber());
		stockingInfo.setStockingUnit(form.getStockingUnit());
		stockingInfo.setStAmount(form.getAmount());
		stockingInfo.setStockingReason("1002");
		stockingInfo.setStockingStatus(form.getStatus());
		stockingInfo.setStComment(form.getComment());
		stockingInfo.setDelFlg(BigDecimal.ZERO);
		stockingInfo.setInsertDate(form.getInsertDate());
		stockingInfo.setInsertUser(form.getInsertUser());
		stockingInfo.setUpdateDate(form.getUpdateDate());
		stockingInfo.setUpdateUser(form.getUpdateUser());
		return stockingInfo;
	}

	/**
	 * 入庫情報
	 * @param item
	 * @param session
	 * @param 画面フォーム
	 * @return 入庫情報
	 */
	public StoringEntity setStoringEntity(WM000V01Form form, StockEntity item, HttpSession session) {
		StoringEntity storingInfo = new StoringEntity();
		storingInfo.setStoregeCode(form.getStoregeCode2());
		storingInfo.setCategoryCode(form.getCategoryCode());
		storingInfo.setGoodsCode(form.getGoodsCode());
		storingInfo.setUserCode(form.getSheUser());
		storingInfo.setStReceipt(form.getMovingDay());
		storingInfo.setStExpiration(item.getStExpiration());
		storingInfo.setSerialNumber(item.getSerialNumber());
		storingInfo.setStPrice(item.getStPrice());
		storingInfo.setStQuantity(form.getMovingNumber());
		storingInfo.setStUnit(form.getStockingUnit());
		storingInfo.setStAmount(form.getAmount());
		storingInfo.setStReason("1002");
		storingInfo.setStStatus(form.getStatus());
		storingInfo.setStComment(form.getComment());
		storingInfo.setPhoto(form.getPhoto());
		storingInfo.setDelFlg(BigDecimal.ZERO);
		storingInfo.setInsertDate(form.getInsertDate());
		storingInfo.setInsertUser(form.getInsertUser());
		storingInfo.setUpdateDate(form.getUpdateDate());
		storingInfo.setUpdateUser(form.getUpdateUser());
		return storingInfo;
	}

	/**
	 * 在庫情報
	 * @param item
	 * @param session
	 * @param sumNumber
	 * @param 画面フォーム
	 * @return 在庫情報
	 */
	public StockEntity setStockEntity(WM000V01Form form, StockEntity item, HttpSession session, BigDecimal sumNumber) {
		StockEntity stockInfo = new StockEntity();

		stockInfo.setStoregeCode(form.getStoregeCode2());
		stockInfo.setCategoryCode(form.getCategoryCode());
		stockInfo.setGoodsCode(form.getGoodsCode());
		stockInfo.setStockNumber(form.getMovingNumber());
		stockInfo.setStUnit(form.getStockingUnit());
		stockInfo.setStExpiration(form.getExpiration());
		stockInfo.setSerialNumber(form.getSerialNumber());
		stockInfo.setStPrice(form.getPrice());
		stockInfo.setStAmount(form.getAmount());
		//在庫ステータス
		String status = bussinessCommon.getAmount(form.getStoregeCode2(), form.getCategoryCode(),
				form.getGoodsCode(), sumNumber.add(form.getMovingNumber()));
		stockInfo.setStockStatus(status);
		stockInfo.setStComment(form.getComment());
		stockInfo.setPhoto(form.getPhoto());
		stockInfo.setDelFlg(BigDecimal.ZERO);
		stockInfo.setInsertDate(form.getInsertDate());
		stockInfo.setInsertUser(form.getInsertUser());
		stockInfo.setUpdateDate(form.getUpdateDate());
		stockInfo.setUpdateUser(form.getUpdateUser());

		return stockInfo;
	}

	/**
	 * 倉庫間移動登録
	 * @param form　画面フォーム
	 * @param wm000v01List　在庫リスト
	 * @param session　セッション
	 * @param result チェック結果
	 * @return エラーあるかどうか
	 * @throws MessagingException
	 */
	public Boolean InsertMove(WM000V01Form form, List<StockEntity> wm000v01List, HttpSession session,
			BindingResult result) throws MessagingException {
		// 移動数
		BigDecimal movingNum = form.getMovingNumber();
		int count = 0;

		for (StockEntity item : wm000v01List) {

			form.setPrice(item.getStPrice());
			form.setSerialNumber(item.getSerialNumber());
			form.setExpiration(item.getStExpiration());

			form.setInsertDate(LocalDateTime.now());
			form.setInsertUser(session.getAttribute("loginID").toString());
			form.setUpdateDate(LocalDateTime.now());
			form.setUpdateUser(session.getAttribute("loginID").toString());
			if (item.getStockNumber().compareTo(movingNum) <= 0) {
				form.setMovingNumber(item.getStockNumber());
				form.setAmount(item.getStPrice().multiply(item.getStockNumber()));
				//入庫
				StoringEntity storingInfo = setStoringEntity(form, item, session);
				count = wm000V01Mapper.insertStoring(storingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}

				//出庫
				StockingEntity stockingInfo = setStockingEntity(form, item, session);
				stockingInfo.setStoringId(storingInfo.getId());
				count = wm000V01Mapper.insertStocking(stockingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				if (form.getStatus().equals("1011")) {
					updateStock(form, item, session, result);
				}
				movingNum = movingNum.subtract(item.getStockNumber());
			} else {
				form.setMovingNumber(movingNum);
				form.setAmount(item.getStPrice().multiply(form.getMovingNumber()));
				//入庫
				StoringEntity storingInfo = setStoringEntity(form, item, session);
				count = wm000V01Mapper.insertStoring(storingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//出庫
				StockingEntity stockingInfo = setStockingEntity(form, item, session);
				stockingInfo.setStoringId(storingInfo.getId());
				count = wm000V01Mapper.insertStocking(stockingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				if (form.getStatus().equals("1011")) {
					updateStock(form, item, session, result);
				}
				break;
			}
		}

		if (form.getStatus().equals("1011")) {
			//送信
			emailCommon.sendMail("倉庫間移動", form.getSheUser(), form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getMovingNumber(), form.getStockingUnit(), BigDecimal.ZERO,
					form.getStoregeCode2(), session);
		}

		return true;
	}

	/**
	 * 倉庫間移動更新
	 * @param form　画面フォーム
	 * @param wm000v01List　在庫リスト
	 * @param session　セッション
	 * @param result チェック結果
	 * @return エラーあるかどうか
	 * @throws MessagingException
	 */
	public Boolean updateMove(WM000V01Form form, List<StockEntity> wm000v01List, HttpSession session,
			BindingResult result) throws MessagingException {
		// 移動数
		BigDecimal movingNum = form.getMovingNumber();
		int count = 0;

		for (StockEntity item : wm000v01List) {

			form.setPrice(item.getStPrice());
			form.setSerialNumber(item.getSerialNumber());
			form.setExpiration(item.getStExpiration());

			form.setInsertDate(LocalDateTime.now());
			form.setInsertUser(session.getAttribute("loginID").toString());
			form.setUpdateDate(LocalDateTime.now());
			form.setUpdateUser(session.getAttribute("loginID").toString());
			if (item.getStockNumber().compareTo(movingNum) <= 0) {
				form.setMovingNumber(item.getStockNumber());
				form.setAmount(item.getStPrice().multiply(item.getStockNumber()));
				//入出庫検索
				StockEntity stokingList = wm000V01Mapper.selectStocking(form.getStockingId());

				if (stokingList != null) {
					form.setInsertDate(stokingList.getInsertDate());
					form.setInsertUser(stokingList.getInsertUser());
				}
				//入庫更新
				count = wm000V01Mapper.updateStoring(form);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//入庫更新後追加
				StoringEntity storingInfo = setStoringEntity(form, item, session);
				count = wm000V01Mapper.insertStoring(storingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//出庫更新
				count = wm000V01Mapper.updateStocking(form);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//出庫更新後追加
				StockingEntity stockingInfo = setStockingEntity(form, item, session);
				stockingInfo.setStoringId(storingInfo.getId());
				count = wm000V01Mapper.insertStocking(stockingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				if (form.getStatus().equals("1011")) {
					updateStock(form, item, session, result);
				}
				movingNum = movingNum.subtract(item.getStockNumber());
			} else {
				form.setMovingNumber(movingNum);
				form.setAmount(item.getStPrice().multiply(form.getMovingNumber()));
				//入出庫検索
				StockEntity stokingList = wm000V01Mapper.selectStocking(form.getStockingId());
				if (stokingList != null) {
					form.setInsertDate(stokingList.getInsertDate());
					form.setInsertUser(stokingList.getInsertUser());
				}
				//入庫更新
				count = wm000V01Mapper.updateStoring(form);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//入庫更新後追加
				StoringEntity storingInfo = setStoringEntity(form, item, session);
				count = wm000V01Mapper.insertStoring(storingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//出庫更新
				count = wm000V01Mapper.updateStocking(form);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//出庫更新後追加
				StockingEntity stockingInfo = setStockingEntity(form, item, session);
				stockingInfo.setStoringId(storingInfo.getId());
				count = wm000V01Mapper.insertStocking(stockingInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				if (form.getStatus().equals("1011")) {
					updateStock(form, item, session, result);
				}
				break;
			}
		}

		if (form.getStatus().equals("1011")) {
			//送信
			emailCommon.sendMail("倉庫間移動", form.getSheUser(), form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), form.getMovingNumber(), form.getStockingUnit(), BigDecimal.ONE,
					form.getStoregeCode2(), session);
		}

		return true;
	}

	/**
	 * 写真コーヒー
	 * @param photo 写真URL
	 * @return 新写真URL
	 */
	private String fileCopy(String photo) {
		String newFileName = "";
		String[] photoList = photo.split(",");
		for (int i = 0; i < photoList.length; i++) {
			String returnFileName = fileUploadService.fileCopy(photoList[i]);
			if (i == 0) {
				newFileName = returnFileName;
			} else {
				newFileName = newFileName + "," + returnFileName;
			}
		}
		return newFileName;
	}

	/**
	 * 在庫備品更新
	 * @param form 画面フォーム
	 * @param item　在庫備品情報
	 * @param session　セッション
	 * @param result チェック結果
	 * @return エラーあるかどうか
	 */
	public Boolean updateStock(WM000V01Form form, StockEntity item, HttpSession session,
			BindingResult result) {
		int count = 0;

		if (form.getStatus().equals("1011")) {
			StockEntity stockList = selectStock(form);

			//入庫前の備品数
			BigDecimal sumNumber = bussinessCommon.getSumStockNumber(form.getStoregeCode2(), form.getCategoryCode(),
					form.getGoodsCode());

			if (stockList == null) {
				//写真コーヒー
				String newFileName = null;
				if (item.getPhoto() != null) {
					newFileName = fileCopy(item.getPhoto());
				}
				form.setPhoto(newFileName);

				//在庫入追加
				StockEntity stockInfo = setStockEntity(form, item, session, sumNumber);
				count = wm000V01Mapper.InsertStockIn(stockInfo);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
			} else {
				//在庫入更新
				count = wm000V01Mapper.updateStockIn(stockList.getId());
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
				//在庫入更新後追加
				stockList.setStockNumber(stockList.getStockNumber().add(form.getMovingNumber()));
				stockList.setStAmount(stockList.getStAmount().add(form.getAmount()));
				stockList.setUpdateDate(LocalDateTime.now());
				stockList.setUpdateUser(session.getAttribute("loginID").toString());
				//在庫ステータス
				String status = bussinessCommon.getAmount(form.getStoregeCode2(), form.getCategoryCode(),
						form.getGoodsCode(), sumNumber.add(form.getMovingNumber()));
				stockList.setStockStatus(status);
				count = wm000V01Mapper.InsertStockIn(stockList);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
			}
			//出庫前の備品数
			BigDecimal sumNumberOut = bussinessCommon.getSumStockNumber(form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode());
			//在庫ステータス
			String status = bussinessCommon.getAmount(form.getStoregeCode(), form.getCategoryCode(),
					form.getGoodsCode(), sumNumberOut.subtract(form.getMovingNumber()));
			item.setStockStatus(status);
			//在庫出更新
			count = wm000V01Mapper.updateStockOut(item.getId(), item.getStockNumber(),
					item.getStAmount(), BigDecimal.ONE,
					session.getAttribute("loginID").toString(), LocalDateTime.now());
			if (count != 1) {
				result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
				return false;
			}
			//在庫出更新後追加
			if (item.getStockNumber().compareTo(form.getMovingNumber()) > 0) {
				item.setStockNumber(item.getStockNumber().subtract(form.getMovingNumber()));
				item.setStAmount(item.getStAmount().subtract(form.getMovingNumber().multiply(item.getStPrice())));
				item.setUpdateDate(LocalDateTime.now());
				item.setUpdateUser(session.getAttribute("loginID").toString());
				count = wm000V01Mapper.InsertStockIn(item);
				if (count != 1) {
					result.reject("", messageSource.getMessage("com.reg.fail", new Object[] {}, null));
					return false;
				}
			}
		}
		return true;
	}

}