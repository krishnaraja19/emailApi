package com.refactor.maillauncher.services;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.refactor.maillauncher.entities.Email;

@Service
public class EmailServiceImpl implements EmailService{

		@Autowired
		public JavaMailSender emailSender;
		
		@Autowired
		public SimpleEmailValidator simpleEmailValidator;
		
		@Value("${mail.response.message}")
		String responseMessage;
		
		@Override
		public String sendSimpleEmail(Email email) {
		  String errorMessage = simpleEmailValidator.validator(email.getMailTo(), email.getMailSubject(), email.getMailContent());
		  if(errorMessage.isEmpty()) {
			  SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			  simpleMailMessage.setTo(email.getMailTo());
			  simpleMailMessage.setSubject(email.getMailSubject());
			  simpleMailMessage.setText(email.getMailContent());
			  emailSender.send(simpleMailMessage);
			  return responseMessage;
		  }else {
			  return errorMessage;
		  }
		
		 }
		
		public String sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException {

		  MimeMessage mimeMessage = emailSender.createMimeMessage();
		  MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		  messageHelper.setTo(toAddress);
		  messageHelper.setSubject(subject);
		  messageHelper.setText(message);
		  FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
		  messageHelper.addAttachment("Purchase Order", file);
		  emailSender.send(mimeMessage);
		  return "";
		 }

		
		
}
