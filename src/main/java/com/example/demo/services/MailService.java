package com.example.demo.services;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.beans.EmailDetails;
import com.example.demo.beans.MailModel;

//Annotation
@Service
//Class
//Implementing EmailService interface
public class MailService implements IMailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private ServletContext servletContext;

	@Value("${spring.mail.username}")
	private String sender;

	// Method 1
	// To send a simple email
	public boolean sendSimpleMail(EmailDetails details) {

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			return true;
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return false;
		}
	}

	// Method 2
	// To send an email with attachment
	public boolean sendMailWithAttachment(EmailDetails details) {
		// Creating a mime message
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
//		System.out.println("send file attach");
//		File exce = new File(this.servletContext.getRealPath("/import_batch_products/" + "uid_8_cateId_5_products.xlsx"));
//		System.out.println(exce);
		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom("sender");
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());

			// Adding the attachment
			FileSystemResource file = new FileSystemResource(details.getAttachment());

//			mimeMessageHelper.addAttachment(file.getFilename(), file);
			mimeMessageHelper.addAttachment("products.xlsx", file);
			// Sending the mail
			javaMailSender.send(mimeMessage);
			return true;
		}

		// Catch block to handle MessagingException
		catch (MessagingException e) {
			e.printStackTrace();
			// Display message when exception occurred
			return false;
		}
	}
}
