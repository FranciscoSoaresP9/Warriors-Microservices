package com.example.play;


import com.example.play.batleInfo.BattleInfo;
import com.example.play.character.Personage;

import com.example.play.character.PersonageType;
import com.example.play.character.Status;

import com.example.play.character.monster.MonsterFabric;
import com.example.play.character.warrior.Warrior;
import com.example.play.character.warrior.WarriorUpdateExperienceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Play {

    private final MonsterFabric monsterFabric;
    private Personage warrior;
    private Personage monster;
    private final RequestSender requestSender;

    @Autowired
    public Play(MonsterFabric monsterFabric, RequestSender requestSender) {
        this.monsterFabric = monsterFabric;
        this.requestSender = requestSender;
    }

    public BattleInfo start(Integer warriorId) {
        try {
            warrior = requestSender.getWarrior(warriorId);
            monster = monsterFabric.createMonster(warrior);

            Personage[] personagesOrder = setOrder(monster, warrior);
            Personage firstOne = personagesOrder[0];
            Personage secondOne = personagesOrder[1];
            while (firstOne.getStatus().getLife() > 0 && secondOne.getStatus().getLife() > 0) {

                fight(secondOne, firstOne.getStatus().getDamage());
                if (doubleAttack(firstOne.getStatus().getSpeed())) {
                    fight(secondOne, firstOne.getStatus().getDamage());
                }
                if (secondOne.getStatus().getLife() <= 0) {
                    break;
                }

                fight(firstOne, secondOne.getStatus().getDamage());
                if (doubleAttack(secondOne.getStatus().getSpeed())) {
                    fight(firstOne, secondOne.getStatus().getDamage());
                }
            }
            BattleInfo battleInfo = new BattleInfo();
            battleInfo.setMonster(monster);
            if (checkWinner(firstOne, secondOne).getPersonageType() == PersonageType.WARRIOR) {
                battleInfo.setExperienceEarn(monster.getExperience());
                battleInfo.setWin(true);
                System.out.println("XP--------------->"+monster.getExperience());
                sendRequestToUpdate((Warrior) warrior, monster.getExperience());
                return battleInfo;
            }
            battleInfo.setExperienceEarn(0);
            battleInfo.setWin(false);
            return battleInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void sendRequestToUpdate(Warrior warrior, int experience) {
        System.out.println("xp requestToUpdate------------>"+experience);
        WarriorUpdateExperienceDTO warriorUpdateExperienceDTO = new WarriorUpdateExperienceDTO();
        warriorUpdateExperienceDTO.setExperience(experience);
        warriorUpdateExperienceDTO.setId(warrior.getId());
        System.out.println("XP INSIDE WARRIOR----->"+warriorUpdateExperienceDTO.getExperience());
        try {
            requestSender.updateExperience(warriorUpdateExperienceDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Personage checkWinner(Personage firstOne, Personage secondOne) {
        if (firstOne.getStatus().getLife() > secondOne.getStatus().getLife()) {
            return firstOne;
        }
        return secondOne;
    }

    private void fight(Personage personage, int damage) {
        int armor = personage.getStatus().getArmor();
        int life = personage.getStatus().getLife();
        int damageToTake = damage;
        if (armor > 0) {
            armor = hitArmor(armor, damage);
            damageToTake = (armor <= 0) ? Math.abs(armor) : 0;
        }
        life -= damageToTake;
        Status status = personage.getStatus();
        status.setLife(life);
        status.setArmor(armor);
        personage.setStatus(status);
    }


    private int hitArmor(int actualArmor, int damage) {

        actualArmor -= damage;
        return actualArmor;
    }

    private boolean doubleAttack(int speed) {

        return Math.random() < speed * 0.02;
    }

    private Personage[] setOrder(Personage monster, Personage warrior) {
        int warriorSpeed = warrior.getStatus().getSpeed();
        int monsterSpeed = monster.getStatus().getSpeed();
        if (warriorSpeed == monsterSpeed) {
            return new Personage[]{warrior, monster};
        }
        return (warriorSpeed > monsterSpeed) ? new Personage[]{warrior, monster} : new Personage[]{monster, warrior};
    }

}
