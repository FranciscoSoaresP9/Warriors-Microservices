package com.warriors.warrior.services;


import com.warriors.warrior.RequestSender;
import com.warriors.warrior.factory.WarriorFactory;
import com.warriors.warrior.jparepository.WarriorRepository;
import com.warriors.warrior.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Warrior Service
 **/

@Service
public class WarriorService implements Services<Warrior> {
    private final WarriorRepository warriorRepository;
    private final WarriorStatusConvert warriorStatusConvert;
    private final WarriorFactory warriorFactory;
    private final RequestSender requestSender;
    private final SearchForOpponent searchForOpponent;

    @Autowired
    public WarriorService(WarriorRepository warriorRepository, WarriorStatusConvert warriorStatusConvert, WarriorFactory warriorFactory, RequestSender requestSender, SearchForOpponent searchForOpponent) {
        this.warriorRepository = warriorRepository;
        this.warriorStatusConvert = warriorStatusConvert;
        this.warriorFactory = warriorFactory;
        this.requestSender = requestSender;
        this.searchForOpponent = searchForOpponent;
    }


    /**
     * Get Warrior
     *
     * @param id
     * @see WarriorRepository#findById(Object)
     */
    @Override
    public Warrior get(int id) {
        return warriorRepository.findById(id).get();
    }


    /**
     * Get all Warrior
     *
     * @see WarriorRepository#findAll()
     */
    @Override
    public Iterable<Warrior> getAll() {
        return warriorRepository.findAll();
    }


    public Warrior createWarrior(WarriorDTO warriorDTO) {

        Warrior warrior = warriorFactory.buildWarrior(warriorDTO);
        Status defaultStatus = warriorStatusConvert.pointsToStatus(warrior);
        warrior.setStatus(defaultStatus);
        return warrior;
    }

    public Warrior saveWarrior(Warrior warrior) {
        System.out.println(warrior);
        return warriorRepository.save(warrior);
    }


    public boolean isWarriorNameExist(String warriorName) {

        return warriorRepository.getWarriorByName(warriorName) != null;

    }

    public Warrior updateExperience(Integer warriorId, int experience) {
        Warrior warrior = get(warriorId);
        checkLvlUp(warrior, experience);
        return warriorRepository.save(warrior);
    }

    private void checkLvlUp(Warrior warrior, int experience) {
        int maxExperience = calculateMaxExperience(warrior);
        if ((warrior.getExperience() + experience) >= maxExperience) {
            warrior.lvlUp();
            Points points = warrior.getPoints();
            points.setPointsAvailable(points.getPointsAvailable() + 1);
            try {
                requestSender.updatePoints(points);
            } catch (IOException e) {
                e.printStackTrace();
            }
            warrior.setExperience((warrior.getExperience() + experience) - maxExperience);
            return;
        }
        warrior.earnExperience(experience);
    }

    private int calculateMaxExperience(Warrior warrior) {
        int lvl = warrior.getLvl();
        return lvl * 100;
    }

    public Warrior updateStatus(Warrior warrior) {

        try {
            requestSender.updatePoints(warrior.getPoints());
            Status statusChanged = warriorStatusConvert.pointsToStatus(warrior);
            requestSender.updateStatus(statusChanged);
            Warrior warriorUpdated = get(warrior.getId());
            return warriorUpdated;
        } catch (IOException e) {
            return null;
        }


    }

    public Warrior searchForOpponent(Warrior warrior) {
        List<Warrior> allWarrior = new LinkedList<>();
        getAll().forEach(allWarrior::add);
        return searchForOpponent.search(warrior, allWarrior);
    }


}
