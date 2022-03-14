package com.warriors.warrior.services;

import com.warriors.warrior.jparepository.StatusRepository;
import com.warriors.warrior.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatusService implements UpdatableService<Status> {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status get(int id) {
        return statusRepository.findById(id).get();
    }

    @Override
    public Iterable<Status> getAll() {
        return statusRepository.findAll();
    }

    public void update(Status status){
        Status statusChanged= get(status.getId());
        statusChanged.setSpeed(status.getSpeed());
        statusChanged.setLife(status.getLife());
        statusChanged.setArmor(status.getArmor());
        statusChanged.setDamage(status.getDamage());

        statusRepository.save(statusChanged);
    }
}
