import {PvmFight20} from "./PvmFight2.0.js";
import {WarriorStatusCard} from "./WarriorStatusCard.js";
import {WarriorStatusElements} from "./WarriorStatusElements.js";
import {RequestSender} from './RequestSender.js';
import {AccountLodgedChecker} from './AccountLodgedChecker.js';
import {Loader} from "./Loader.js";

const pvmFight = new PvmFight20();
const warrior = sessionStorage.getItem("warrior");
const warriorStatusElement = new WarriorStatusElements(JSON.parse(warrior));
const warriorStatusCard = new WarriorStatusCard();
window.onload = () => {
    bootStrap();
    pvmFight.setWarrior(JSON.parse(warrior));
    pvmFight.setRequestSender(new RequestSender);
}

function bootStrap() {
    warriorStatusCard.setWarriorStatusElements(warriorStatusElement);
    warriorStatusCard.setWarrior(JSON.parse(warrior));
    warriorStatusCard.setRequestSender(new RequestSender());
    warriorStatusCard.buildStatusBarsWithoutPlusButton();
}
