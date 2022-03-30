package com.example.accountRegestryservice.service;

import com.example.accountRegestryservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *Class to send emails to user
 */
@Service
public class EmailServiceImpl {
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
                "Welcom to Warrios \n \n" +
                "Username: " + account.getUsername() + "\n" +
                "Password: " + account.getPassword());
        javaMailSender.send(msg);
    }
}
