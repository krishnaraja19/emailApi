package com.refactor.maillauncher.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.refactor.maillauncher.entities.EmailModel;

@Service
public class EmailServiceImpl implements EmailService{
	 private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
	
		@Autowired
		public JavaMailSender emailSender;
		
		@Autowired
		public SimpleEmailValidator simpleEmailValidator;
		
		@Value("${mail.response.message}")
		String responseMessage;
		
		
		@Override
		public String sendSimpleEmail(EmailModel bulkMail) {
			// TODO Auto-generated method stub
			  LOGGER.info("EmailServiceImpl will start the validation process for bulk email");
			  String errorMessage = simpleEmailValidator.validator(bulkMail.getMailTo(), bulkMail.getMailSubject(), bulkMail.getMailContent());
			  LOGGER.info("The validation process is completed");
			  
			  if(errorMessage.isEmpty()) {
		      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			  simpleMailMessage.setTo(bulkMail.getMailTo());
			  simpleMailMessage.setSubject(bulkMail.getMailSubject());
			  simpleMailMessage.setText(bulkMail.getMailContent());
			  emailSender.send(simpleMailMessage);
				
			  LOGGER.info("Mails sent successfully");
			  return responseMessage;
			  }else {
				  LOGGER.info("The validation process is throw the error");
				  return errorMessage;
			  }
		}
		
				
		
}
