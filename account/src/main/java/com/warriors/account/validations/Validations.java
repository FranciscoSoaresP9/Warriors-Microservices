package com.warriors.account.validations;

import com.warriors.account.messages.Messages;
import com.warriors.account.model.Account;
import com.warriors.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Class to validate if the user input are correct
 */
@Service
public class Validations {
    private final AccountService accountService;

    @Autowired
    public Validations(AccountService accountService) {
        this.accountService = accountService;

    }

    /**
     * Check if the fields are filled
     *
     * @param account
     */
  private boolean checkFieldsAreFill(Account account) {
        if (account == null) {

            return false;
        }
        if (account.getUsername() == null || account.getEmail() == null || account.getPassword() == null) {

            return false;
        }
        return true;
    }

    /**
     * Check if the numbers match with verifications
     *
     * @param numberOfChars
     * @param value
     */
    private boolean checkNumberOfChars(String value, int numberOfChars) {

        if (value.length() >= numberOfChars) {
            return true;
        }
        return false;
    }

    public  HashMap<String,Boolean> checkFieldsOfAccountCreating(Account account) {
        HashMap<String,Boolean> validation= new HashMap<>();

        if (accountService.checkUserNameExist(account.getUsername())) {
           validation.put(Messages.USERNAME_TAKEN.message,false);
            return validation;
        }

        if (accountService.checkEmailExist(account.getEmail())) {
            validation.put(Messages.EMAIL_TAKEN.message,false);
            return validation;
        }

        if (!checkFieldsAreFill(account)) {
            validation.put(Messages.FILL_THE_FIELDS.message,false);
            return validation;
        }

        if (!account.getEmail().contains("@")) {
            return null;
        }

        if (!checkNumberOfChars(account.getUsername(), ValidationsProperties.USERNAME_CHARS_NUMBER.value)) {
            validation.put(Messages.NUMBER_OF_USERNAME_CHARS.message,false);
            return validation;

        }
        if (!checkNumberOfChars(account.getPassword(), ValidationsProperties.PASSWORD_CHARS_NUMBER.value)) {
            validation.put(Messages.NUMBER_OF_PASSWORD_CHARS.message,false);
            return validation;
        }
        validation.put(Messages.ACCOUNT_CREATED.message, true);
        return validation;
    }

}
