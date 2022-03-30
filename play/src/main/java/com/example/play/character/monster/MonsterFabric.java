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

        switch (monster.getType()) {
            case LOW:
                return buildStatus(0.5, 0.5, 0.5, 1, monster.getLvl());
            case MEDIUM:
                return buildStatus(1, 1, 1, 1, monster.getLvl());
            default:
                return buildStatus(2.5, 2.5, 3.0, 1, monster.getLvl());

        }

    }

    private Status buildStatus(double armorOdd, double lifeOdd, double damgeOdd, int speedValue, int monsterLvl) {
        Status status = new Status();
        int armor = 1;
        int life = 1;
        int damage = 1;
        int speed = 1;
        armor = statusCalculator(armorOdd, monsterLvl);
        life = statusCalculator(lifeOdd, monsterLvl);
        damage = statusCalculator(damgeOdd, monsterLvl);
        speed = 3;
        status.setArmor(armor);
        status.setDamage(damage);
        status.setLife(life);
        status.setSpeed(speed);
        return status;
    }

    private int statusCalculator(double odd, int lvl) {
        return (lvl * odd) < 1 ? 1 : (int) Math.round(lvl * odd);
    }

    private int setLvl(int warriorLvl) {
        final long MIN = -3;
        final long MAX = 3;
        long randomLvl = Math.round(Math.random() * (MAX - (MIN) + 1) + MIN);
        return (randomLvl + warriorLvl <= 0) ? 1 : (int) (randomLvl + warriorLvl);
    }
}
