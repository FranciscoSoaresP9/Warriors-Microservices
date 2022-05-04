package com.warriors.account.service;

import com.warriors.account.model.AccountChangePasswordDto;
import com.warriors.account.warrior.Warrior;

import java.io.IOException;

public interface Services<T> {

    T get(int id);

    Iterable<T> getAll();

    void changePassword(AccountChangePasswordDto accountChangePasswordDto);

    Warrior getWarrior(Integer warriorId) throws IOException;
}
