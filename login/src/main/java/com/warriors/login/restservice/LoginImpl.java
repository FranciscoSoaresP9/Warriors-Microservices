package com.warriors.login.restservice;

import com.warriors.login.model.account.Account;
import com.warriors.login.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginImpl implements Login {


    private final AccountRepository accountRepository;

    @Autowired
    public LoginImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    /**
     * Method to mapping a login request
     *
     * @param account
     */

    public Account login(Account account) {
        System.out.println("LOGIN:  "+account);
        if (checkFields(account)) {
            return accountRepository.getAccountByUsername(account.getUsername());
        }
        return null;
    }

    private boolean checkFields(Account account) {
        Account dbAccount = accountRepository.getAccountByUsername(account.getUsername());
        if (dbAccount != null) {
            if (dbAccount.getPassword().equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
