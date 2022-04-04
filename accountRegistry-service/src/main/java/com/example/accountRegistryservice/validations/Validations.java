package com.example.accountRegistryservice.validations;


import com.example.accountRegistryservice.RequestSender;
import com.example.accountRegistryservice.messages.Messages;
import com.example.accountRegistryservice.model.Account;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * Class to validate if the user input are correct
 */
@Service
public class Validations {

    private final RequestSender requestSender;

    @Autowired
    public Validations(RequestSender requestSender) {
        this.requestSender = requestSender;
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

    public HashMap<String, Boolean> checkFieldsOfAccountCreating(Account account) {
        HashMap<String, Boolean> validation = new HashMap<>();

        try {
            if (requestSender.isUserNameExist(account.getUsername())) {
                validation.put(Messages.USERNAME_TAKEN.message, false);
                return validation;
            }
            if (requestSender.isEmailExist(account.getEmail())) {
                validation.put(Messages.EMAIL_TAKEN.message, false);
                return validation;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        if (!checkFieldsAreFill(account)) {
            validation.put(Messages.FILL_THE_FIELDS.message, false);
            return validation;
        }

        if (!account.getEmail().contains("@")) {
            return null;
        }

        if (!checkNumberOfChars(account.getUsername(), ValidationsProperties.USERNAME_CHARS_NUMBER.value)) {
            validation.put(Messages.NUMBER_OF_USERNAME_CHARS.message, false);
            return validation;

        }
        if (!checkNumberOfChars(account.getPassword(), ValidationsProperties.PASSWORD_CHARS_NUMBER.value)) {
            validation.put(Messages.NUMBER_OF_PASSWORD_CHARS.message, false);
            return validation;
        }
        validation.put(Messages.ACCOUNT_CREATED.message, true);
        return validation;
    }

}
