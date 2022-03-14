package com.warriors.warrior;

import com.warriors.warrior.model.Points;
import com.warriors.warrior.model.Warrior;
import com.warriors.warrior.model.WarriorType;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestSender {

    private final RestTemplate restTemplate;

    @Autowired
    public RequestSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void associateWarriorToAccount(Integer accountId, Warrior warriorSaved) {

        restTemplate.postForObject("http://ACCOUNT-SERVICE/addWarrior/" + accountId, setRequestValueForWarrior(warriorSaved), String.class);
    }

    public Points persistPointsInDB(Points points){
       return restTemplate.postForObject("http://POINTS-SERVICE//updatePoints/",setRequestValueForPoints(points),Points.class);
    }



    private  HttpEntity<String> setRequestValueForWarrior(Warrior warrior) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject = setJsonValueOfWarrior(warrior);
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

    private  HttpEntity<String> setRequestValueForPoints(Points points) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject = setJsonValueOfPoints(points);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }

    private JSONObject setJsonValueOfPoints(Points points) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", points.getId());
        jsonObject.put("life",points.getLife());
        jsonObject.put("armor",points.getArmor());
        jsonObject.put("damage", points.getDamage());
        jsonObject.put("speed", points.getSpeed());
        jsonObject.put("pointsAvailable", points.getPointsAvailable());
        return jsonObject;
    }
}
