package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.beans.EmailDetails;

import com.example.demo.services.IMailService;


//Annotation
@Controller
//Class
public class MailController {

	@Autowired private IMailService emailService;

	// Sending a simple Email
	@GetMapping("/sendMail")
	public boolean
	sendMail()
	{
		EmailDetails details = new EmailDetails();
		details.setMsgBody("dsfafds");
		details.setSubject("subject");
		boolean status
			= emailService.sendSimpleMail(details);

		return status;
	}

	// Sending email with attachment
	@GetMapping("/sendMailWithAttachment")
	public boolean sendMailWithAttachment()
	{
		EmailDetails details = new EmailDetails();
		details.setMsgBody("dsfafds");
		details.setSubject("Kết quả thêm sản phẩm hàng loạt");
		boolean status
			= emailService.sendMailWithAttachment(details);

		return status;
	}
}

