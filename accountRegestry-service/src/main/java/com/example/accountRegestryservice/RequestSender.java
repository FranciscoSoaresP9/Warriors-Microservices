package com.example.accountRegestryservice;


import com.example.accountRegestryservice.model.Account;
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

    public void persistAccountInDB(Account account) throws IOException, JSONException {
        System.out.println("ACCOUNT ON PERSIST"+account.toString());
        restTemplate.postForObject("http://" + getIp() + ":8088/account/add",
                createRequest(setJsonValueOfAccount(account)), String.class);
    }
    public Boolean isUserNameExist(String userName) throws IOException, JSONException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/isUserNameExist/"+userName
                , Boolean.class);
    }

    public Boolean isEmailExist(String email) throws IOException, JSONException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/isUserNameExist/"+email
                , Boolean.class);
    }

    private HttpEntity<String> createRequest(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }

    private JSONObject setJsonValueOfAccount(Account acccount) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", acccount.getUsername());
        jsonObject.put("email", acccount.getEmail());
        jsonObject.put("password", acccount.getPassword());
        System.out.println("JSON OBJECT ON SET JSON VALUE -->" + jsonObject);
        return jsonObject;
    }

}
