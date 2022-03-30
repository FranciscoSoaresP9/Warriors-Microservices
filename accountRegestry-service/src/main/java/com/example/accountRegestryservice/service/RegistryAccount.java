package com.example.accountRegestryservice.service;


import com.example.accountRegestryservice.RequestSender;
import com.example.accountRegestryservice.messages.Messages;
import com.example.accountRegestryservice.model.Account;
import com.example.accountRegestryservice.validations.Validations;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

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
            emailService.sendEmail(account);
        } catch (Exception exception) {
            System.out.println(exception);
            return Messages.GENERIC_ERROR.message;
        }
        try {
            requestSender.persistAccountInDB(account);
            return Messages.ACCOUNT_CREATED.message;
        } catch (IOException e) {
            e.printStackTrace();
            return Messages.GENERIC_ERROR.message;
        } catch (JSONException e) {
            e.printStackTrace();
            return Messages.GENERIC_ERROR.message;
        }
    }
}
