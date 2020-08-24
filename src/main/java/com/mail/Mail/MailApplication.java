package com.mail.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
public class MailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

	@Autowired
	private SendEmailService sendEmailService;

	@EventListener(ApplicationReadyEvent.class)
	public void triggerWhenStarts() throws MessagingException {
		sendEmailService.sendEmail("rithikaspotlight@gmail.com","Hello","Demo");
	}

}
