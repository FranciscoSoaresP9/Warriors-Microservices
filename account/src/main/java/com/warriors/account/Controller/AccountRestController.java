package com.warriors.account.Controller;

import com.warriors.account.messages.Messages;
import com.warriors.account.model.Account;
import com.warriors.account.service.AccountService;
import com.warriors.account.warrior.Warrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Class that accepts requests of account
 */
@RestController
@RequestMapping("/account")
public class AccountRestController {
    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * This method accepts request to persist a new account
     *
     * @param account
     * @return the account creation status
     */
    @PostMapping(path = "/add")
    public String registryAccount(@RequestBody Account account) {
        accountService.saveOrUpdate(account);
        return Messages.ACCOUNT_CREATED.message;
    }

    /**
     * This method accepts request to add a warrior to account
     * @param id
     * @param warrior
     * @return The warrior creation status
     */
     @PostMapping(path = "/addWarrior/{id}")
    public String creatWarrior(@PathVariable Integer id, @RequestBody Warrior warrior) {
        accountService.createWarrior(id, warrior);
        return Messages.WARRIOR_CREATED.message;
     }

    /**
     * This method returns the warrior associated with the account
     * @param accountId
     * @return warrior
     */
    @GetMapping(path = "/getwarrior/{accountId}")
    public Warrior getWarriorOfAccount(@PathVariable Integer accountId) {
        return accountService.get(accountId).getWarrior();
    }

    /**
     * This method find a account by username
     * @param accountUserName
     * @return warrior
     */
    @GetMapping(path = "/getAccountByUsername/{accountUserName}")
    public Account getAccountByUserName(@PathVariable String accountUserName) {

        return accountService.getAccountByUsername(accountUserName);
    }
    /**
     * This method check´s if the username exist
     * @param userName
     * @return true if username exists and false if not
     */
    @GetMapping(path = "/isUserNameExist/{userName}")
    public Boolean isUsernameExist(@PathVariable String userName) {
        return accountService.isUserNameExist(userName);
    }

    /**
     * This method check´s if the username exist
     * @param email
     * @return true if email exists and false if not
     */
    @GetMapping(path = "/isEmailExist/{email}")
    public Boolean isEmailExist(@PathVariable String email) {
        return accountService.isEmailExist(email);
    }


}
