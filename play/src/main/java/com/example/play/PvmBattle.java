package com.example.play;


import com.example.play.batleInfo.BattleInfo;
import com.example.play.character.GameElements;

import com.example.play.character.GameElementType;
import com.example.play.character.warrior.Status;

import com.example.play.fabric.MonsterFabric;
import com.example.play.character.warrior.Warrior;
import com.example.play.character.warrior.WarriorUpdateExperienceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * This class Simulate a battle between a monster and a player
 */
@Service
public class PvmBattle implements PvmBattleSimulator {

    private final MonsterFabric monsterFabric;
    private final RequestSender requestSender;

    @Autowired
    public PvmBattle(MonsterFabric monsterFabric, RequestSender requestSender) {
        this.monsterFabric = monsterFabric;
        this.requestSender = requestSender;
    }

    /**
     * Method to start simulation
     * @See BattleInfo
     * @param warriorId
     * @return BattleInfo
     */
    public BattleInfo startSimulation(Integer warriorId) {
        try {
            GameElements[] gameElements= bootStrap(warriorId);
            GameElements playerOne = gameElements[0];
            GameElements playerTwo = gameElements[1];

            fight(playerOne, playerTwo);

            return buildBattleInfo(playerOne, playerTwo);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Method to field GameObject[]
     * @param warriorId
     * @return
     * @throws IOException
     */

    private GameElements[] bootStrap(Integer warriorId) throws IOException {
        GameElements warrior = requestSender.getWarrior(warriorId);
        GameElements monster = monsterFabric.createMonster(warrior);
        GameElements[] orderToAttack = setOrder(monster, warrior);
        return orderToAttack;
    }

    /**
     * Method to build a info of battle
     * @param playerOne
     * @param playerTwo
     * @return
     */
    private BattleInfo buildBattleInfo(GameElements playerOne, GameElements playerTwo) {
        BattleInfo battleInfo = new BattleInfo();
        GameElements monster = playerOne.getPersonageType() == GameElementType.MONSTER ? playerOne : playerTwo;
        GameElements warrior = playerOne.getPersonageType() == GameElementType.WARRIOR ? playerOne : playerTwo;
        battleInfo.setMonster(monster);

        if (checkWinner(playerOne, playerTwo) == GameElementType.WARRIOR) {
            battleInfo.setExperienceEarn(monster.getExperience());
            battleInfo.setWin(true);
            sendRequestToUpdate((Warrior) warrior, monster.getExperience());
            return battleInfo;
        }

        battleInfo.setExperienceEarn(0);
        battleInfo.setWin(false);
        return battleInfo;
    }

    /**
     * Method to simulate a fight between a monster and a warrior
     * @param playerOne
     * @param playerTwo
     */
    private void fight(GameElements playerOne, GameElements playerTwo) {
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

    /**
     * Send a request to update experience earn at the end off batle
     * @param warrior
     * @param experience
     */
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

    /**
     * Method to check how win the battle
     * @param firstOne
     * @param secondOne
     * @return return a monster or a warrior
     */
    private GameElementType checkWinner(GameElements firstOne, GameElements secondOne) {
        if (firstOne.getStatus().getLife() > secondOne.getStatus().getLife()) {
            return firstOne.getPersonageType();
        }
        return secondOne.getPersonageType();
    }

    /**
     * Method to simulate a round of battle
     * @param personage
     * @param damage
     */
    private void round(GameElements personage, int damage) {
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

    /**
     * Method to calculate when a GameObject hits another's armor of GameObject
     * @param actualArmor
     * @param damage
     * @return return the actual armor of GameObject
     */
    private int hitArmor(int actualArmor, int damage) {
        actualArmor -= damage;
        return actualArmor;
    }

    /**
     * Method to calculate when a GameObject do a double Attack
     * @param speed
     * @return 
     */
    private boolean doubleAttack(int speed) {

        return Math.random() < speed * 0.02;
    }

    /**
     * set order of batle
     * @param monster
     * @param warrior
     * @return
     */
    private GameElements[] setOrder(GameElements monster, GameElements warrior) {
        int warriorSpeed = warrior.getStatus().getSpeed();
        int monsterSpeed = monster.getStatus().getSpeed();
        if (warriorSpeed == monsterSpeed) {
            return new GameElements[]{warrior, monster};
        }
        return (warriorSpeed > monsterSpeed) ? new GameElements[]{warrior, monster} : new GameElements[]{monster, warrior};
    }

}
