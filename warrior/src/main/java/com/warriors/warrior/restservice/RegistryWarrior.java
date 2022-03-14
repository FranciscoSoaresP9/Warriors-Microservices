package com.warriors.warrior.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warriors.warrior.factory.WarriorFactory;
import com.warriors.warrior.messages.Messages;
import com.warriors.warrior.model.Warrior;
import com.warriors.warrior.model.WarriorType;
import com.warriors.warrior.services.WarriorService;
import com.warriors.warrior.validations.Validations;
import com.warriors.warrior.model.WarriorDTO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RegistryWarrior implements Registry<WarriorDTO> {
    private final Validations validations;
    private final WarriorService warriorService;


    @Autowired
    public RegistryWarrior(Validations validations, WarriorService warriorService) {
        this.validations = validations;
        this.warriorService = warriorService;

    }

    @Override
    public String registry(WarriorDTO warriorDTO) {
        if (!validations.isEmpty(warriorDTO)) {
            return Messages.FILL_THE_FIELDS.message;
        }

        if (!validations.checkIfWarriorNameExist(warriorDTO)) {

            warriorService.createWarrior(warriorDTO);

            return Messages.WARRIOR_CREATED.message;
        }
        return Messages.WARRIOR_NAME_TAKEN.message;
    }


}
