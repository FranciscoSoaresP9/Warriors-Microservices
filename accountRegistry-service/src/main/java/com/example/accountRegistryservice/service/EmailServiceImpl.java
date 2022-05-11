package com.example.accountRegistryservice.service;

import com.example.accountRegistryservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Implementation of EmailService
 **/
@Service
public class EmailServiceImpl implements EmailService {
    /**
     * @see JavaMailSender
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     *Method to send a email to user
     * @Param account
     */
    public void sendEmail(Account account) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(account.getEmail());
        msg.setSubject("Account Created");
        msg.setText("Hello " + account.getUsername() + " \n \n " +
                "Welcome to Warriors \n \n" +
                "Username: " + account.getUsername() + "\n");
        javaMailSender.send(msg);
    }
}
