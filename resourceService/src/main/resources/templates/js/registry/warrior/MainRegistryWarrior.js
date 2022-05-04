import {RegistryWarrior} from "./RegistryWarrior.js";
import {RequestSender} from './RequestSender.js';
import {AccountLodgedChecker} from './AccountLodgedChecker.js';
import {Loader} from "./Loader.js";

window.onload = () => {
    console.log(sessionStorage.getItem("warrior"));
    const accountLodgedChecker = new AccountLodgedChecker();
    accountLodgedChecker.isLodged("../mainpageloged");
}

const registryWarrior = new RegistryWarrior();
var warriorType;

async function handleSubmit(event) {
    const data = new FormData(event.target);
    let warrior={};
    console.log(Object.fromEntries(data.entries()));
    warrior.name = Object.fromEntries(data.entries()).name;
    warrior.accountId = sessionStorage.getItem("id");
    warrior.warriorType = warriorType;


    disableButton();
  bootStrap(warrior);
  await registryWarrior.registry();
}

function bootStrap(data) {
    registryWarrior.setData(data);
    registryWarrior.setLoader(new Loader());
    registryWarrior.setRequestSender(new RequestSender());
}


function disableButton() {
    const submitButton = document.getElementById('submitbutton');
    submitButton.disabled = true;
}


$('#assassin').click(() => {
    warriorType = "ASSASSIN"
});
$('#warrior').click(() => {
    warriorType = "WARRIOR"
});
$('#archer').click(() => {
    warriorType = "ARCHER"
});
const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);