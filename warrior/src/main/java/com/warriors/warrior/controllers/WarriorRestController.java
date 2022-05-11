package com.warriors.warrior.controllers;

import com.warriors.warrior.model.WarriorUpdateExperienceDTO;
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
     *
     * @see WarriorService
     */
    private final WarriorService warriorService;

    @Autowired
    public WarriorRestController(WarriorService warriorService) {
        this.warriorService = warriorService;
    }


    /**
     * GetAll
     *
     * @return a json with all warriors
     */
    @GetMapping(path = "/api/get/all")
    public Iterable<Warrior> getall() {
        return warriorService.getAll();
    }

    /**
     * Get one Warrior
     *
     * @param id
     * @return a json with a warriors
     */
    @GetMapping(path = "/api/get/{id}")
    public Warrior get(@PathVariable Integer id) {
        return warriorService.get(id);
    }

    @PostMapping(path = "/create")
    public Warrior createWarrior(@RequestBody WarriorDTO warriorDTO) {
        System.out.println(warriorDTO);
        System.out.println("CREATE");
        return warriorService.createWarrior(warriorDTO);

    }

    @GetMapping("/searchForOpponent/{warriorId}")
    public Warrior searchForOpponent(@PathVariable Integer warriorId) {
        System.out.println("where");
        return warriorService.searchForOpponent(warriorId);
    }

    @PostMapping(path = "/save")
    public Warrior saveWarrior(@RequestBody Warrior warrior) {
        System.out.println("SAVE");
        return warriorService.saveWarrior(warrior);

    }

    @GetMapping(path = "/iswarriornameexist/{warriorName}")
    public boolean isWarriorNameExist(@PathVariable String warriorName) {
        return warriorService.isWarriorNameExist(warriorName);
    }

    @PutMapping(path = "/updatestatus")
    public Warrior upDateStatus(@RequestBody Warrior warrior) {
        return warriorService.updateStatus(warrior);
    }

    @PostMapping(path = "/updateexperience")
    public Warrior upDateExeperience(@RequestBody WarriorUpdateExperienceDTO warriorUpdateExperienceDTO) {
        return warriorService.
                updateExperience(warriorUpdateExperienceDTO.getId(), warriorUpdateExperienceDTO.getExperience());
    }
}
