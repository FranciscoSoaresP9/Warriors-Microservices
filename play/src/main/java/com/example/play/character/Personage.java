package com.example.play.character;


public abstract class Personage {
    private Status status;
    private int experience;
    private int lvl;
    private PersonageType personageType;

    public Personage(){
        personageType=PersonageType.WARRIOR;
    }

    public PersonageType getPersonageType() {
        return personageType;
    }

    public void setPersonageType(PersonageType personageType) {
        this.personageType = personageType;
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
