package com.example.pagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 *Class to mapping all views
 */
@Controller
@RequestMapping("/page")
public class MainController {

    /**
     *Method to mapping Create Account View
     * @return createaccount view
     */

    @RequestMapping(method = RequestMethod.GET,path = "/newaccount")
    public String register() {

        return "account/create/registryAccount.html";
    }

    /**
     *Method to mapping Main Page View
     * @return mainpage view
     */
    @GetMapping(path ={ "/mainpage",""})
    public String mainPage() {
        System.out.println("where");

        return "mainpage/mainpage.html";
    }

    /**
     *Method to mapping Login View
     * @return login view
     */

    @RequestMapping(method = RequestMethod.GET,path = "/login")
    public String login() {

        return "account/login/login.html";
    }


    @RequestMapping(method = RequestMethod.GET,path = "/mainpageloged")
    public String mainpageLoged() {

        return "mainpage/mainpageloged.html";
    }


    @RequestMapping(method = RequestMethod.GET,path = "/createwarrior")
    public String createWarrior() {

        return "warrior/create/createwarrior.html";
    }
    @RequestMapping(method = RequestMethod.GET,path = "/warriorstatus")
    public String warriorStatus() {
        return "warrior/card/statuscard.html";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/play")
    public String play() {
        return "play/pvmfight.html";
    }

}

