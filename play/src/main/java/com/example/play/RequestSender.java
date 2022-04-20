package com.example.play;


import com.example.play.character.warrior.Warrior;
import com.example.play.character.warrior.WarriorUpdateExperienceDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import net.minidev.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class is responsible for send request to the others services
 */
@Service
public class RequestSender {
    /**
     * @see RestTemplate
     */
    private final RestTemplate restTemplate = new RestTemplate();
    /**
     * This method is used to get the ip of the server
     *
     * @return ipOfServer
     * @throws IOException
     */
    private String getIp() throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int lastIndexOfIp = line.lastIndexOf(":");

        return line.substring(line.indexOf("//") + 2, lastIndexOfIp);
    }

    /**
     * This method send a request to Warrior service to update the experience of a warrior
     * @param warriorUpdateExperienceDTO
     * @return
     * @throws IOException
     */
    public WarriorUpdateExperienceDTO updateExperience(WarriorUpdateExperienceDTO warriorUpdateExperienceDTO) throws IOException {
        return restTemplate.postForObject("http://" + getIp() + ":8088/warrior/updateexperience",
                createRequest(setJsonValueOfWarrior(warriorUpdateExperienceDTO)), WarriorUpdateExperienceDTO.class);
    }

    /**
     * This method create a HttpEntity
     * @see HttpEntity
     * @param jsonObject
     * @return
     */
    private HttpEntity<String> createRequest(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }

    /**
     * This method transform the warriorUpdateExperienceDTO into JSONObject
     * @see JSONObject
     * @param warrior
     * @return
     */

    private JSONObject setJsonValueOfWarrior(WarriorUpdateExperienceDTO warrior) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", warrior.getId());
        jsonObject.put("experience", warrior.getExperience());
        return jsonObject;
    }

    /**
     * This method is used to get a warrior from warrior Service
     * @param warriorId
     * @return
     * @throws IOException
     */
    public Warrior getWarrior(Integer warriorId) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/warrior//api/get/"+warriorId,Warrior.class);

    }
}
