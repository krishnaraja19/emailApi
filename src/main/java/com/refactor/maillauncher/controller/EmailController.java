package com.refactor.maillauncher.controller;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.refactor.maillauncher.entities.Email;
import com.refactor.maillauncher.services.EmailServiceImpl;


@RestController
@RequestMapping("/email/")
public class EmailController {
	  ;
	   private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
	   
	    @Autowired
	    EmailServiceImpl emailService;
	   
	   @RequestMapping(value = "simple-mail", method = RequestMethod.GET)
	    public ResponseEntity<String> send(@RequestParam(value = "mailAddress") String mailAddress,
	    		   @RequestParam(value = "subject") String subject,
	    		   @RequestParam(value = "content") String content) {
		 String response;
		   try {
			   Email email = new Email();
			   email.setMailTo(mailAddress);
			   email.setMailSubject(subject);
			   email.setMailContent(content);
			   response = emailService.sendSimpleEmail(email);
			   LOGGER.info("Mail response "+response);
			   return new ResponseEntity<>(response, HttpStatus.OK);
			  
	        } catch (MailException mailException) {
	        	LOGGER.error("Error while sending out email..{}", mailException.getMessage());
	        	LOGGER.error("Error while sending out email..{}", mailException.fillInStackTrace());
	            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	       
		 
	 }
	
	   
	   @RequestMapping(value = "/simple-attachment-email/")
	    ResponseEntity<String> sendEmailAttachment(@RequestParam String mailAddress, @RequestParam String subject,
	    		   @RequestParam String content) {

	        try {
	            emailService.sendEmailWithAttachment(mailAddress, subject, content, "classpath:purchase_order.pdf");
	        } catch (MessagingException | FileNotFoundException mailException) {
	        	LOGGER.error("Error while sending out email..{}", mailException.getMessage());
	        	LOGGER.error("Error while sending out email..{}", mailException.fillInStackTrace());
	            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	        return new ResponseEntity<>("Please check your inbox for order confirmation", HttpStatus.OK);
	    }
}
