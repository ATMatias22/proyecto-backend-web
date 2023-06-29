package com.sensor.security.service.implementation;

import com.sensor.security.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String subject, String to, String email) {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setContent(email,"text/html");
            mailSender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
