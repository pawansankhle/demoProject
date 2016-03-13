package com.petCart.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.petCart.service.mail.IMailSender;


@Component("mailSender")
public class MailSenderImpl implements IMailSender{

	public MailSenderImpl() {
	}
	
	@Override
	@Async
	public void sendMail(String subject, String message, String string,
			String eod) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Async
	public void sendMailWithCC(String subject, String message, String string,
			String cc) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Async
	public void sendMailWithCCAndBCC(String subject, String message,
			String string, String cc, String bcc) throws AddressException,
			MessagingException {
		// TODO Auto-generated method stub
		
	}

}
