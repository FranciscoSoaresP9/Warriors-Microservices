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


    @Autowired
    public RegistryAccount(AccountService accountService, Validations validations) {
        this.accountService = accountService;
        this.validations = validations;

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


   /*     try {
            emailServiceImpl.sendEmail(account);
        } catch (Exception exception) {
            return Messages.GENERIC_ERROR.message;
        }*/

        accountService.saveOrUpdate(account);
        return Messages.ACCOUNT_CREATED.message;
    }
}
