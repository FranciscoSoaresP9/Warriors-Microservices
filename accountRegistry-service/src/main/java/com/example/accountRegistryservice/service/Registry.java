package com.example.accountRegistryservice.service;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service to Registry a new Account into DataBase
 **/
public interface Registry<T> {
    /**
     * Method to registry a new Account
     **/
    String registry(@RequestBody T account);
}
