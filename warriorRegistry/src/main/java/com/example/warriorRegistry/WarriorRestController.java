package com.example.warriorRegistry;

import com.example.warriorRegistry.model.Warrior;
import com.example.warriorRegistry.model.WarriorDTO;
import com.example.warriorRegistry.registry.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Warrior> createWarrior(@RequestBody WarriorDTO warriorDTO) {
        System.out.println(warriorDTO);
        return new ResponseEntity<Warrior>(registryWarrior.registry(warriorDTO), HttpStatus.CREATED);

    }

}
