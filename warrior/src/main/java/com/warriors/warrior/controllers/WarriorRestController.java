package com.warriors.warrior.controllers;

import com.warriors.warrior.model.WarriorUpdateExperienceDTO;
import com.warriors.warrior.restservice.Registry;
import com.warriors.warrior.services.WarriorService;
import com.warriors.warrior.model.Warrior;
import com.warriors.warrior.model.WarriorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * Class to serve warriors api
 */
@RestController
@RequestMapping(path = "/warrior")
public class WarriorRestController {


    /**
     * warriorService
     * @see WarriorService
     */
    private final WarriorService warriorService;
    private final Registry registryWarrior;

    @Autowired
    public WarriorRestController(WarriorService warriorService, Registry registryWarrior) {
        this.warriorService = warriorService;
        this.registryWarrior = registryWarrior;
    }


    /**
     * GetAll
     * @return a json with all warriors
     */
    @GetMapping(path = "/api/get/all")
    public Iterable<Warrior> getall() {
        return warriorService.getAll();
    }

    /**
     * Get one Warrior
     * @param id
     * @return a json with a warriors
     */
    @GetMapping(path = "/api/get/{id}")
    public Warrior get(@PathVariable Integer id) {
        return warriorService.get(id);
    }

    @PostMapping(path = "/createwarrior")
    public String createWarrior(@RequestBody WarriorDTO warriorDTO) {
        return registryWarrior.registry(warriorDTO);

    }

    @PutMapping(path = "/updatestatus")
    public Warrior upDateStatus(@RequestBody Warrior warrior) {
        return warriorService.updateStatus(warrior);
    }

    @PostMapping(path = "/updateexperience")
    public Warrior upDateExeperience(@RequestBody WarriorUpdateExperienceDTO warriorUpdateExperienceDTO) {
        System.out.println("--------------------------------------------");
        System.out.println(warriorUpdateExperienceDTO.toString());
        return warriorService.
                updateExperience(warriorUpdateExperienceDTO.getId(),warriorUpdateExperienceDTO.getExperience());
    }
}
