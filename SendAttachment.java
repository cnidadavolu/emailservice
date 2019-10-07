package com.project.email.service;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class SendAttachment {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.debug", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chaitanya.java88@gmail.com", "cnJOB@2018");
            }
        });

        try {

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("xyz@gmail.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO, "abc@gmail.com");
            mimeMessage.setSubject("This is Subject Line");
            mimeMessage.setSentDate(new Date());

            Multipart multipart = new MimeMultipart();
            MimeBodyPart textPart = new MimeBodyPart();

            String textContent = "Hi, Please find the attached file with this mail.";
            textPart.setText(textContent);
            multipart.addBodyPart(textPart);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File("D:\\Sample.pdf"));
            multipart.addBodyPart(attachmentPart);

            mimeMessage.setContent(multipart);
            Transport.send(mimeMessage);
            System.out.println("----Mail Sent----");

        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end of sendEmailWithResume
} //end of Class
