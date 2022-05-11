package com.example.play;

import com.example.play.batleInfo.BattleInfo;
import com.example.play.character.GameElements;
import com.example.play.character.warrior.Warrior;
import com.example.play.character.warrior.WarriorUpdateExperienceDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PvpBattle extends Battle implements BattleSimulator {
    private final RequestSender requestSender;

    public PvpBattle(RequestSender requestSender) {
        this.requestSender = requestSender;
    }

    @Override
    public BattleInfo startSimulation(Integer warriorId) throws IOException {
        GameElements[] gameElements = bootStrap(warriorId);
        GameElements playerOne = gameElements[0];
        GameElements playerTwo = gameElements[1];
        fight(playerOne, playerTwo);
        return endOfSimulation(playerOne, playerTwo, warriorId);
    }

    private BattleInfo endOfSimulation(GameElements playerOne, GameElements playerTwo, Integer warriorId) throws IOException {
        GameElements warrior = playerOne.getId() == warriorId ? playerOne : playerTwo;
        GameElements warriorAttacked = playerTwo.getId() != warriorId ? playerTwo : playerOne;
        if (checkWinner(playerOne, playerTwo).equals(warriorId)) {
            sendRequestToUpdate(warriorId, calculateXp(warriorAttacked));
            return buildBattleInfo(warrior, warriorAttacked, calculateXp(warriorAttacked), true);
        }
        return buildBattleInfo(warrior, warriorAttacked, 0, false);
    }

    private int calculateXp(GameElements warriorAttacked) {
        int finalXp = warriorAttacked.getExperience()/4;
        System.out.println("FINAL XP");
        System.out.println(finalXp);

        return (finalXp) <= 0 ? 20 : finalXp;
    }

    private void sendRequestToUpdate(Integer warriorId, int experience) throws IOException {
        WarriorUpdateExperienceDTO warriorUpdateExperienceDTO = new WarriorUpdateExperienceDTO();
        warriorUpdateExperienceDTO.setExperience(experience);
        warriorUpdateExperienceDTO.setId(warriorId);
        requestSender.updateExperience(warriorUpdateExperienceDTO);
    }

    private GameElements[] bootStrap(Integer warriorId) throws IOException {
        GameElements warrior = requestSender.getWarrior(warriorId);
        GameElements warriorToAttack = requestSender.getWarriorToFight(warriorId);
        GameElements[] orderToAttack = super.setOrder(warriorToAttack, warrior);
        return orderToAttack;
    }

    private Warrior getWarriorToFight(Integer warriorId) throws IOException {
        return requestSender.getWarriorToFight(warriorId);
    }

    private Warrior getWarrior(Integer warriorId) throws IOException {
        return requestSender.getWarrior(warriorId);
    }
}
