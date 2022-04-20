package com.warriors.login.services;

import com.warriors.login.RequestSender;
import com.warriors.login.model.account.Account;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Class to receive account, check the credentials and return the account logged
 */
@Service
public class LoginImpl implements Login {


    private final RequestSender requestSender;

    @Autowired
    public LoginImpl(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    /**
     * Method to log a account
     *
     * @param accountToLogin
     * @return account loged
     */
    public Account login(Account accountToLogin) {
        try {
            if (doValidations(accountToLogin)) {
                return requestSender.getAccount(accountToLogin.getUsername());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();

        }
        return null;
    }


    /**
     * Validate the credentials
     *
     * @param account
     * @return return true if the username and password are correct otherwise return false
     */
    private boolean doValidations(Account account) throws JSONException, IOException {
        if (validateUsername(account)) {
            Account dbAccount = requestSender.getAccount(account.getUsername());
            if (validatePassword(account, dbAccount)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Validate Username
     *
     * @param account
     * @return return true if the username correct otherwise return false
     */
    private boolean validateUsername(Account account) throws JSONException, IOException {
        return requestSender.isUserNameExist(account.getUsername());
    }

    /**
     * Validate Password
     *
     * @param accountToLogin
     * @param dbAccount
     * @return return true if the password are correct otherwise return false
     */
    private boolean validatePassword(Account accountToLogin, Account dbAccount) {
        if (dbAccount.getPassword().equals(accountToLogin.getPassword())) {
            return true;
        }

        return false;
    }
}
