package com.warriors.warrior.services;

public interface UpdatableService<T> extends Services<T>{
    void update(T toUpdate);
}
