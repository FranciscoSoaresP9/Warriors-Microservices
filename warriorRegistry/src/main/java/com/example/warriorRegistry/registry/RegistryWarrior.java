package com.example.warriorRegistry.registry;

import com.example.warriorRegistry.RequestSender;
import com.example.warriorRegistry.messages.Messages;
import com.example.warriorRegistry.model.Points;
import com.example.warriorRegistry.model.Status;
import com.example.warriorRegistry.model.Warrior;
import com.example.warriorRegistry.model.WarriorDTO;
import com.example.warriorRegistry.validations.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class RegistryWarrior implements Registry<WarriorDTO> {
    private final Validations validations;
    private final RequestSender requestSender;


    @Autowired
    public RegistryWarrior(Validations validations, RequestSender requestSender) {
        this.validations = validations;

        this.requestSender = requestSender;
    }

    @Override
    public String registry(WarriorDTO warriorDTO) {
        try {
            if (!validations.isEmpty(warriorDTO)) {
                return Messages.FILL_THE_FIELDS.message;
            }

            if (!validations.checkIfWarriorNameExist(warriorDTO.getName())) {


                Warrior warrior = requestSender.createWarrior(warriorDTO);

                associateWarriorToEntitys(warrior, warriorDTO.getAccountId());

                return Messages.WARRIOR_CREATED.message;
            }
            return Messages.WARRIOR_NAME_TAKEN.message;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Messages.GENERIC_ERROR.message;
    }

    private void associateWarriorToEntitys(Warrior warrior, Integer accountId) {


        try {
            Points points = requestSender.persistPointsInDB(warrior.getPoints());
            warrior.setPoints(points);

            Status status = requestSender.persistStatusInDB(warrior.getStatus());
            warrior.setStatus(status);

            requestSender.associateWarriorToAccount(accountId, requestSender.saveWarrior(warrior));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}