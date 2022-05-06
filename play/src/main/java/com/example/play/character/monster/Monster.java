package com.example.play.character.monster;

import com.example.play.character.GameElements;
import com.example.play.character.GameElementType;
import com.example.play.character.warrior.Status;
/**
 * A model of Monster
 */
public class Monster extends GameElements {
    private MonsterType type;
    public MonsterType getType() {
        return type;
    }

    @Override
    public void setPersonageType(GameElementType gameElementType) {
        super.setPersonageType(gameElementType);
    }

    @Override
    public GameElementType getPersonageType() {
        return super.getPersonageType();
    }

    public void setType(MonsterType type) {
        this.type = type;
    }

    public Status getStatus() {
        return super.getStatus();
    }

    public void setStatus(Status status) {
        super.setStatus(status);
    }

    public int getExperience() {
        return super.getExperience();
    }

    public void setExperience(int experience) {
        super.setExperience(experience);
    }

    public int getLvl() {
        return super.getLvl();
    }

    public void setLvl(int lvl) {
        super.setLvl(lvl);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "type=" + type +
                '}';
    }
}
