package com.example.accountRegestryservice.controller;

import com.example.accountRegestryservice.model.Account;
import com.example.accountRegestryservice.service.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/registryaccount")
public class RegistryAccountController {
    private final Registry registry;

    @Autowired
    public RegistryAccountController(Registry registryAccount) {
        this.registry = registryAccount;
    }


    /**
     * Method to mapping a new Account Request
     *
     * @param account
     */


    @PostMapping()
    public String registryAccount(@RequestBody Account account) {
        return registry.registry(account);
    }




}
