package com.example.warriorRegistry.registry;


import com.example.warriorRegistry.model.Warrior;
import org.springframework.web.bind.annotation.RequestBody;


public interface Registry<T> {
    Warrior registry(@RequestBody T entity);

}
