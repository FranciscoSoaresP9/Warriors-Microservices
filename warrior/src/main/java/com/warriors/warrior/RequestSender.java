package com.warriors.warrior;

import com.warriors.warrior.model.Points;
import com.warriors.warrior.model.Status;
import com.warriors.warrior.model.Warrior;
import com.warriors.warrior.model.WarriorType;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@Service
public class RequestSender {

    private final RestTemplate restTemplate = new RestTemplate();



    private String getIp() throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int lastIndexOfIp = line.lastIndexOf(":");

        return line.substring(line.indexOf("//") + 2, lastIndexOfIp);
    }

    public Points updatePoints(Points pointsUpdated) throws IOException {
        return restTemplate.postForObject("http://" + getIp() + ":8088/points/updatePoints",
                createRequest(setJsonValueOfPoints(pointsUpdated)), Points.class);
    }

    public Status updateStatus(Status statusUpdated) throws IOException {
        return restTemplate.postForObject("http://" + getIp() + ":8088/status/updateStatus/",
                createRequest(setJsonValueOfStatus(statusUpdated)), Status.class);
    }

    private HttpEntity<String> createRequest(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
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
