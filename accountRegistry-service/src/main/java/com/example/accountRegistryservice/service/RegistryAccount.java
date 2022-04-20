package com.example.accountRegistryservice.service;


import com.example.accountRegistryservice.request_sender.RequestSender;
import com.example.accountRegistryservice.messages.Messages;
import com.example.accountRegistryservice.model.Account;
import com.example.accountRegistryservice.validations.Validations;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * Implementation of Registry
 **/
@Service
public class RegistryAccount implements Registry<Account> {
    private final Validations validations;
    private final EmailServiceImpl emailService;
    private final RequestSender requestSender;

    @Autowired
    public RegistryAccount(Validations validations, EmailServiceImpl emailService, RequestSender requestSender) {
        this.validations = validations;
        this.emailService = emailService;
        this.requestSender = requestSender;
    }


    /**
     * This method registry a new account
     * @param account
     **/
    @Override
    public String registry(Account account) {
        HashMap<String, Boolean> validation = validations.checkFieldsOfAccountCreating(account);
        if (validation == null) {
            return null;
        }
        String validationMessage = validation
                .keySet()
                .stream()
                .findFirst()
                .get();

        if (!validation.get(validationMessage)) {
            return validationMessage;
        }

        try {
            sendEmail(account);
            persistAccountOnDB(account);
        } catch (Exception e) {
            e.printStackTrace();
            return Messages.GENERIC_ERROR.message;
        }

        return Messages.ACCOUNT_CREATED.message;
    }

    /**
     * This method persist the account on DataBase
     * @param account
     * @throws JSONException
     * @throws IOException
     */
    private void persistAccountOnDB(Account account) throws JSONException, IOException {
        requestSender.persistAccountInDB(account);
    }

    /**
     * This method sends an email to the user
     * @param account
     * @throws Exception
     */
    private void sendEmail(Account account) throws Exception {
        emailService.sendEmail(account);
    }

}
