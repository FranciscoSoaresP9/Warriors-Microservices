package com.example.resourceService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Class to mapping all resources
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

    @GetMapping(value = "/css/navBar.css")
    public String styleNavBar() {
        return "css/navBar.css";
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
        return "js/warriorStatus/WarriorStatusMain.js";
    }

    @GetMapping(path = "/js/WarriorStatusElements.js")
    public String warriorStatusElementsScript() {
        return "js/warriorStatus/WarriorStatusElements.js";
    }


    @GetMapping(path = "/js/WarriorStatusCard.js")
    public String warriorSatusCardScript() {
        return "js/warriorStatus/WarriorStatusCard.js";
    }

    @GetMapping(path = "/js/MonsterStatusElements.js")
    public String monsterStatusElementsScript() {
        return "js/monster/MonsterStatusElements.js";
    }

    @GetMapping(path = "/js/MonsterStatusCard.js")
    public String monsterStatusCardScript() {
        return "js/monster/MonsterStatusCard.js";
    }



    @GetMapping(path = "/js/UpdateStatus.js")
    public String UpdateStatusScript() {
        return "js/warriorStatus/UpdateStatus.js";
    }

    @GetMapping(path = "/js/pvmfight.js")
    public String pvmFightScript() {
        return "js/fight/pvmfight/PvmFightMain.js";
    }

    @GetMapping(path = "/js/PvmFight2.0.js")
    public String pvmMainFightScript() {
        return "js/fight/pvmfight/PvmFight2.0.js";
    }

    @GetMapping(path = "/js/MainRegistryAccount.js")
    public String mainRegistryAccount() {
        return "js/registry/account/MainRegistryAccount.js";
    }

    @GetMapping(path = "/js/RegistryAccount.js")
    public String registryAccount() {
        return "js/registry/account/RegistryAccount.js";
    }

    @GetMapping(path = "/js/MainRegistryWarrior.js")
    public String creatWarrior() {
        return "js/registry/warrior/MainRegistryWarrior.js";
    }

    @GetMapping(path = "/js/ChoseType.js")
    public String choseType() {
        return "js/registry/warrior/ChoseType.js";
    }

    @GetMapping(path = "/js/RegistryWarrior.js")
    public String registryWarrior() {
        return "js/registry/warrior/RegistryWarrior.js";
    }


    @GetMapping(path = "/js/Login.js")
    public String login() {
        return "js/login/Login.js";
    }

    @GetMapping(path = "/js/MainLogin.js")
    public String mainlogin() {
        return "js/login/MainLogin.js";
    }

    @GetMapping(path = "/js/mainpage.js")
    public String mainpage() {
        return "js/mainpage/mainpage.js";
    }

    @GetMapping(path = "/js/mainpageloged.js")
    public String mainpageLoged() {
        return "js/mainpageloged.js";
    }

    @GetMapping(path = "js/RequestSender.js")
    public String requestSender() {
        return "js/requestsender/RequestSender.js";
    }

    @GetMapping(path = "js/AccountLodgedChecker.js")
    public String accountLodgedChecker() {
        return "js/accountlodgedchecker/AccountLodgedChecker.js";
    }

    @GetMapping(path = "js/Loader.js")
    public String loader() {
        return "js/loader/Loader.js";
    }

    @GetMapping(path = "js/RecoveryPassword.js")
    public String recoveryPassword(){
        return "js/login/RecoveryPassword.js";
    }

}
