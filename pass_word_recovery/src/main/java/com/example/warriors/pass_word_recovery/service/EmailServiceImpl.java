package com.example.warriors.pass_word_recovery.service;

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
     * Method to send a email to user
     *
     * @Param account
     */
    public void sendEmail(String email, String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Recovery Code");
        msg.setText("Welcom to Warrios \n \n" +
                "Your code is: " + code);
        javaMailSender.send(msg);
    }
}
