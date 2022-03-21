package com.warriors.login.restservice;

import com.warriors.login.model.account.Account;
import org.springframework.web.bind.annotation.RequestBody;


public interface Login {
    Account login(@RequestBody Account account);

}
