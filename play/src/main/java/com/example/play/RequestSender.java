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

@Service
public class RequestSender {

    private final RestTemplate restTemplate = new RestTemplate();

    /*@Autowired
    public RequestSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/
    private String getIp() throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int lastIndexOfIp = line.lastIndexOf(":");

        return line.substring(line.indexOf("//") + 2, lastIndexOfIp);
    }

    public WarriorUpdateExperienceDTO updateExperience(WarriorUpdateExperienceDTO warriorUpdateExperienceDTO) throws IOException {
        System.out.println("---------------------------------------------------------");
        System.out.println(warriorUpdateExperienceDTO.toString());
        return restTemplate.postForObject("http://" + getIp() + ":8088/warrior/updateexperience",
                createRequest(setJsonValueOfWarrior(warriorUpdateExperienceDTO)), WarriorUpdateExperienceDTO.class);
    }

    private HttpEntity<String> createRequest(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }

    private JSONObject setJsonValueOfWarrior(WarriorUpdateExperienceDTO warrior) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", warrior.getId());
        jsonObject.put("experience", warrior.getExperience());
        return jsonObject;
    }

    public Warrior getWarrior(Integer warriorId) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/warrior//api/get/"+warriorId,Warrior.class);

    }
}
