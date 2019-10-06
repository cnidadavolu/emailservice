package com.project.email.service;

import com.project.email.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }


    public void sendEmailWithMessage(User user) throws MailException {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getRecipientEmailAddress());
        mailMessage.setSubject(user.getSubjectLine());
        mailMessage.setText(user.getBodyMessage());
        javaMailSender.send(mailMessage);
    }

    //Send email with Attachment...
    public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(user.getRecipientEmailAddress());
        helper.setSubject(user.getSubjectLine());
        helper.setText(user.getBodyMessage());

        ClassPathResource classPathResource = new ClassPathResource("CHAITANYA_Java_6+years.doc");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);
        javaMailSender.send(mimeMessage);
    }

}
