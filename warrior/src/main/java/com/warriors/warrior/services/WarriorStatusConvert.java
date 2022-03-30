package com.warriors.warrior.services;

import com.warriors.warrior.model.Points;
import com.warriors.warrior.model.Status;
import com.warriors.warrior.model.Warrior;
import com.warriors.warrior.model.WarriorType;
import org.springframework.stereotype.Service;

@Service
public class WarriorStatusConvert {

    public Status pointsToStatus(Warrior warrior) {
        System.out.println(warrior);

        Points points = warrior.getPoints();
        Status status = warrior.getStatus();
        WarriorType warriorType = warrior.getWarriorType();

        status.setDamage((warriorType.damage / 2) * points.getDamage());
        status.setArmor(warriorType.armor * points.getArmor());
        status.setLife((warriorType.life * 2) * points.getLife());
        status.setSpeed((warriorType.speed) + points.getSpeed() / 4);


        System.out.println(status.toString());

        return status;
    }
}
