package com.warriors.login;

import com.warriors.login.model.account.Account;
import com.warriors.login.restservice.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final Login login;

    @Autowired
    public LoginController(Login login) {
        this.login = login;
    }

    @PostMapping("/login")
    public Account login(@RequestBody Account account) {
       return login.login(account);
    }
}
