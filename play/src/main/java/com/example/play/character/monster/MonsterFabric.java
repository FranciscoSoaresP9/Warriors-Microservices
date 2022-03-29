package com.example.play.character.monster;


import com.example.play.character.PersonageType;
import com.example.play.character.Status;
import org.springframework.stereotype.Service;

@Service
public class MonsterFabric {
    public Monster createMonster(com.example.play.character.Personage warrior) {

        double randomType = Math.random();
        Monster monster = new Monster();
        monster.setPersonageType(PersonageType.MONSTER);
        monster.setLvl(setLvl(warrior.getLvl()));
        if (randomType <= 0.40) {
            monster.setType(MonsterType.LOW);
            monster.setExperience(3 * monster.getLvl());
        }
        if (randomType > 0.40 && randomType <= 0.7) {
            monster.setType(MonsterType.MEDIUM);
            monster.setExperience(5 * monster.getLvl());
        }
        if (randomType > 0.7) {
            monster.setType(MonsterType.HIGH);
            monster.setExperience(7 * monster.getLvl());
        }
        monster.setStatus(setStatus(monster));
        return monster;
    }

    private Status setStatus(Monster monster) {
        Status status = new Status();
        int armor = 1;
        int life = 1;
        int damage = 1;
        int speed = 1;
        switch (monster.getType()) {
            case LOW:
                armor = (monster.getLvl() * 0.50) < 1 ? 1 : (int) Math.round(monster.getLvl() * 0.50);
                life = (monster.getLvl() * 0.50) < 1 ? 1 : (int) Math.round(monster.getLvl() * 0.50);
                damage = (monster.getLvl() * 0.50) < 1 ? 1 : (int) Math.round(monster.getLvl() * 0.50);
                speed = 1;
                break;
            case MEDIUM:
                armor = (monster.getLvl()) < 1 ? 1 : (int) Math.round(monster.getLvl());
                life = (monster.getLvl()) < 1 ? 1 : (int) Math.round(monster.getLvl());
                damage = (monster.getLvl()) < 1 ? 1 : (int) Math.round(monster.getLvl());
                speed = 1;
                break;
            case HIGH:
                armor = (monster.getLvl() * 2.5) < 1 ? 1 : (int) Math.round(monster.getLvl() * 2.5);
                life = (monster.getLvl() * 2.5) < 1 ? 1 : (int) Math.round(monster.getLvl() * 2.5);
                damage = (monster.getLvl() * 3) < 1 ? 1 : (int) Math.round(monster.getLvl() * 2.5);
                speed = 3;
                break;
        }
        status.setArmor(armor);
        status.setDamage(damage);
        status.setLife(life);
        status.setSpeed(speed);
        return status;
    }

    private int setLvl(int warriorLvl) {
        final long MIN = -3;
        final long MAX = 3;
        long randomLvl = Math.round(Math.random() * (MAX - (MIN) + 1) + MIN);
        return (randomLvl + warriorLvl <=0) ? 1 : (int) (randomLvl + warriorLvl);
    }
}
