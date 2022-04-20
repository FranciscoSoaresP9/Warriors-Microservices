package com.warriors.status.services;


import com.warriors.status.jparepository.StatusRepository;
import com.warriors.status.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that link the DataBase and Java
 */
@Service
public class StatusServiceImpl implements StatusService<Status> {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    /**
     * Method to save new Status in DataBase
     *
     * @param toSave
     * @return status saved
     */
    @Override
    public Status Save(Status toSave) {
        return statusRepository.save(toSave);
    }

    /**
     * Method to get a status from DataBase
     *
     * @param id
     * @return status
     */
    public Status get(int id) {
        return statusRepository.findById(id).get();
    }

    //TODO CHECK IF UPDATE WORK
    /**
     * Method to update Status from DataBase
     *
     * @param status
     * @return
     */
    public Status update(Status status) {

        /*Status statusChanged= get(status.getId());
        statusChanged.setSpeed(status.getSpeed());
        statusChanged.setLife(status.getLife());
        statusChanged.setArmor(status.getArmor());
        statusChanged.setDamage(status.getDamage());
*/
        return statusRepository.save(status);
    }

}
