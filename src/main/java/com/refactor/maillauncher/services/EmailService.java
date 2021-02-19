package com.refactor.maillauncher.services;


import com.refactor.maillauncher.entities.EmailModel;

public interface EmailService {

	 String sendSimpleEmail(EmailModel bulkMail);
}
