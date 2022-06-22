package com.example.demo.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.beans.MailModel;

@Service
public class MailBatchProductService {

	@Autowired
	private JavaMailSender sender;
	
	public void push(List<String> bodies, List<String> bcc, List<File> files) throws MessagingException {
		MailModel mail = new MailModel(bodies, bcc, files);
		this.push(mail);
	}
	
	public void push(MailModel mail) throws MessagingException {
		List<MimeMessage> queue = new ArrayList<MimeMessage>();
		MimeMessage mimeMessage = sender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
		mimeMessageHelper.setFrom(mail.getFrom());
		mimeMessageHelper.setSubject(mail.getSubject());
		mimeMessageHelper.setReplyTo(mail.getFrom());
		
		for (String email : mail.getBodies()) {
			mimeMessageHelper.setText(email);
		}
		for (String email : mail.getBcc()) {
			mimeMessageHelper.addBcc(email);
		}
		
		for (File file : mail.getFiles()) {
			mimeMessageHelper.addAttachment(file.getName(), file);
		}
		
		queue.add(mimeMessage);
		
		while(!queue.isEmpty()) {
			MimeMessage message = queue.remove(0);
			try {
				sender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
 }
