package com.warriors.warrior.services;


import com.warriors.warrior.RequestSender;
import com.warriors.warrior.factory.WarriorFactory;
import com.warriors.warrior.jparepository.PointsRepository;
import com.warriors.warrior.jparepository.StatusRepository;
import com.warriors.warrior.jparepository.WarriorRepository;
import com.warriors.warrior.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Warrior Service
 **/

@Service
public class WarriorService implements Services<Warrior> {
    private final StatusRepository statusRepository;
    private final PointsRepository pointsRepository;
    private final WarriorRepository warriorRepository;
    private final WarriorStatusConvert warriorStatusConvert;
    private final WarriorFactory warriorFactory;
    private final RequestSender requestSender;

    @Autowired
    public WarriorService(StatusRepository statusRepository, PointsRepository pointsRepository, WarriorRepository warriorRepository, WarriorStatusConvert warriorStatusConvert, WarriorFactory warriorFactory, RequestSender requestSender) {
        this.statusRepository = statusRepository;
        this.pointsRepository = pointsRepository;
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
        Points points = requestSender.persistPointsInDB(defaultPoints);

        warrior.setPoints(points);

        Status defaultStatus = new Status(0, 0, 0, 0);
        Status status = statusRepository.save(defaultStatus);
        warrior.setStatus(status);

        requestSender.associateWarriorToAccount(warriorDTO.getAccountId(), warriorRepository.save(warrior));
    }


    public boolean checkIfWarriorNameExist(WarriorDTO warriorDTO) {

        return warriorRepository.getWarriorByName(warriorDTO.getName()) != null;

    }

    public Warrior updateStatus(Warrior warrior) {
        //TODO put implemation there
        // updatableServicePointService.update(warrior.getPoints());
        Status statusChanged = warriorStatusConvert.pointsToStatus(warrior);
        //updatableServiceStatusService.update(statusChanged);

        Warrior warriorUpdated = get(warrior.getId());

        return warriorUpdated;

    }

}
