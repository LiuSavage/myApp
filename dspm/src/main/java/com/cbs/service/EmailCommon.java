package com.cbs.service;

import java.math.BigDecimal;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.cbs.dao.EmailCommonMapper;
import com.cbs.entity.EmailCommonEntity;

/*
 * メール送信の共通サービス
 */
@Service
@Transactional
public class EmailCommon {

	@Value("${spring.mail.from}")
	private String from;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailCommonMapper emailCommonMapper;

	/**
	 * HTML形式で送信すること。
	 */
	public void sendDiscardMail() throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(from);
		String[] userList = emailCommonMapper.getAllUserAuth();
		messageHelper.setTo(userList);
		messageHelper.setSubject("賞味／消費期限近づきのお知らせ");
		// 内容
		String content = "";
		List<EmailCommonEntity> emailList = emailCommonMapper.selectAll();
		if (emailList != null && emailList.size() > 0) {
			for (EmailCommonEntity item : emailList) {
				content += "<p>" + item.getStoregeName() + "倉庫の備品" + item.getGoodsName() + "期限切れ日は「"
						+ item.getDiscardDays() + "日」近づきのお知らせです。<br/>" +
						"期限切れるまでお早めにご確認ください。</p>";
			}
		}
		content += "<p>**********************************************************<br/>" +
				"このメールは防災備品管理システムより、自動配信メールです。<br/>" +
				"ご返信は、できませんのでご注意ください。<br/>" +
				"**********************************************************</p>";

		messageHelper.setText(content, true);
		mailSender.send(message);
	}

	/**
	 * 登録した送信すること。
	 * @param functionName 機能名
	 * @param userID 担当者コード
	 * @param storegeCode 元倉庫コード
	 * @param categoryCode カテゴリーコード
	 * @param goodsCode 備品コード
	 * @param count 数量
	 * @param stUnit 単位
	 * @param dist 操作区分
	 * @param storegeCodeTo 先倉庫コード
	 * @param session セッション
	 * @throws MessagingException
	 */
	public void sendMail(String functionName, String userID, String storegeCode, String categoryCode, String goodsCode,
			BigDecimal count, String stUnit, BigDecimal dist, String storegeCodeTo, HttpSession session)
			throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(from);
		BigDecimal userAuth = (BigDecimal) session.getAttribute("userAuth");
		String[] userList = emailCommonMapper.getUserAuth(userAuth, userID);
		messageHelper.setTo(userList);
		String title = "";
		// dist=0(登録)
		if (dist.compareTo(BigDecimal.ZERO) == 0) {
			title = "登録";

			// dist=1(更新)
		} else if (dist.compareTo(BigDecimal.ONE) == 0) {
			title = "更新";
		}
		messageHelper.setSubject(functionName + "備品" + title + "のお知らせ");

		String userName = (String) session.getAttribute("loginName");
		String storegeName = emailCommonMapper.getStoregeName(storegeCode);
		String storegeToName = emailCommonMapper.getStoregeName(storegeCodeTo);
		String goodsName = emailCommonMapper.getGoodsName(categoryCode, goodsCode);
		String unitName = emailCommonMapper.getUnitName(stUnit);
		// 内容
		String content = "";

		//入庫
		if (StringUtils.equals(functionName, "入庫")) {
			//登録
			if (dist.compareTo(BigDecimal.ZERO) == 0) {
				content = "<p>" + userName + "さんが" + storegeName + "倉庫へ" + goodsName + "を" + count + unitName
						+ "入庫しました。</p>";
			} else {
				content = "<p>" + userName + "さんが" + storegeName + "倉庫の" + goodsName + "を" + count + unitName + title
						+ "しました。</p>";
			}

			//出庫
		} else if (StringUtils.equals(functionName, "出庫")) {
			//登録
			if (dist.compareTo(BigDecimal.ZERO) == 0) {
				content = "<p>" + userName + "さんが" + storegeName + "倉庫から" + goodsName + "を" + count + unitName
						+ "出庫しました。</p>";
			} else {
				content = "<p>" + userName + "さんが" + storegeName + "倉庫の" + goodsName + "を" + count + unitName + title
						+ "しました。</p>";
			}

			//倉庫間移動
		} else if (StringUtils.equals(functionName, "倉庫間移動")) {
			//登録
			if (dist.compareTo(BigDecimal.ZERO) == 0) {
				content = "<p>" + userName + "さんが" + storegeName + "倉庫から" + goodsName + "を" + storegeToName + "倉庫へ"
						+ count + unitName + "移動しました。</p>";
			} else {
				content = "<p>" + userName + "さんが" + storegeName + "倉庫から" + goodsName + "を" + storegeToName + "倉庫へ"
						+ count + unitName + title + "しました。</p>";
			}
		}

		content += "<p>**********************************************************<br/>" +
				"このメールは防災備品管理システムより、自動配信メールです。<br/>" +
				"ご返信は、できませんのでご注意ください。<br/>" +
				"**********************************************************</p>";

		messageHelper.setText(content, true);
		mailSender.send(message);
	}
}