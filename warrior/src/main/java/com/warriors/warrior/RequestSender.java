package com.warriors.warrior;

import com.warriors.warrior.model.Points;
import com.warriors.warrior.model.Status;
import com.warriors.warrior.model.Warrior;
import com.warriors.warrior.model.WarriorType;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestSender {

    private final RestTemplate restTemplate= new RestTemplate();
    @Value("${database.url}")
    private String url;

    /*@Autowired
    public RequestSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }*/

    public void associateWarriorToAccount(Integer accountId, Warrior warriorSaved) {

        restTemplate.postForObject("http://ACCOUNT-SERVICE/addWarrior/" + accountId,
                createRequest(setJsonValueOfWarrior(warriorSaved)), String.class);
    }

    public Points persistPointsInDB(Points points) {
        return restTemplate.postForObject("http://POINTS-SERVICE/savePoints/",
                createRequest(setJsonValueOfPoints(points)), Points.class);
    }

    public Status persistStatusInDB(Status defaultStatus) {
        return restTemplate.postForObject("http://STATUS-SERVICE/saveStatus/",
                createRequest(setJsonValueOfStatus(defaultStatus)), Status.class);
    }

    public Points updatePoints(Points pointsUpdated) {
        System.out.println(url+ "/points/updatePoints");
        return restTemplate.postForObject(url+"/points/updatePoints",
                createRequest(setJsonValueOfPoints(pointsUpdated)), Points.class);
    }

    public Status updateStatus(Status statusUpdated) {
        System.out.println(url);
        return restTemplate.postForObject(url+"/status/updateStatus/",
                createRequest(setJsonValueOfStatus(statusUpdated)), Status.class);
    }

    private HttpEntity<String> createRequest(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }

    private JSONObject setJsonValueOfWarrior(Warrior warrior) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", warrior.getId());
        jsonObject.put("name", warrior.getName());
        jsonObject.put("warriorType", WarriorType.WARRIOR);
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
