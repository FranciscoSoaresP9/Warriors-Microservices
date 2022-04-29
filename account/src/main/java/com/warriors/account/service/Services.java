package com.warriors.account.service;

import com.warriors.account.model.AccountChangePasswordDto;

public interface Services<T> {

    T get(int id);

    Iterable<T> getAll();

    void changePassword(AccountChangePasswordDto accountChangePasswordDto);
}
