package com.warriors.account.service;

import com.warriors.account.messages.Messages;
import com.warriors.account.model.Account;
import com.warriors.account.validations.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;

@Service
public class RegistryAccount implements Registry<Account> {
    private final AccountService accountService;
    private final Validations validations;
    private final EmailServiceImpl emailService;
    @Autowired
    public RegistryAccount(AccountService accountService, Validations validations, EmailServiceImpl emailService) {
        this.accountService = accountService;
        this.validations = validations;

        this.emailService = emailService;
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

        accountService.saveOrUpdate(account);
        return Messages.ACCOUNT_CREATED.message;
    }
}
