package com.example.play.character.warrior;


import com.example.play.character.Personage;
import com.example.play.character.Status;

/**
 * A warrior model
 */

public class Warrior extends Personage {

    private Integer id;
    private String name;
    private WarriorType warriorType;
    private Points points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WarriorType getWarriorType() {
        return warriorType;
    }

    public void setWarriorType(WarriorType warriorType) {
        this.warriorType = warriorType;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
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
        return "Warrior{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", warriorType=" + warriorType +
                ", lvl=" + super.getLvl() +
                ", experience=" + super.getExperience() +
                ", points=" + points +
                ", status=" + super.getStatus() +
                '}';
    }
}
