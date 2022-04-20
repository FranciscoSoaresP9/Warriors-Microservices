package com.warriors.login.services;

import com.warriors.login.model.account.Account;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Class to receive account, check the credentials and return the account logged
 */
public interface Login {
    Account login(@RequestBody Account account);

}
