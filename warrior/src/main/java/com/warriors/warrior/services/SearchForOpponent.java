package com.warriors.warrior.services;

import com.warriors.warrior.model.Warrior;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchForOpponent {

    public Warrior search(Warrior warrior, List<Warrior> allWarriors) {
       return makeMoreCloseSearch(warrior, allWarriors).get();
    }

    private boolean warriorMatchCalculator(Warrior warrior1, Warrior warrior2) {

        return warrior1.getLvl() == warrior2.getLvl() ||
                warrior1.getLvl() == (warrior2.getLvl() - 1)
                || (warrior1.getLvl() == warrior2.getLvl() + 1);
    }

    private Optional<Warrior> makeMoreCloseSearch(Warrior warrior, List<Warrior> allWarriors) {
        List<Warrior> warriorFiltered = allWarriors.stream().
                filter(warrior1 -> warriorMatchCalculator(warrior1, warrior)).collect(Collectors.toList());
        if (warriorFiltered.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(allWarriors.get(random(allWarriors.size())));

    }

    private int random(int maxRange) {
        return (int) Math.floor(Math.random() * maxRange);
    }

}
