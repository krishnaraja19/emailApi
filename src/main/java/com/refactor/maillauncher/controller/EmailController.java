package com.refactor.maillauncher.controller;



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

import com.refactor.maillauncher.entities.EmailModel;
import com.refactor.maillauncher.services.EmailServiceImpl;


@RestController
@RequestMapping("/email/")
public class EmailController {
	  
	   private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
	   
	    @Autowired
	    EmailServiceImpl emailService;
	   
	   
	    
	  //Simple mail request 	    
	    @RequestMapping(value = "simple-bulk-mail", method = RequestMethod.POST)
	    public ResponseEntity<String> bulkMailSend(@RequestParam(value = "mailAddress") String[] mailAddress,
	    		   @RequestParam(value = "subject") String subject,
	    		   @RequestParam(value = "content") String content) {
		 String response;
		   try {
			   
			   EmailModel bulkMailSender = new EmailModel();
			   bulkMailSender.setMailTo(mailAddress);
			   bulkMailSender.setMailSubject(subject);
			   bulkMailSender.setMailContent(content);
			   
               response = emailService.sendSimpleEmail(bulkMailSender);
			   
			   LOGGER.info("Mail response "+response);
			   
			   return new ResponseEntity<>(response, HttpStatus.OK);
			  
	        } catch (MailException mailException) {
	        	LOGGER.error("Error while sending out email..{}", mailException.getMessage());
	        	LOGGER.error("Error while sending out email..{}", mailException.fillInStackTrace());
	            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	 }
	
}
