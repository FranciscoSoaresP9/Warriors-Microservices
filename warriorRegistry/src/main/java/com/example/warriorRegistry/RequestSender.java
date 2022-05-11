package com.example.warriorRegistry;


import com.example.warriorRegistry.model.*;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class RequestSender {

    private final RestTemplate restTemplate = new RestTemplate();


    public void associateWarriorToAccount(Integer accountId, Warrior warriorSaved) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("warriorId", warriorSaved.getId());
        restTemplate.postForObject("http://" + getIp() + ":8088/account/addWarrior/" + accountId+"/"+warriorSaved.getId(),
               createRequestWithoutJson(), String.class);
    }

    private String getIp() throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int lastIndexOfIp = line.lastIndexOf(":");

        return line.substring(line.indexOf("//") + 2, lastIndexOfIp);
    }

    public Points persistPointsInDB(Points points) throws IOException {
        return restTemplate.postForObject("http://" + getIp() + ":8088/points/savePoints/",
                createRequest(setJsonValueOfPoints(points)), Points.class);
    }

    public Status persistStatusInDB(Status defaultStatus) throws IOException {
        return restTemplate.postForObject("http://" + getIp() + ":8088/status/saveStatus/",
                createRequest(setJsonValueOfStatus(defaultStatus)), Status.class);
    }

    public Warrior createWarrior(WarriorDTO warriorDTO) throws IOException {
        return restTemplate.postForObject("http://" + getIp() + ":8088/warrior/create",
                createRequest(setJsonValueOfWarriorDTO(warriorDTO)), Warrior.class);
    }

    public Warrior saveWarrior(Warrior warrior) throws IOException {

        return restTemplate.postForObject("http://" + getIp() + ":8088/warrior/save",
                createRequest(setJsonValueOfWarrior(warrior)), Warrior.class);
    }

    public boolean isWarriorNameExist(String warriorName) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/warrior/iswarriornameexist/" + warriorName, Boolean.class);
    }

    private HttpEntity<String> createRequest(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }
    private HttpEntity<String> createRequestWithoutJson() {
        HttpHeaders headers = new HttpHeaders();

        return new HttpEntity<>(headers);
    }
    private JSONObject setJsonValueOfWarriorDTO(WarriorDTO warriorDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountId", warriorDTO.getAccountId());
        jsonObject.put("name", warriorDTO.getName());
        jsonObject.put("warriorType", warriorDTO.getWarriorType());
        return jsonObject;
    }

    private JSONObject setJsonValueOfWarrior(Warrior warrior) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", warrior.getId());
        jsonObject.put("name", warrior.getName());
        jsonObject.put("warriorType", warrior.getWarriorType());
        jsonObject.put("exeperince", warrior.getExperience());
        jsonObject.put("points", warrior.getPoints());
        jsonObject.put("status", warrior.getStatus());

        return jsonObject;
    }


    private JSONObject setJsonValueOfPoints(Points points) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", points.getId());
        jsonObject.put("life", points.getLife());
        jsonObject.put("armor", points.getArmor());
        jsonObject.put("damage", points.getDamage());
        jsonObject.put("speed", points.getSpeed());
        jsonObject.put("pointsAvailable", points.getPointsAvailable());
        return jsonObject;
    }

    private JSONObject setJsonValueOfStatus(Status status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", status.getId());
        jsonObject.put("life", status.getLife());
        jsonObject.put("armor", status.getArmor());
        jsonObject.put("damage", status.getDamage());
        jsonObject.put("speed", status.getSpeed());
        return jsonObject;
    }


}
