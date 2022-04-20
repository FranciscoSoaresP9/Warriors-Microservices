package com.example.play.character;


import com.example.play.character.warrior.Status;

/**
 * A abstrac class model of GameELements
 */
public abstract class GameElements {
    private Status status;
    private int experience;
    private int lvl;
    private GameElementType gameElementType;

    public GameElements(){
        gameElementType = GameElementType.WARRIOR;
    }

    public GameElementType getPersonageType() {
        return gameElementType;
    }

    public void setPersonageType(GameElementType gameElementType) {
        this.gameElementType = gameElementType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
