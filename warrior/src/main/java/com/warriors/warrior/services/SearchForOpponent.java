package com.warriors.warrior.services;

import com.warriors.warrior.model.Warrior;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchForOpponent {

    public Warrior search(Warrior warrior, List<Warrior> allWarriors) {
        return makeMoreCloseSearch(warrior, allWarriors, 1, 1).get();
    }


    private Optional<Warrior> makeMoreCloseSearch(Warrior warrior, List<Warrior> allWarriors, int minLvl, int maxLvl) {
        List<Warrior> warriorFiltered = allWarriors.stream().
                filter(warrior1 -> warriorMatchCalculator(warrior1, warrior, minLvl, maxLvl)).collect(Collectors.toList());
        if (warriorFiltered.isEmpty()) {
            return makeMoreCloseSearch(warrior, allWarriors, minLvl + 1, maxLvl + 1);
        }
        Warrior warriorFound = allWarriors.get(random(allWarriors.size()));
        if(warriorFound.getId()==warrior.getId()){
            return makeMoreCloseSearch(warrior, allWarriors, minLvl + 1, maxLvl + 1);
        }
        return Optional.of(warriorFound);

    }

    private boolean warriorMatchCalculator(Warrior warrior1, Warrior warrior2, int minLvl, int maxLvl) {

        return warrior1.getLvl() == warrior2.getLvl() ||
                warrior1.getLvl() == (warrior2.getLvl() - minLvl)
                || (warrior1.getLvl() == warrior2.getLvl() + maxLvl);
    }

    private int random(int maxRange) {
        return (int) Math.floor(Math.random() * maxRange);
    }

}
