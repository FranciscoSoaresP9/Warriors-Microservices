package com.warriors.account.repository;


import com.warriors.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * Class to do the CRUD operation to the Data Base
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

Account getAccountByUsername(String username);



}