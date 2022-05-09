package com.example.play;


import com.example.play.batleInfo.BattleInfo;
import com.example.play.character.GameElements;

import com.example.play.character.GameElementType;


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
public class PvmBattle extends Battle implements BattleSimulator {

    private final MonsterFabric monsterFabric;
    private final RequestSender requestSender;

    @Autowired
    public PvmBattle(MonsterFabric monsterFabric, RequestSender requestSender) {
        this.monsterFabric = monsterFabric;
        this.requestSender = requestSender;
    }

    /**
     * Method to start simulation
     *
     * @param warriorId
     * @return BattleInfo
     * @See BattleInfo
     */
    public BattleInfo startSimulation(Integer warriorId) {
        System.out.println("START SIMULATION");
        try {
            GameElements[] gameElements = bootStrap(warriorId);
            GameElements playerOne = gameElements[0];
            GameElements playerTwo = gameElements[1];

            super.fight(playerOne, playerTwo);

            return endOfSimulation(playerOne, playerTwo, warriorId);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private BattleInfo endOfSimulation(GameElements playerOne, GameElements playerTwo, Integer warriorId) throws IOException {
        GameElements warrior = playerOne.getId() == warriorId ? playerOne : playerTwo;
        GameElements warriorAttacked = playerTwo.getId() != warriorId ? playerTwo : playerOne;
        if (checkWinner(playerOne, playerTwo).equals(warriorId)) {
            sendRequestToUpdate(warriorId, warriorAttacked.getExperience());
            return buildBattleInfo(warrior, warriorAttacked, warriorAttacked.getExperience(), true);
        }
        return buildBattleInfo(warrior, warriorAttacked, 0, false);
    }

    private void sendRequestToUpdate(Integer warriorId, int experience) throws IOException {
        WarriorUpdateExperienceDTO warriorUpdateExperienceDTO = new WarriorUpdateExperienceDTO();
        warriorUpdateExperienceDTO.setExperience(experience);
        warriorUpdateExperienceDTO.setId(warriorId);
        requestSender.updateExperience(warriorUpdateExperienceDTO);
    }


    /**
     * Method to field GameObject[]
     *
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



}
