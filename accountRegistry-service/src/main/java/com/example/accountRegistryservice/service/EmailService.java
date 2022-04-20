package com.example.accountRegistryservice.service;

import com.example.accountRegistryservice.model.Account;

/**
 * Service to send a email to the user
 **/
public interface EmailService {
    /**
     *Method to send a email to user
     * @Param account
     */
    void sendEmail(Account account);
}
