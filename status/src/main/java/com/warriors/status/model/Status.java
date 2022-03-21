package com.warriors.status.model;

import javax.persistence.*;

@Entity
@Table(name = "Status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int life;
    private int armor;
    private int damage;
    private int speed;

    public Status(Integer id, int life, int armor, int damage, int speed) {
        this.life = life;
        this.armor = armor;
        this.damage = damage;
        this.speed = speed;
        this.id=id;
    }

    public Status(int life, int armor, int damage, int speed) {

        this.life = life;
        this.armor = armor;
        this.damage = damage;
        this.speed = speed;
    }


    public Status() {

    }

    public Integer  getId() {
        return id;
    }

    public void setId(Integer  id) {
        this.id = id;
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int attack) {
        this.damage = attack;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", life=" + life +
                ", armor=" + armor +
                ", damage=" + damage +
                ", speed=" + speed +
                '}';
    }
}
