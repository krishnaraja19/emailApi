package com.refactor.maillauncher.services;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import com.refactor.maillauncher.entities.Email;

public interface EmailService {

	 String sendSimpleEmail(Email email);
     String sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;
}
