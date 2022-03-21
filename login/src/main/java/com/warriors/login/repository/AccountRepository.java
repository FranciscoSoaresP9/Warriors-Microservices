package com.warriors.login.repository;


import com.warriors.login.model.account.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

Account getAccountByUsername(String username);



}