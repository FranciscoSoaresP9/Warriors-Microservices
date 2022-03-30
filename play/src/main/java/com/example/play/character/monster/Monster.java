package com.example.play.character.monster;

import com.example.play.character.Personage;
import com.example.play.character.PersonageType;
import com.example.play.character.Status;

public class Monster extends Personage {
    private MonsterType type;

    public MonsterType getType() {
        return type;
    }

    @Override
    public void setPersonageType(PersonageType personageType) {
        super.setPersonageType(personageType);
    }

    @Override
    public PersonageType getPersonageType() {
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

}
