package com.warriors.warrior.services;


import com.warriors.warrior.RequestSender;
import com.warriors.warrior.factory.WarriorFactory;
import com.warriors.warrior.jparepository.WarriorRepository;
import com.warriors.warrior.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Warrior Service
 **/

@Service
public class WarriorService implements Services<Warrior> {
    private final WarriorRepository warriorRepository;
    private final WarriorStatusConvert warriorStatusConvert;
    private final WarriorFactory warriorFactory;
    private final RequestSender requestSender;

    @Autowired
    public WarriorService(WarriorRepository warriorRepository, WarriorStatusConvert warriorStatusConvert, WarriorFactory warriorFactory, RequestSender requestSender) {
        this.warriorRepository = warriorRepository;
        this.warriorStatusConvert = warriorStatusConvert;
        this.warriorFactory = warriorFactory;
        this.requestSender = requestSender;
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


    public void createWarrior(WarriorDTO warriorDTO) {

        Warrior warrior = warriorFactory.buildWarrior(warriorDTO);

        Points defaultPoints = new Points(0, 0, 0, 0, 0);
        Points points = null;
        try {
            points = requestSender.persistPointsInDB(defaultPoints);
            warrior.setPoints(points);

            Status defaultStatus = new Status(0, 0, 0, 0);
            Status status = requestSender.persistStatusInDB(defaultStatus);
            warrior.setStatus(status);

            requestSender.associateWarriorToAccount(warriorDTO.getAccountId(), warriorRepository.save(warrior));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public boolean checkIfWarriorNameExist(WarriorDTO warriorDTO) {

        return warriorRepository.getWarriorByName(warriorDTO.getName()) != null;

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

}
