package com.example.warriorRegistry;

import com.example.warriorRegistry.model.WarriorDTO;
import com.example.warriorRegistry.registry.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Class to serve warriors api
 */
@RestController
@RequestMapping(path = "/registrywarrior")
public class WarriorRestController {


    private final Registry registryWarrior;

    @Autowired
    public WarriorRestController( Registry registryWarrior) {
        this.registryWarrior = registryWarrior;
    }



    @PostMapping()
    public String createWarrior(@RequestBody WarriorDTO warriorDTO) {
        System.out.println("registry warrior");
        return registryWarrior.registry(warriorDTO);

    }

}
