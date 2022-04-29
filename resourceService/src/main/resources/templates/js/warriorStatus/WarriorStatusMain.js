import {WarriorStatusCard} from "./WarriorStatusCard.js";
import {WarriorStatusElements} from "./WarriorStatusElements.js";
import {RequestSender} from './RequestSender.js';
import {AccountLodgedChecker} from './AccountLodgedChecker.js';
import {Loader} from "./Loader.js";

const warrior = sessionStorage.getItem("warrior");
const warriorStatusElement = new WarriorStatusElements(JSON.parse(warrior));
const warriorStatusCard = new WarriorStatusCard();
window.onload = () => {
    let warrior = JSON.parse(sessionStorage.getItem("warrior"));
    if (warrior == null) {
        window.location = "../page/createwarrior";
        return;
    }

    bootStrap();
}

function bootStrap() {
    warriorStatusCard.setWarriorStatusElements(warriorStatusElement);
    warriorStatusCard.setWarrior(JSON.parse(warrior));
    warriorStatusCard.setRequestSender(new RequestSender());
    warriorStatusCard.buildStatusBars();
}
