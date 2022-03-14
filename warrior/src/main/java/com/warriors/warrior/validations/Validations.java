package com.warriors.warrior.validations;

import com.warriors.warrior.services.WarriorService;
import com.warriors.warrior.model.WarriorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to validate if the user input are correct
 */
@Service
public class Validations {

    private final WarriorService warriorService;

    @Autowired
    public Validations(WarriorService warriorService) {

        this.warriorService = warriorService;
    }



    /**
     * Check if the numbers match with verifications
     *
     * @param numberOfChars
     * @param value
     */
    private boolean checkNumberOfChars(String value, int numberOfChars) {

        if (value.length() >= numberOfChars) {
            return true;
        }
        return false;
    }

    public boolean checkIfWarriorNameExist(WarriorDTO warriorDTO){
        return warriorService.checkIfWarriorNameExist(warriorDTO);
    }

    public boolean isEmpty(WarriorDTO warriorDTO){
        return warriorDTO.getName()!=null&&warriorDTO.getName()!="";
    }


}
