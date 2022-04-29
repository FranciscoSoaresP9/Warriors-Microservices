package com.example.accountRegistryservice.validations;


import com.example.accountRegistryservice.request_sender.RequestSender;
import com.example.accountRegistryservice.messages.Messages;
import com.example.accountRegistryservice.model.Account;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * Check all field of the account
     * @param account
     */

    public HashMap<HttpStatus, Boolean> checkFieldsOfAccountCreating(Account account) {
        HashMap<HttpStatus, Boolean> validation = new HashMap<>();
        if (!checkFieldsAreFill(account)) {
            validation.put(HttpStatus.NO_CONTENT, false);
            return validation;
        }
        try {
            if (checkIfTheUsernameExist(account)) {
                validation.put(HttpStatus.ALREADY_REPORTED, false);
                return validation;
            }
            if (checkIfTheEmailExist(account)) {
                validation.put(HttpStatus.IM_USED, false);
                return validation;
            }

            if (!checkTheNumberOfCharsOfUsername(account)) {
                validation.put(HttpStatus.LENGTH_REQUIRED, false);
                return validation;

            }
            if (!checkTheNumberOfCharsOfPassword(account)) {
                validation.put(HttpStatus.LENGTH_REQUIRED, false);
                return validation;
            }
            validation.put(HttpStatus.CREATED, true);
            return validation;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        validation.put(HttpStatus.INTERNAL_SERVER_ERROR, false);
        return validation;

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
        if (!requestSender.isEmailExist(account.getEmail())) {
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
