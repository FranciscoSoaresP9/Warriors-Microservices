package com.example.warriors.pass_word_recovery.service;

/**
 * Service to send a email to the user
 **/
public interface EmailService {
    /**
     *Method to send a email to user
     * @Param account
     */
    void sendEmail(String email,String code);
}
