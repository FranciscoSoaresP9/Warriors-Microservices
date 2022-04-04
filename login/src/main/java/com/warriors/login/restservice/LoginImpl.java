package com.warriors.login.restservice;

import com.warriors.login.RequestSender;
import com.warriors.login.model.account.Account;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginImpl implements Login {


    private final RequestSender requestSender;

    @Autowired
    public LoginImpl(RequestSender requestSender) {
        this.requestSender = requestSender;
    }


    /**
     * Method to mapping a login request
     *
     * @param
     */

    public Account login(Account accountToLogin) {
        try {
            if (requestSender.isUserNameExist(accountToLogin.getUsername())) {

              Account  dbAccount= requestSender.getAccount(accountToLogin.getUsername());

                if (checkPassword(accountToLogin,dbAccount)) {
                   return dbAccount;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return null;
    }

    private boolean checkPassword(Account accountToLogin,Account dbAccount) {
        if (dbAccount.getPassword().equals(accountToLogin.getPassword())) {
            return true;
        }

        return false;
    }
}
