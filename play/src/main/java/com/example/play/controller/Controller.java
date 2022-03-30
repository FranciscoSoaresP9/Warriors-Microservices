package com.example.play.controller;

import com.example.play.Play;
import com.example.play.RequestSender;
import com.example.play.batleInfo.BattleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@RequestMapping("/play")
public class Controller {
    private final Play play;

    @Autowired
    public Controller(Play play) {
        this.play = play;
    }

    @PostMapping("pvmfight/{warriorId}")
    public BattleInfo fight(@PathVariable Integer warriorId) {
            return play.start(warriorId);
    }
}
