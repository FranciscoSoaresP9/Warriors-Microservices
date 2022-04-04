package com.example.warriorRegistry.validations;

import com.example.warriorRegistry.RequestSender;
import com.example.warriorRegistry.model.WarriorDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Class to validate if the user input are correct
 */
@Service
public class Validations {

private final RequestSender requestSender;

    public Validations(RequestSender requestSender) {
        this.requestSender = requestSender;
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

    public boolean checkIfWarriorNameExist(String warriorName){
        try {
            return requestSender.isWarriorNameExist(warriorName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isEmpty(WarriorDTO warriorDTO){
        return warriorDTO.getName()!=null&&warriorDTO.getName()!="";
    }


}
