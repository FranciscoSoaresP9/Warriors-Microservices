package com.example.play.batleInfo;


import com.example.play.character.GameElements;
import com.example.play.character.warrior.Warrior;

/**
 * This class is a BattleInfo model
 */
public class BattleInfo {
    private GameElements gameElementAttacked;
    private Warrior warrior;
    private boolean win;
    private Integer experienceEarn;

    public GameElements getGameElementAttacked() {
        return gameElementAttacked;
    }

    public void setGameElementAttacked(GameElements personage) {
        this.gameElementAttacked = personage;
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


    public Warrior getWarrior() {
        return warrior;
    }

    public void setWarrior(Warrior warrior) {
        this.warrior = warrior;
    }

    @Override
    public String toString() {
        return "BattleInfo{" +
                "gameElementAttacked=" + gameElementAttacked +
                ", warrior=" + warrior +
                ", win=" + win +
                ", experienceEarn=" + experienceEarn +
                '}';
    }
}
