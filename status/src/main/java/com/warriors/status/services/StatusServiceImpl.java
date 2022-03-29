package com.warriors.status.services;


import com.warriors.status.jparepository.StatusRepository;
import com.warriors.status.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService<Status> {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status get(int id) {
        return statusRepository.findById(id).get();
    }

    public Status update(Status status){
        Status statusChanged= get(status.getId());
        statusChanged.setSpeed(status.getSpeed());
        statusChanged.setLife(status.getLife());
        statusChanged.setArmor(status.getArmor());
        statusChanged.setDamage(status.getDamage());

       return statusRepository.save(statusChanged);
    }

    @Override
    public Status Save(Status toSave) {
        return statusRepository.save(toSave);
    }
}
