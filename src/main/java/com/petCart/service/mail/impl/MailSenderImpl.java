package com.petCart.service.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.petCart.service.mail.IMailSender;
import com.petCart.service.mail.MailServiceProvider;
import com.petCart.util.ConfigUtil;



@Component("mailSender")
public class MailSenderImpl implements IMailSender{

	public MailSenderImpl() {
	}

	
	public void sendMail(String subject, String message, String sentTo,
			String eod) throws AddressException, MessagingException {
		String email=ConfigUtil.getConfigProp(ConfigUtil.EMAIL_ID);
		String password=ConfigUtil.getConfigProp(ConfigUtil.EMAIL_PASSWORD);
		MailServiceProvider.sendEmail(email,getDecodedPassword(password), sentTo, "", "", subject,message);		
	}
	public void sendMailWithCC(String subject, String message, String sentTo,
			String cc) throws AddressException, MessagingException {
		String email=ConfigUtil.getConfigProp(ConfigUtil.EMAIL_ID);
		String password=ConfigUtil.getConfigProp(ConfigUtil.EMAIL_PASSWORD);
		MailServiceProvider.sendEmail(email,getDecodedPassword(password), sentTo, cc, "", subject,message);		
	}
	
	public void sendMailWithCCAndBCC(String subject, String message, String sentTo,
			String cc,String bcc) throws AddressException, MessagingException {
		String email=ConfigUtil.getConfigProp(ConfigUtil.EMAIL_ID);
		String password=ConfigUtil.getConfigProp(ConfigUtil.EMAIL_PASSWORD);
		MailServiceProvider.sendEmail(email,getDecodedPassword(password), sentTo, cc, bcc, subject,message);		
	}
	
	private String getDecodedPassword(String encodePassword){
		if(encodePassword!=null){
		byte[] decodedPassword=Base64.decodeBase64(encodePassword.getBytes());
		return new String(decodedPassword);
		}else {
			return encodePassword;
		}
	}
}
