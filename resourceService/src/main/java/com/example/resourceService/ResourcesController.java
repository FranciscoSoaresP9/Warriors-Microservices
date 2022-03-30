package com.example.resourceService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *Class to mapping all resources
 */
@Controller
@RequestMapping(path = "/resource")
public class ResourcesController {

    //------------CSS----------------------------
    @GetMapping(path = "/css/stylecreatewarrior.css")
    public String stylecreatewarrior() {
        return "css/warrior/stylecreatewarrior.css";
    }

    @GetMapping(path = "/css/cardStyle.css")
    public String styleStatusWarrior() {
        return "css/warrior/cardStyle.css";
    }

    @GetMapping(value = "/css/stylelogin.css")
    public String styleLogin() {
        return "css/account/stylelogin.css";
    }

    @GetMapping(value = "css/stylemainpage.css")
    public String styleMainPage() {
        return "/css/mainpage/stylemainpage.css";
    }

    @GetMapping(value = "/css/style.css")
    public String styleCreateAccount() {
        return "css/account/style.css";
    }

    @GetMapping(value = "/css/play.css")
    public String stylePlay() {
        return "css/play/play.css";
    }

    @GetMapping(path = "/fonts/line-awesome/css/line-awesome.min.css")
    public String fonts1() {
        return "css/fonts/line-awesome/css/line-awesome.min.css";
    }

    @GetMapping(path = "/css/opensans-font.css")
    public String opensansFont() {
        return "css/account/opensans-font.css";
    }


//---------------JS------------------------------------


    @GetMapping(path = "/js/cardScript.js")
    public String satusScript() {
        return "js/cardScript.js";
    }

    @GetMapping(path = "/js/pvmfight.js")
    public String pvmFightScript() {
        return "js/pvmfight.js";
    }

    @GetMapping(path = "/js/createNewAccount.js")
    public String creatNewAccount() {
        return "js/createaccount.js";
    }

    @GetMapping(path = "/js/createwarrior.js")
    public String creatWarrior() {
        return "js/createwarrior.js";
    }

    @GetMapping(path = "/js/login.js")
    public String login() {
        return "js/login.js";
    }

    @GetMapping(path = "/js/mainpage.js")
    public String mainpage() {
        return "js/mainpage.js";
    }

    @GetMapping(path = "/js/mainpageloged.js")
    public String mainpageLoged() {
        return "js/mainpageloged.js";
    }


}
