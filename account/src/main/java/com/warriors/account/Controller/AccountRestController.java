package com.warriors.account.Controller;

import com.warriors.account.model.Account;
import com.warriors.account.service.AccountService;
import com.warriors.account.service.Registry;
import com.warriors.account.warrior.Warrior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("")
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


    @PostMapping(path = "account/add")
    public String registryAccount(@RequestBody Account account) {
        return registry.registry(account);
    }

    @PostMapping(path = "/addWarrior/{id}")
    public String creatWarrior(@PathVariable Integer id,@RequestBody Warrior warrior) {
         accountService.createWarrior(id,warrior);
        return "Warrior Created";
    }

    @GetMapping(path = "account/getwarrior/{accountId}")
    public Warrior getWarriorOfAccount(@PathVariable Integer accountId){
        System.out.println(accountService.get(accountId).getWarrior());
       return accountService.get(accountId).getWarrior();
    }


}
