package com.warriors.account.Controller;

import com.warriors.account.messages.Messages;
import com.warriors.account.model.Account;
import com.warriors.account.service.AccountService;
import com.warriors.account.service.Registry;
import com.warriors.account.warrior.Warrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/account")
public class AccountRestController {
    private final AccountService accountService;
    private final Registry registry;

    @Autowired
    public AccountRestController(AccountService accountService, Registry registryAccount) {
        this.accountService = accountService;
        this.registry = registryAccount;
    }


    /**
     * Method to mapping a new Account Request
     *
     * @param account
     */


    @PostMapping(path = "/add")
    public String registryAccount(@RequestBody Account account) {
        System.out.println(account);
        accountService.saveOrUpdate(account);
        return Messages.ACCOUNT_CREATED.message;
    }

    @PostMapping(path = "/addWarrior/{id}")
    public String creatWarrior(@PathVariable Integer id,@RequestBody Warrior warrior) {
         accountService.createWarrior(id,warrior);
        return "Warrior Created";
    }

    @GetMapping(path = "/getwarrior/{accountId}")
    public Warrior getWarriorOfAccount(@PathVariable Integer accountId){
        System.out.println(accountService.get(accountId).getWarrior());
       return accountService.get(accountId).getWarrior();
    }

    @GetMapping(path = "/isUserNameExist/{userName}")
    public Boolean isUsernameExist(@PathVariable String userName){
        return accountService.isUserNameExist(userName);
    }
    @GetMapping(path = "/isEmailExist/{email}")
    public Boolean isEmailExist(@PathVariable String email){
        return accountService.isEmailExist(email);
    }


}
