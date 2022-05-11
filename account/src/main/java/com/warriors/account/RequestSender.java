package com.warriors.account;



import com.warriors.account.warrior.Warrior;
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

    public Warrior getWarrior(Integer accountId) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/warrior/api/get/" + accountId, Warrior.class);
    }





}
