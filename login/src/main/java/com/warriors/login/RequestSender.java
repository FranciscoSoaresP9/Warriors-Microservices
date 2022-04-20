package com.warriors.login;


import com.warriors.login.model.account.Account;
import org.json.JSONException;
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
     * This method send a request to check if the username exist
     *
     * @param username
     * @throws IOException
     * @throws JSONException
     */
    public Boolean isUserNameExist(String username) throws IOException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/isUserNameExist/"+username
                , Boolean.class);
    }

    /**
     * This method send a request to get an Account from Database
     *
     * @param username
     * @throws IOException
     * @throws JSONException
     */
    public Account getAccount(String username) throws IOException, JSONException {
        return restTemplate.getForObject("http://" + getIp() + ":8088/account/getAccountByUsername/"+username
                , Account.class);
    }


}
