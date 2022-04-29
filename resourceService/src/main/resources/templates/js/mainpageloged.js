import {AccountLodgedChecker} from "./AccountLodgedChecker";

window.onload = () => {
    const accountLodgedChecker = new AccountLodgedChecker();
    accountLodgedChecker.check('/page/mainpage');

}


function logout() {

    if (sessionStorage.getItem("id") !== "null") {
        sessionStorage.setItem("id", null);
        window.location = "../page/mainpage"
        return;
    }

}