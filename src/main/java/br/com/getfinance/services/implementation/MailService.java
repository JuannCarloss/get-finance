package br.com.getfinance.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String subject, String body, String emailTo) {

        var message = new SimpleMailMessage();

        message.setSubject(subject);
        message.setText(body);
        message.setTo(emailTo);

        mailSender.send(message);
    }
}