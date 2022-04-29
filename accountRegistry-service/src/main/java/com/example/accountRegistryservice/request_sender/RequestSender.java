package com.example.accountRegistryservice.request_sender;

import com.example.accountRegistryservice.model.Account;
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
     * This method send a request to persist a Account on Database
     *
     * @param account
     * @throws IOException
     * @throws JSONException
     */
    public Account persistAccountInDB(Account account) throws IOException, JSONException {
        return restTemplate.postForObject("http://" + getIp() + ":8088/account/add",
                createHttpEntity(setJsonValueOfAccount(account)), Account.class);
    }

    /**
     * This method send a request to check if the username already exist
     *
     * @param username
     * @throws IOException
     * @throws JSONException
     */
    public Boolean isUserNameExist(String username) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/isUserNameExist/" + username
                , Boolean.class);
    }

    /**
     * This method send a request to check if the email already exist
     *
     * @param email
     * @throws IOException
     * @throws JSONException
     */
    public Boolean isEmailExist(String email) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/isEmailExist/" + email
                , Boolean.class);
    }

    /**
     * This method create a HttpEntity
     *
     * @param jsonObject
     * @return
     * @see HttpEntity
     */
    private HttpEntity<String> createHttpEntity(JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(jsonObject.toString(), headers);
    }

    /**
     * This method transform the account into JSONObject
     *
     * @param account
     * @return
     * @see JSONObject
     */
    private JSONObject setJsonValueOfAccount(Account account) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", account.getUsername());
        jsonObject.put("email", account.getEmail());
        jsonObject.put("password", account.getPassword());
        return jsonObject;
    }

}
