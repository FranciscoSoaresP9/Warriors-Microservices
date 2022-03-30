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
    private final RequestSender requestSender;

    @Autowired
    public Play(MonsterFabric monsterFabric, RequestSender requestSender) {
        this.monsterFabric = monsterFabric;
        this.requestSender = requestSender;
    }

    public BattleInfo start(Integer warriorId) {
        try {
            Personage warrior = requestSender.getWarrior(warriorId);
            Personage monster = monsterFabric.createMonster(warrior);
            Personage[] orderToAttack = setOrder(monster, warrior);
            Personage playerOne = orderToAttack[0];
            Personage playerTwo = orderToAttack[1];

            fight(playerOne, playerTwo);

            return buildBattleInfo(playerOne, playerTwo);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private BattleInfo buildBattleInfo(Personage playerOne, Personage playerTwo) {
        BattleInfo battleInfo = new BattleInfo();
        Personage monster = playerOne.getPersonageType() == PersonageType.MONSTER ? playerOne : playerTwo;
        Personage warrior = playerOne.getPersonageType() == PersonageType.WARRIOR ? playerOne : playerTwo;
        battleInfo.setMonster(monster);

        if (checkWinner(playerOne, playerTwo) == PersonageType.WARRIOR) {
            battleInfo.setExperienceEarn(monster.getExperience());
            battleInfo.setWin(true);
            sendRequestToUpdate((Warrior) warrior, monster.getExperience());
            return battleInfo;
        }

        battleInfo.setExperienceEarn(0);
        battleInfo.setWin(false);
        return battleInfo;
    }

    private void fight(Personage playerOne, Personage playerTwo) {
        while (playerOne.getStatus().getLife() > 0 && playerTwo.getStatus().getLife() > 0) {
            round(playerTwo, playerOne.getStatus().getDamage());

            if (doubleAttack(playerOne.getStatus().getSpeed())) {
                round(playerTwo, playerOne.getStatus().getDamage());
            }

            if (playerTwo.getStatus().getLife() <= 0) {
                break;
            }

            round(playerOne, playerTwo.getStatus().getDamage());
            if (doubleAttack(playerTwo.getStatus().getSpeed())) {
                round(playerOne, playerTwo.getStatus().getDamage());
            }
        }
    }

    private void sendRequestToUpdate(Warrior warrior, int experience) {
        WarriorUpdateExperienceDTO warriorUpdateExperienceDTO = new WarriorUpdateExperienceDTO();
        warriorUpdateExperienceDTO.setExperience(experience);
        warriorUpdateExperienceDTO.setId(warrior.getId());

        try {
            requestSender.updateExperience(warriorUpdateExperienceDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PersonageType checkWinner(Personage firstOne, Personage secondOne) {
        if (firstOne.getStatus().getLife() > secondOne.getStatus().getLife()) {
            return firstOne.getPersonageType();
        }
        return secondOne.getPersonageType();
    }

    private void round(Personage personage, int damage) {
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
