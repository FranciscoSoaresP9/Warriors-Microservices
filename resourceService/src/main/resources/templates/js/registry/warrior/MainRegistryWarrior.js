import {RegistryWarrior} from "./RegistryWarrior.js";
import {RequestSender} from './RequestSender.js';
import {AccountLodgedChecker} from './AccountLodgedChecker.js';
import {Loader} from "./Loader.js";

window.onload = () => {

    const accountLodgedChecker = new AccountLodgedChecker();
    accountLodgedChecker.isLodged("../mainpageloged");
}

const registryWarrior = new RegistryWarrior();


async function handleSubmit(event) {
    const data = new FormData(event.target);
    const warrior = Object.fromEntries(data.entries());
    warrior.warriorType = getWarriorType();
    warrior.accountId = sessionStorage.getItem("id");
    disableButton();
    bootStrap(warrior);
    await registryWarrior.registry();
}

function bootStrap(data) {
    registryWarrior.setData(data);
    registryWarrior.setLoader(new Loader());
    registryWarrior.setRequestSender(new RequestSender());
}

function getWarriorType() {
    const allElements = document.getElementsByClassName("warrior-class-active")
    const typeOfWarrior = allElements[0].innerText;

    return typeOfWarrior;
}

function disableButton() {
    const submitButton = document.getElementById('submitbutton');
    submitButton.disabled = true;
}





const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);