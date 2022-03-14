package com.warriors.warrior.services;


import com.warriors.warrior.factory.WarriorFactory;
import com.warriors.warrior.jparepository.PointsRepository;
import com.warriors.warrior.jparepository.StatusRepository;
import com.warriors.warrior.jparepository.WarriorRepository;
import com.warriors.warrior.model.*;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private final RestTemplate restTemplate;
    @Autowired
    public WarriorService(StatusRepository statusRepository, PointsRepository pointsRepository, WarriorRepository warriorRepository, WarriorStatusConvert warriorStatusConvert, WarriorFactory warriorFactory, RestTemplate restTemplate) {
        this.statusRepository = statusRepository;
        this.pointsRepository = pointsRepository;
        this.warriorRepository = warriorRepository;
        this.warriorStatusConvert = warriorStatusConvert;

        this.warriorFactory = warriorFactory;
        this.restTemplate = restTemplate;
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

        Points defaultPoints = new Points( 0, 0, 0,0,0);
        Points points = pointsRepository.save(defaultPoints);

        warrior.setPoints(points);

        Status defaultStatus = new Status(0, 0, 0, 0);
        Status status = statusRepository.save(defaultStatus);
        warrior.setStatus(status);

      associateWarriorToAccount(warriorDTO.getAccountId(), warriorRepository.save(warrior));
    }

    private void associateWarriorToAccount(Integer accountId,Warrior warriorSaved){
        restTemplate.postForObject("http://ACCOUNT-SERVICE/addWarrior/"+accountId,setRequestValue(warriorSaved),String.class);
    }

    public boolean checkIfWarriorNameExist(WarriorDTO warriorDTO) {

        return warriorRepository.getWarriorByName(warriorDTO.getName()) != null;

    }

    public Warrior updateStatus(Warrior warrior) {
       //TODO put implemation there
        // updatableServicePointService.update(warrior.getPoints());
        Status statusChanged = warriorStatusConvert.pointsToStatus(warrior);
        //updatableServiceStatusService.update(statusChanged);

        Warrior warriorUpdated=get(warrior.getId());

        return warriorUpdated;

    }
    private HttpEntity<String> setRequestValue(Warrior warrior){
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject = setJsonValue(warrior);
        return new HttpEntity<>(jsonObject.toString(),headers);
    }
    private JSONObject setJsonValue(Warrior warrior){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",warrior.getId());
        jsonObject.put("name",warrior.getName());
        jsonObject.put("warriorType", WarriorType.WARRIOR);
        jsonObject.put("exeperince",warrior.getExperience());
        jsonObject.put("points",warrior.getPoints());
        jsonObject.put("status",warrior.getStatus());
        return jsonObject;
    }
}
