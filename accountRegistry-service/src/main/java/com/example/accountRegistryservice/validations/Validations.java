package com.example.accountRegistryservice.validations;


import com.example.accountRegistryservice.request_sender.RequestSender;
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
     * @param account
     */
    private boolean checkFieldsAreFill(Account account) {

        if (account == null) {
            return false;
        }
        if (account.getUsername() == "" || account.getEmail() == "" || account.getPassword() == "") {
            return false;
        }
        return true;
    }


    /**
     * Check all field of the account
     * @param account
     */

    public HashMap<String, Boolean> checkFieldsOfAccountCreating(Account account) {
        HashMap<String, Boolean> validation = new HashMap<>();
        if (!checkFieldsAreFill(account)) {
            validation.put(Messages.FILL_THE_FIELDS.message, false);
            return validation;
        }
        try {
            if (checkIfTheUsernameExist(account)) {
                validation.put(Messages.USERNAME_TAKEN.message, false);
                return validation;
            }
            if (checkIfTheEmailExist(account)) {
                validation.put(Messages.EMAIL_TAKEN.message, false);
                return validation;
            }
            if (!checkIfTheEmailAreValid(account)) {
                return null;
            }

            if (!checkTheNumberOfCharsOfUsername(account)) {
                validation.put(Messages.NUMBER_OF_USERNAME_CHARS.message, false);
                return validation;

            }
            if (!checkTheNumberOfCharsOfPassword(account)) {
                validation.put(Messages.NUMBER_OF_PASSWORD_CHARS.message, false);
                return validation;
            }
            validation.put(Messages.ACCOUNT_CREATED.message, true);
            return validation;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        validation.put(Messages.GENERIC_ERROR.message, false);
        return validation;

    }

    /**
     * Check if the numbers of chars of password match with verifications
     * @param account
     */
    private boolean checkTheNumberOfCharsOfPassword(Account account) {
        return checkNumberOfChars(account.getPassword(), ValidationsProperties.PASSWORD_CHARS_NUMBER.value);
    }
    /**
     * Check if the numbers of chars of username match with verifications
     * @param account
     */
    private boolean checkTheNumberOfCharsOfUsername(Account account) {
        return checkNumberOfChars(account.getUsername(), ValidationsProperties.USERNAME_CHARS_NUMBER.value);
    }

    /**
     * Check if the email is valid
     * @param account
     */
    private boolean checkIfTheEmailAreValid(Account account) {
        return account.getEmail().contains("@");
    }

    /**
     * Check if the email already exist on DataBase
     * @param account
     */
    private boolean checkIfTheEmailExist(Account account) throws JSONException, IOException {
        if (requestSender.isEmailExist(account.getEmail())) {
            return false;
        }
        return true;
    }

    /**
     * Check if the UserName already exist on DataBase
     * @param account
     */
    private boolean checkIfTheUsernameExist(Account account) throws JSONException, IOException {
        return requestSender.isUserNameExist(account.getUsername());
    }

    /**
     * Check if the numbers match with verifications
     * @param numberOfChars
     * @param value
     */
    private boolean checkNumberOfChars(String value, int numberOfChars) {

        if (value.length() >= numberOfChars) {
            return true;
        }
        return false;
    }
}
