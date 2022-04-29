package com.example.accountRegistryservice.service;


import com.example.accountRegistryservice.request_sender.RequestSender;
import com.example.accountRegistryservice.messages.Messages;
import com.example.accountRegistryservice.model.Account;
import com.example.accountRegistryservice.validations.Validations;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     *
     * @param account
     **/
    @Override
    public ResponseEntity<Account> registry(Account account) {
        HashMap<HttpStatus, Boolean> validation = validations.checkFieldsOfAccountCreating(account);

        HttpStatus validationMessage = validation
                .keySet()
                .stream()
                .findFirst()
                .get();

        if (!validation.get(validationMessage)) {
            return ResponseEntity.status(validationMessage)
                    .body(account);
        }

        try {
            sendEmail(account);
          account=  persistAccountOnDB(account);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("statusText", Messages.GENERIC_ERROR.message)
                    .body(account);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("status", Messages.ACCOUNT_CREATED.message)
                .body(account);
    }

    /**
     * This method persist the account on DataBase
     *
     * @param account
     * @throws JSONException
     * @throws IOException
     */
    private Account persistAccountOnDB(Account account) throws JSONException, IOException {
       return requestSender.persistAccountInDB(account);
    }

    /**
     * This method sends an email to the user
     *
     * @param account
     * @throws Exception
     */
    private void sendEmail(Account account) throws Exception {
        emailService.sendEmail(account);
    }

}
