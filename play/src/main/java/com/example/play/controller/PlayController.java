package com.example.play.controller;

import com.example.play.PvmBattle;
import com.example.play.PvpBattle;
import com.example.play.batleInfo.BattleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * Class that accepts requests to Battles
 */
@RestController()
@RequestMapping("/play")
public class PlayController {
    private final PvmBattle pvmBattle;
    private final PvpBattle pvpBattle;

    @Autowired
    public PlayController(PvmBattle pvmBattle, PvpBattle pvpBattle) {
        this.pvmBattle = pvmBattle;
        this.pvpBattle = pvpBattle;
    }

    /**
     * A method that accepts one pvm fight
     *
     * @param warriorId
     * @return
     */
    @PostMapping("pvmfight/{warriorId}")
    public BattleInfo fight(@PathVariable Integer warriorId) {
        System.out.println(pvmBattle.startSimulation(warriorId));
        return pvmBattle.startSimulation(warriorId);
    }


    @PostMapping("pvpFight/{warriorId}")
    public BattleInfo fightPvp(@PathVariable Integer warriorId) {
        try {
            return pvpBattle.startSimulation(warriorId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
