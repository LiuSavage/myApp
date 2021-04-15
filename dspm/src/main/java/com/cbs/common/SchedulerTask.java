package com.cbs.common;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.cbs.service.EmailCommon;

@Configuration
public class SchedulerTask   {

	@Autowired
	private EmailCommon emailCommon;

	/**
	 * 期限切れた情報を送信すること。
	 */
	@Scheduled(cron = "0 0 10 * * ?")
    public void scheduled() throws MessagingException{
		emailCommon.sendDiscardMail();
    }
}
