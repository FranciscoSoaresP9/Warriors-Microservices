package com.example.play;


import com.example.play.character.Personage;

import com.example.play.character.PersonageType;
import com.example.play.character.Status;

import com.example.play.character.monster.MonsterFabric;


public class Play {

    private final MonsterFabric monsterFabric;
    private Personage warrior;
    private Personage monster;

    //  TODO  @Autowired
    public Play(MonsterFabric monsterFabric) {
        this.monsterFabric = monsterFabric;
    }

    public int start() {
          monster = monsterFabric.createMonster(warrior);

        Personage[] personagesOrder = setOrder(monster, warrior);
        Personage firstOne = personagesOrder[0];
        Personage secondOne = personagesOrder[1];

        System.out.println(firstOne.toString());
        System.out.println(secondOne.toString());
        while (firstOne.getStatus().getLife() > 0 && secondOne.getStatus().getLife() > 0) {

            fight(secondOne,firstOne.getStatus().getDamage());
            if(doubleAttack(firstOne.getStatus().getSpeed())){
                fight(secondOne,firstOne.getStatus().getDamage());
            }
           if(secondOne.getStatus().getLife()<=0){
               break;
           }

            fight(firstOne,secondOne.getStatus().getDamage());
            if(doubleAttack(secondOne.getStatus().getSpeed())){
                fight(firstOne,secondOne.getStatus().getDamage());
            }
        }
        if(checkWinner(firstOne,secondOne).getPersonageType()== PersonageType.WARRIOR){
            return monster.getExperience();
        }
        return 0;
    }

    private Personage checkWinner(Personage firstOne,Personage secondOne){
        if(firstOne.getStatus().getLife()>secondOne.getStatus().getLife()){
            return firstOne;
        }
        return secondOne;
    }

    private void fight(Personage personage, int damage) {
        int armor = personage.getStatus().getArmor();
        int life = personage.getStatus().getLife();
        int damageToTake = damage;
        if (armor > 0) {
            armor= hitArmor(armor, damage);
            damageToTake = (armor <= 0) ? Math.abs(armor) : 0;
        }
        life -= damageToTake;
        Status status= personage.getStatus();
        status.setLife(life);
        status.setArmor(armor);
        personage.setStatus(status);
    }


    private int hitArmor(int actualArmor, int damage) {

        actualArmor -= damage;
        return actualArmor;
    }

    private boolean doubleAttack(int speed) {

        return Math.random() < speed * 0.02;
    }

    private Personage[] setOrder(Personage monster, Personage warrior) {
        int warriorSpeed = warrior.getStatus().getSpeed();
        int monsterSpeed = monster.getStatus().getSpeed();
        if (warriorSpeed == monsterSpeed) {
            return new Personage[]{warrior, monster};
        }
        return (warriorSpeed > monsterSpeed) ? new Personage[]{warrior, monster} : new Personage[]{monster, warrior};
    }

    public void setWarrior(Personage warrior) {
        this.warrior = warrior;
    }
}
