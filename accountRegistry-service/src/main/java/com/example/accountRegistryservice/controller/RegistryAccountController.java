package com.example.accountRegistryservice.controller;

import com.example.accountRegistryservice.model.Account;
import com.example.accountRegistryservice.service.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Class that accepts requests to create an account
 */

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
        * @param account
     */

    @PostMapping()
    public String registryAccount(@RequestBody Account account) {
        return registry.registry(account);
    }




}
