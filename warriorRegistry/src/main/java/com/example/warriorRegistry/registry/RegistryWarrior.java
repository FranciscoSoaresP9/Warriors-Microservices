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
    public Warrior registry(WarriorDTO warriorDTO) {
        try {
            if (!validations.checkIfWarriorNameExist(warriorDTO.getName())) {


                Warrior warrior = requestSender.createWarrior(warriorDTO);

                warrior = associateWarriorToEntitys(warrior, warriorDTO.getAccountId());
                return warrior;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Warrior associateWarriorToEntitys(Warrior warrior, Integer accountId) {
        try {
            associatePoints(warrior);
            associateStatus(warrior);
            warrior = saveWarrior(warrior);
            requestSender.associateWarriorToAccount(accountId, warrior);
            return warrior;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void associatePoints(Warrior warrior) throws IOException {
        Points points = requestSender.persistPointsInDB(warrior.getPoints());
        warrior.setPoints(points);
    }

    private void associateStatus(Warrior warrior) throws IOException {
        Status status = requestSender.persistStatusInDB(warrior.getStatus());
        warrior.setStatus(status);
    }

    private Warrior saveWarrior(Warrior warrior) throws IOException {
        return requestSender.saveWarrior(warrior);
    }
}