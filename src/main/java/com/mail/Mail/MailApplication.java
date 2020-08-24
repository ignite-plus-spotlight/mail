package com.mail.Mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class MailApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MailApplication.class, args);
		final String username = "spotlightadm@gmail.com";
		final String password = "spotlight123.";
		String fromEmail = "spotlightadm@gmail.com";
		String toEmail = "rithikaspotlight@gmail.com";

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});

		CertGenerator certGenerator = new CertGenerator();
		String htmlData = certGenerator.certGenerate();
		VelToPDF.velocityToPdf(htmlData);

		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Subject Line");

			Multipart emailContent = new MimeMultipart();

			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("My multipart text");

			//Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile("C:/Users/User/Downloads/Mail/Test.pdf");

			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);

			//Attach multipart to message
			msg.setContent(emailContent);

			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}

//	@Autowired
//	private SendEmailService sendEmailService;
//
//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerWhenStarts() throws MessagingException {
//		sendEmailService.sendEmail("rithikaspotlight@gmail.com","Hello","Demo");
//	}


//}
