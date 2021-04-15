package com.cbs.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ページ数取得共通サービス
 */
@Service
@Transactional
public class PageCommon {

	/**
	 * 総ページ数を取得する。
	 * @param dateSize 情報リストサイズ
	 * @param pageSize 表示件数
	 * @return 総ページ数
	 */
	public BigDecimal getSumPage(int dateSize, BigDecimal pageSize) {
		if (dateSize == 0) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(dateSize).divide(pageSize, 0, BigDecimal.ROUND_UP);
	}

	/**
	 * 表示ページ数を取得する。
	 * @param sumPage 総ページ数
	 * @param pageNo 選択ページ番号
	 * @param size 表示ページ数
	 * @return 開始ページ数
	 */
	public BigDecimal getStarPageNo(BigDecimal sumPage, BigDecimal pageNo, BigDecimal size) {
		if (sumPage == null || sumPage.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		return getPageNum(sumPage, pageNo, size).get(0);
	}

	/**
	 * 表示ページ数を取得する。
	 * @param sumPage 総ページ数
	 * @param pageNo 選択ページ番号
	 * @param size 表示ページ数
	 * @return 終了ページ数
	 */
	public BigDecimal getEndPageNo(BigDecimal sumPage, BigDecimal pageNo, BigDecimal size) {
		if (sumPage == null || sumPage.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		return getPageNum(sumPage, pageNo, size).get(1);
	}

	/**
	 * 表示ページ数を取得する。
	 * @param sumPage 総ページ数
	 * @param pageNo 選択ページ番号
	 * @param size 表示ページ数
	 * @return 開始と終了ページ数
	 */
	private List<BigDecimal> getPageNum(BigDecimal sumPage, BigDecimal pageNo, BigDecimal size) {

		List<BigDecimal> result = new ArrayList<>();
		BigDecimal sumPageCompare = sumPage.subtract(size);
		if (sumPageCompare.compareTo(BigDecimal.ZERO) > 0) {

			BigDecimal starPageCompare = pageNo.subtract(size);
			if (starPageCompare.compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal endPageCompare = sumPage.subtract(pageNo);
				if (endPageCompare.compareTo(size) > 0) {
					//start
					result.add(pageNo);
					//end
					BigDecimal endPage = pageNo.add(new BigDecimal(4));
					result.add(endPage);
				} else {
					//start
					BigDecimal starPage = sumPage.subtract(new BigDecimal(4));
					result.add(starPage);
					//end
					result.add(sumPage);
				}
			} else {
				//start
				result.add(BigDecimal.ONE);
				//end
				result.add(size);
			}
		} else {
			//start
			result.add(BigDecimal.ONE);
			//end
			result.add(sumPage);
		}

		return result;
	}

}