import {RegistryAccount} from "./RegistryAccount.js";
import {RequestSender} from './RequestSender.js';
import {AccountLodgedChecker} from './AccountLodgedChecker.js';
import {Loader} from "./Loader.js";

window.onload = () => {
    const accountLodgedChecker = new AccountLodgedChecker();
    accountLodgedChecker.isLodged("../mainpageloged");
}

const registryAccount = new RegistryAccount();

function bootStrap(data) {
    registryAccount.setData(data);
    registryAccount.setLoader(new Loader());
    registryAccount.setRequestSender(new RequestSender());
}

async function handleSubmit(event) {
    event.preventDefault();

    const data = new FormData(event.target);
    const value = Object.fromEntries(data.entries());
    bootStrap(value);
    await registryAccount.registry();

}

const form = document.querySelector('form');
form.addEventListener('submit', handleSubmit);