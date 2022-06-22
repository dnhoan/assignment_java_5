package com.example.demo.services;

import com.example.demo.beans.EmailDetails;
import com.example.demo.beans.MailModel;

public interface IMailService {
	boolean sendMailWithAttachment(EmailDetails details);
	boolean sendSimpleMail(EmailDetails details);
}
