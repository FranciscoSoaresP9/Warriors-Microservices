package com.example.play;

import com.example.play.batleInfo.BattleInfo;

import java.io.IOException;

/**
 * Class to simulate a pvm Battle
 */
public interface BattleSimulator {
    /**
     * Method to start a pvm battle simulation
     * @param warriorId
     * @return A info of Battle
     * @see BattleInfo
     */
    BattleInfo startSimulation(Integer warriorId) throws IOException;
}
