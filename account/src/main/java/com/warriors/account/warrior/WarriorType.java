package com.warriors.account.warrior;

/**
 * Enum of warrior types on application
 */
public enum WarriorType {
    ARCHER(5,2,2,2),
    WARRIOR(3,4,4,1),
    ASSASSIN(4,2,3,3);

    public int damage;
    public int life;
    public int armor;
    public int speed;

    WarriorType(int damage,int life,int armor,int speed){
        this.damage=damage;
        this.armor =armor;
        this.speed=speed;
        this.life=life;
    }


}
