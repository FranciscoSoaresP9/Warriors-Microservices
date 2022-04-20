package com.example.play.controller;

import com.example.play.PvmBattle;
import com.example.play.batleInfo.BattleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class that accepts requests to Battles
 */
@RestController()
@RequestMapping("/play")
public class PlayController {
    private final PvmBattle pvmBattle;

    @Autowired
    public PlayController(PvmBattle pvmBattle) {
        this.pvmBattle = pvmBattle;
    }

    /**
     * A method that accepts one pvm fight
     * @param warriorId
     * @return
     */
    @PostMapping("pvmfight/{warriorId}")
    public BattleInfo fight(@PathVariable Integer warriorId) {
            return pvmBattle.startSimulation(warriorId);
    }
}
