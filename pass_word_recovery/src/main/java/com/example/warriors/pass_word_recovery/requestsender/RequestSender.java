package com.example.warriors.pass_word_recovery.requestsender;


import com.example.warriors.pass_word_recovery.model.AccountChangePasswordDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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


    public String getEmailByUserName(String username) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/getEmailByUsername/" + username, String.class);
    }

    public void changePassword(AccountChangePasswordDto account) throws IOException, JSONException {
        restTemplate.put("http://" + getIp() + ":8088/account/changePassword"
                , createRequest(setJsonValueOfWarriorDTO(account)));
    }

    private HttpEntity<String> createRequest(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }

    private JSONObject setJsonValueOfWarriorDTO(AccountChangePasswordDto account) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", account.getUsername());
        jsonObject.put("password", account.getPassword());
        return jsonObject;
    }

}
