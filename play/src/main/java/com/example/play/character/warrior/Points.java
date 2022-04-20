package com.example.play.character.warrior;

/**
 * A Points model
 */
public class Points {
    public static final int MAX_POINTS=50;

    private Integer id;
    private int life;
    private int armor;
    private int damage;
    private int speed;
    private int pointsAvailable;


    public Points(Integer id, int life, int armor, int damage, int speed, int pointsAvailable) {
        this.life = life;
        this.armor = armor;
        this.damage = damage;
        this.speed = speed;
        this.id=id;
        this.pointsAvailable = pointsAvailable;
    }

    public Points(int life, int armor, int damage, int speed,int pointsAvailable) {
        this.life = life;
        this.armor = armor;
        this.damage = damage;
        this.speed = speed;
        this.pointsAvailable = pointsAvailable;

    }
    public Points() {

    }

    public int getPointsAvailable() {
        return pointsAvailable;
    }

    public void setPointsAvailable(int pointAvailable) {
        this.pointsAvailable = pointAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setDamage(int attack) {
        this.damage = attack;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }



    public int getLife() {
        return life;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }


    @Override
    public String toString() {
        return "Points{" +
                "id=" + id +
                ", life=" + life +
                ", armor=" + armor +
                ", damage=" + damage +
                ", speed=" + speed +
                '}';
    }
}
