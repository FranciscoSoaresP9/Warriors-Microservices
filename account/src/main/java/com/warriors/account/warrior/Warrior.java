package com.warriors.account.warrior;

import javax.persistence.*;

/**
 * A warrior model
 */
@Entity
@Table(name = "warriors")
public class Warrior  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private WarriorType warriorType;
    private int lvl;
    private int experience;

    @OneToOne
    private Points points;
    @OneToOne
    private Status status;



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

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Warrior{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", warriorType=" + warriorType +
                ", lvl=" + lvl +
                ", experience=" + experience +
                ", points=" + points +
                ", status=" + status +
                '}';
    }
}
