import {PvmFight20} from "./PvmFight2.0.js";
import {WarriorStatusCard} from "./WarriorStatusCard.js";
import {WarriorStatusElements} from "./WarriorStatusElements.js";
import {RequestSender} from './RequestSender.js';
import {MonsterStatusCard} from "./MonsterStatusCard.js";
import {MonsterStatusElements} from "./MonsterStatusElements.js";
import {AccountLodgedChecker} from './AccountLodgedChecker.js';
import {Loader} from "./Loader.js";


const pvmFight = new PvmFight20();
const warrior = sessionStorage.getItem("warrior");
const warriorStatusElement = new WarriorStatusElements(JSON.parse(warrior));
const warriorStatusCard = new WarriorStatusCard();
const monsterStatusCard= new MonsterStatusCard();
window.onload = () => {
    bootStrap();
    pvmFight.setWarrior(JSON.parse(warrior));
    pvmFight.setRequestSender(new RequestSender);
    pvmFight.setMonsterStatusCard(monsterStatusCard);
    pvmFight.setWarriorStatusCard(warriorStatusCard);
}

function bootStrap() {
    warriorStatusCard.setWarriorStatusElements(warriorStatusElement);
    warriorStatusCard.setWarrior(JSON.parse(warrior));
    warriorStatusCard.setRequestSender(new RequestSender());
    warriorStatusCard.buildStatusBarsWithoutPlusButton();
    monsterStatusCard.setMonsterStatusElements(new MonsterStatusElements());



}
