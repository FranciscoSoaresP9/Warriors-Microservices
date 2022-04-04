package com.warriors.login;


import com.warriors.login.model.account.Account;
import org.json.JSONException;
import org.json.JSONObject;
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



    private String getIp() throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int lastIndexOfIp = line.lastIndexOf(":");

        return line.substring(line.indexOf("//") + 2, lastIndexOfIp);
    }

    public Boolean isUserNameExist(String userName) throws IOException, JSONException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/isUserNameExist/"+userName
                , Boolean.class);
    }
    public Account getAccount(String userName) throws IOException, JSONException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/getAccountByUsername/"+userName
                , Account.class);
    }


}
