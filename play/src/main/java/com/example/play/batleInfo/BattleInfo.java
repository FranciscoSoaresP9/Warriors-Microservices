package com.example.play.batleInfo;


import com.example.play.character.GameElements;

/**
 * This class is a BattleInfo model
 */
public class BattleInfo {
    private GameElements monster;
    private boolean win;
    private Integer experienceEarn;

    public GameElements getMonster() {
        return monster;
    }

    public void setMonster(GameElements personage) {
        this.monster = personage;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Integer getExperienceEarn() {
        return experienceEarn;
    }

    public void setExperienceEarn(Integer experienceEarn) {
        this.experienceEarn = experienceEarn;
    }


}
