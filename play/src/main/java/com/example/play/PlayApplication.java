package com.example.play;


import com.example.play.character.Status;
import com.example.play.character.monster.MonsterFabric;
import com.example.play.character.warrior.Warrior;

public class PlayApplication {

    public static void main(String[] args) {

        MonsterFabric monsterFabric = new MonsterFabric();
        Warrior warrior = new Warrior();
        warrior.setLvl(1);
        Status status = new Status();
        status.setDamage(10);
        status.setLife(100);
        status.setArmor(100);
        status.setSpeed(1);
        warrior.setStatus(status);
        warrior.setExperience(10);

        Play play = new Play(monsterFabric);
        play.setWarrior(warrior);
        for (int i = 0; i < 15 ; i++) {

            System.out.println(play.start());
        }




    }

}
