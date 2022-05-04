package com.warriors.account.service;

import com.warriors.account.RequestSender;
import com.warriors.account.model.Account;
import com.warriors.account.model.AccountChangePasswordDto;
import com.warriors.account.repository.AccountRepository;
import com.warriors.account.warrior.Warrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 *
 */
@Service
public class AccountService implements Services<Account> {

    private final AccountRepository accountRepository;
    private final RequestSender requestSender;

    /**
     * @see AccountRepository
     */
    @Autowired
    public AccountService(AccountRepository accountRepository, RequestSender requestSender) {
        this.accountRepository = accountRepository;
        this.requestSender = requestSender;
    }

    /**
     * Make a new account
     *
     * @see AccountRepository#save(Object)
     */
    public Account saveOrUpdate(Account account) {
        return accountRepository.save(account);
    }


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

    @Override
    public void changePassword(AccountChangePasswordDto accountChangePasswordDto) {
        Account account = getAccountByUsername(accountChangePasswordDto.getUsername());
        account.setPassword(accountChangePasswordDto.getPassword());
        saveOrUpdate(account);
    }

    @Override
    public Warrior getWarrior(Integer accountId) throws IOException {

        Account account=accountRepository.getById(accountId);
        if(account.getWarriorId()==null){
            return null ;
        }
        Integer warriorId= account.getWarriorId();
        return requestSender.getWarrior(warriorId);
    }

    public Account getAccountByUsername(String userName) {
        return accountRepository.getAccountByUsername(userName);
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
    public boolean isUserNameExist(String userName) {

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
    public boolean isEmailExist(String email) {

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
     * @see Account#setWarrior(Warrior)
     */
    public void createWarrior(int id, Integer warriorId) {
        Account account = get(id);
        System.out.println(account.getWarriorId());
        if (account.getWarriorId() != null) {
            return;
        }
        account.setWarriorId(warriorId);
        System.out.println(account);
        accountRepository.save(account);
    }

    public String getEmailByUserName(String username) {
        return getAccountByUsername(username).getEmail();
    }
}
