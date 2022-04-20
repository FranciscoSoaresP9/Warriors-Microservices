package com.warriors.account.service;

public interface Services<T> {

    T get(int id);

    Iterable<T> getAll();
}
