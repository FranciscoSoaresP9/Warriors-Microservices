package com.example.play;

import com.example.play.batleInfo.BattleInfo;
import com.example.play.character.GameElements;
import com.example.play.character.warrior.Status;
import com.example.play.character.warrior.Warrior;
import com.example.play.character.warrior.WarriorUpdateExperienceDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class Battle {
    @Autowired
    private RequestSender requestSender;


    protected void fight(GameElements playerOne, GameElements playerTwo) {
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
     * Method to simulate a round of battle
     *
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
     *
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
     *
     * @param speed
     * @return
     */
    private boolean doubleAttack(int speed) {

        return Math.random() < speed * 0.02;
    }

    /**
     * set order of battle
     *
     * @param playerOne
     * @param playerTwo
     * @return
     */
    protected GameElements[] setOrder(GameElements playerOne, GameElements playerTwo) {
        int warriorSpeed = playerTwo.getStatus().getSpeed();
        int monsterSpeed = playerOne.getStatus().getSpeed();
        if (warriorSpeed == monsterSpeed) {
            return new GameElements[]{playerTwo, playerOne};
        }
        return (warriorSpeed > monsterSpeed) ? new GameElements[]{playerTwo, playerOne} : new GameElements[]{playerOne, playerTwo};
    }

    /**
     * Method to build a info of battle
     *
     * @param playerOne
     * @param playerTwo
     * @return
     */
    protected BattleInfo buildBattleInfo(GameElements playerOne, GameElements playerTwo, Integer warriorId) {
        BattleInfo battleInfo = new BattleInfo();
        battleInfo.setGameElementAttacked(warriorAttacked);
        battleInfo.setWarrior(warrior);
        battleInfo.setExperienceEarn(experience);
        battleInfo.setWin(win);

        GameElements warrior = playerOne.getId() == warriorId ? playerOne : playerTwo;
        GameElements warriorAttacked = playerTwo.getId() == 66996699 ? playerTwo : playerOne;
        battleInfo.setGameElementAttacked(warriorAttacked);
        battleInfo.setWarrior((Warrior) warrior);
        if (checkWinner(playerOne, playerTwo).equals(warriorId)) {
            battleInfo.setExperienceEarn(warriorAttacked.getExperience());
            battleInfo.setWin(true);
            sendRequestToUpdate((Warrior) warrior, warrior.getExperience());
            return battleInfo;
        }
        battleInfo.setExperienceEarn(0);
        battleInfo.setWin(false);
        return battleInfo;
    }


    /**
     * Method to check how win the battle
     *
     * @param firstOne
     * @param secondOne
     * @return return a monster or a warrior
     */
    private Integer checkWinner(GameElements firstOne, GameElements secondOne) {
        if (firstOne.getStatus().getLife() > secondOne.getStatus().getLife()) {
            return firstOne.getId();
        }
        return secondOne.getId();
    }



}


