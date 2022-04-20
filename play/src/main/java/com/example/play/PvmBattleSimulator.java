package com.example.play;

import com.example.play.batleInfo.BattleInfo;

/**
 * Class to simulate a pvm Battle
 */
public interface PvmBattleSimulator {
    /**
     * Method to start a pvm battle simulation
     * @param warriorId
     * @return A info of Battle
     * @see BattleInfo
     */
    BattleInfo startSimulation(Integer warriorId);
}
