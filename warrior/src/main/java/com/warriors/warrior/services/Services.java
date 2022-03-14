package com.warriors.warrior.services;

public interface Services<T> {

    T get(int id);

    Iterable<T> getAll();
}
