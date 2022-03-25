package com.warriors.account.service;

import com.warriors.account.model.Account;
import com.warriors.account.repository.AccountRepository;
import com.warriors.account.warrior.Warrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;


/**
 * AccountService
 */
@Service
public class AccountService implements warriors.services.Services<Account> {

    private final AccountRepository accountRepository;

    /**
     * @see AccountRepository
     */
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Make a new account
     *
     * @see AccountRepository#save(Object)
     */
    public void saveOrUpdate(Account account) {
        accountRepository.save(account);
        System.out.println("created");
    }
    //Most check if have allready a warrior


    /**
     * Get Account
     *
     * @param id
     * @see AccountRepository#findById(Object)
     */
    @Override
    public Account get(int id) {
        return accountRepository.findById(id).get();
    }

    /**
     * Get all Account
     *
     * @see AccountRepository#findAll()
     */
    @Override
    public Iterable<Account> getAll() {
        return accountRepository.findAll();
    }

    public Integer getIdByUsername(Account account) {
        return accountRepository.getAccountByUsername(account.getUsername()).getId();
    }

    /**
     * Check if username and password match
     */
    public Account checkUsernameAndPassword(Account account) {
        Account dbAccount = accountRepository.getAccountByUsername(account.getUsername());
        if (dbAccount == null) {
            return null;
        }
        if (dbAccount.getPassword().equals(account.getPassword())) {

            return dbAccount;
        }
        return null;
    }

    /**
     * Check if username exist
     */
    public boolean checkUserNameExist(String userName) {

        List<String> allUserName = new LinkedList<>();
        Iterable<Account> accounts = getAll();
        accounts.forEach(account -> allUserName.add(account.getUsername()));

        return allUserName.contains(userName);


    }

    /**
     * Check if email exist
     *
     * @param email
     */
    public boolean checkEmailExist(String email) {

        List<String> allEmails = new LinkedList<>();
        Iterable<Account> accounts = getAll();
        accounts.forEach(account -> allEmails.add(account.getEmail()));

        return allEmails.contains(email);


    }

    /**
     * Create new Warrior
     *
     * @param warrior
     * @param id
     * @see AccountRepository#save(Object)
     * @see Account#creatWarrior(Warrior)
     */
    public void createWarrior(int id, Warrior warrior) {
        Account account = get(id);
        if(account.getWarrior()!=null){
            return;
        }
        account.creatWarrior(warrior);
        System.out.println(account);
        accountRepository.save(account);
    }
}
