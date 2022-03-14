package com.warriors.warrior.jparepository;


import com.warriors.warrior.model.Warrior;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarriorRepository extends CrudRepository<Warrior,Integer> {

    Warrior getWarriorByName(String name);
}
