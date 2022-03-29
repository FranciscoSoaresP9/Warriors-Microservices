package com.example.play.batleInfo;


import com.example.play.character.Personage;

public class BattleInfo {
    private Personage monster;
    private boolean win;

    public Personage getMonster() {
        return monster;
    }

    public void setMonster(Personage personage) {
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

    private Integer experienceEarn;

}
