package com.warriors.status.services;

public interface StatusService<T>{
    T update(T toUpdate);
    T Save(T toSave);
}
