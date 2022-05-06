package com.example.play;

import com.example.play.batleInfo.BattleInfo;
import com.example.play.character.GameElements;
import com.example.play.character.warrior.Warrior;
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
        return buildBattleInfo(playerOne, playerTwo, warriorId);
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
