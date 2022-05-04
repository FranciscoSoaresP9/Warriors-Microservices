package com.warriors.login.controller;

import com.warriors.login.model.account.Account;
import com.warriors.login.services.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * Class that accepts requests to user login
 */
@RestController
public class LoginController {
    private final Login login;

    @Autowired
    public LoginController(Login login) {
        this.login = login;
    }
    /**
     * Method login a account
     * @param account
     * @return account loged
     */
    @PostMapping("/login")
    public Account login(@RequestBody Account account) {
        return login.login(account);
    }
}
