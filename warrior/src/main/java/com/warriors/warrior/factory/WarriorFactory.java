package com.warriors.warrior.factory;

import com.warriors.warrior.services.WarriorStatusConvert;
import com.warriors.warrior.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WarriorFactory {
    private WarriorStatusConvert warriorStatusConvert;

    @Autowired
    public void setWarriorStatusConvert(WarriorStatusConvert warriorStatusConvert) {
        this.warriorStatusConvert = warriorStatusConvert;
    }

    public Warrior buildWarrior(WarriorDTO warriorDTO) {
        Warrior warrior = new Warrior();
        WarriorType warriorType = warriorDTO.getWarriorType();

        warrior.setName(warriorDTO.getName());
        warrior.setWarriorType(warriorType);
        System.out.println(warrior);
        setDefaults(warrior);

        warriorStatusConvert.pointsToStatus(warrior);
        return warrior;

    }

    private void setDefaults(Warrior warrior) {

        warrior.setPoints(new Points( 1, 1, 1,1,0));
        warrior.setStatus(new Status(0, 0, 0, 0));
        warrior.setLvl(1);
        warrior.setExperience(0);
    }

    private WarriorType convertStringTypeToWarriorType(String warriorType) {
        switch (warriorType) {
            case "Archer":
                return WarriorType.ARCHER;

            case "Assasin":
                return WarriorType.ASSASSIN;
        }
        return WarriorType.WARRIOR;
    }
}
