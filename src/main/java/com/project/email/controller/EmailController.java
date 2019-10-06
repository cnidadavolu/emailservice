package com.project.email.controller;

import com.project.email.model.User;
import com.project.email.service.EmailService;
import com.project.email.service.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Iterator;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReadFile readFile;

    @Autowired
    private User user;

    @RequestMapping("sendmail")
    public String sendEmailWithAttachment() throws MessagingException {
        String bodyLine;
        Map<String, String> mailList = readFile.recipientList();

        for(Map.Entry entry: mailList.entrySet()) {
            user.setRecipientName((String) entry.getKey());
            user.setRecipientEmailAddress((String) entry.getValue());
            user.setSubjectLine("Looking for new Java Opportunities");
            bodyLine = "Hi " + entry.getKey() +",\n" +"Please find the attached Resume with this email.." + "\n" + "Regards" +"\n"+ "Chaitanya";
            user.setBodyMessage(bodyLine);

            try {
                emailService.sendEmailWithAttachment(user);
            } catch (MailException me) {
                me.printStackTrace();
            }
        }
        return "Mail Sent Successfully";
    }






}
