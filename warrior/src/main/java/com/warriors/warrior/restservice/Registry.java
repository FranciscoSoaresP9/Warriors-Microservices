package com.warriors.warrior.restservice;

import org.springframework.web.bind.annotation.RequestBody;


public interface Registry<T> {
    String registry(@RequestBody T account);
}
